package kr.or.ddit.company.recruitment.talentpool.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import kr.or.ddit.dto.MailDTO;
import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.common.PaymentLogMapper;
import kr.or.ddit.mapper.common.PaymentMapper;
import kr.or.ddit.mapper.common.ReadResumeMapper;
import kr.or.ddit.mapper.common.ScrabUserMapper;
import kr.or.ddit.mapper.common.TalentPoolMapper;
import kr.or.ddit.mapper.recruitment.ComMailTemMapper;
import kr.or.ddit.vo.common.PaymentLogVO;
import kr.or.ddit.vo.common.PaymentVO;
import kr.or.ddit.vo.common.ReadResumeVO;
import kr.or.ddit.vo.common.ScrabUserVO;
import kr.or.ddit.vo.recruitment.ComMailTemVO;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TalentPoolServiceImpl implements TalentPoolService {
	private final TalentPoolMapper mapper;
	private final ScrabUserMapper scrabUserMapper;
	private final JavaMailSender mailSender;
	private final ComMailTemMapper comMailTemMapper;
	private final PaymentMapper paymentMapper;
	private final PaymentLogMapper paymentLogMapper;

	private final CompanyMapper companyMapper;

	private final ReadResumeMapper readResumeMapper;

	@Override
	public Map<String, Object> readResumeByFilter(Map<String, Object> params) {
		List<ResumeVO> resumeList = mapper.selectResumeByFilter(params);
		int totalCnt = mapper.selectResumeCountByFilter(params);
		Map<String, Object> resp = new HashMap<String, Object>();
		resp.put("data", resumeList);
		resp.put("totalCnt", totalCnt);
		log.info("{}", params);

		return resp;
	}
	public String NonPayment() {
		String paymentY = companyMapper.selectComPayment(getUserId());
		return paymentY;
	}
	
	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();		// 기업 ID 
		}
	
	// 저장된 관심 인재 리스트 조회
	public List<String> getSavedTalentList() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String companyId = authentication.getName();
		return scrabUserMapper.selectSavedTalentList(companyId);
	}

	public void replaceTalentUsers(List<String> newUserList) {
        StopWatch sw = new StopWatch("replaceTalentUsers");
        sw.start();

        String companyId = SecurityContextHolder.getContext().getAuthentication().getName();

        // 1) 전부 삭제
        int del = scrabUserMapper.deleteAllTalentUsers(companyId);

        // 2) 새로 INSERT (빈 리스트면 skip)
        scrabUserMapper.insertTalentUsers(companyId, newUserList);

        sw.stop();
        log.info("[perf] replaceTalentUsers took {} ms (deleted={}, inserted={}, requested={})",
                sw.getTotalTimeMillis(), del, newUserList == null ? 0 : newUserList.size());
    }
	
	// 증분 저장: 추가/삭제
	public void updateTalentList(List<String> addList, List<String> removeList) {
		StopWatch sw = new StopWatch("updateTalentList");
        sw.start();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String companyId = authentication.getName();
		if (addList != null && !addList.isEmpty()) {
			scrabUserMapper.insertTalentUsers(companyId, addList);
		}
		if (removeList != null && !removeList.isEmpty()) {
			scrabUserMapper.deleteTalentUsers(companyId, removeList);
		}

		sw.stop();
        log.info("[perf] updateTalentList took {} ms (add={}, remove={})",
                sw.getTotalTimeMillis(),
                addList == null ? 0 : addList.size(),
                removeList == null ? 0 : removeList.size());
	}

	@Override
	public Map<String, Object> readSetupList() {
		Map<String, Object> resp = new HashMap<String, Object>();

		List<String> licList = mapper.selectLicenseList();
		List<String> eduList = mapper.selectEducationList();
		List<String> skiList = mapper.selectSkillList();

		resp.put("skiList", skiList);
		resp.put("eduList", eduList);
		resp.put("licList", licList);

		return resp;
	}

	@Override
	public Map<String, Object> readResumeByMyScrab(Map<String, Object> params) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String companyId = authentication.getName();
		params.put("comId", companyId);

		List<ResumeVO> resumeList = mapper.selectScrabResume(params);
		int totalCnt = mapper.selectCountScrabResume(params);
		Map<String, Object> resp = new HashMap<String, Object>();
		resp.put("data", resumeList);
		resp.put("totalCnt", totalCnt);

		return resp;
	}

	@Override
	public int postMailLogic(List<MailDTO> mailList) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		ComMailTemVO comMailTem = new ComMailTemVO();
		comMailTem.setUserId(username);

		for (MailDTO mail : mailList) {
			comMailTem.setTemNo(mail.getTemplate());
			ComMailTemVO data = comMailTemMapper.selectComMailTem(comMailTem);
			log.info("메일데이터 : {}, {}", mail, data);
			String subject = data.getComName() + "에서 " + mail.getJob() + " 입사제안드립니다.";

			sendOfferMail(mail.getUserId(), subject, data.getTemContent());
		}
		return 0;
	}

	@Value("${spring.mail.username}")
	private String from;

	public void sendOfferMail(String toEmail, String subject, String content) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		SimpleMailMessage message = new SimpleMailMessage();
		PaymentLogVO lvo = new PaymentLogVO();
		PaymentVO pvo = paymentMapper.selectStauts(username);
		log.info("pvo : {}", pvo);
		if (pvo.getUsageRemaining() == -1) {
			lvo.setEmailAddress(toEmail);
			lvo.setMessageBody(content);
			lvo.setSubject(subject);
			lvo.setPaymentNo(pvo.getPaymentNo());
			lvo.setProductNo(pvo.getProductNo());
			lvo.setUsedCount(1);
			paymentLogMapper.insertLog(lvo);

			message.setTo(toEmail);
			message.setSubject(subject);
			message.setText(content);
			message.setFrom(from);

			mailSender.send(message);
		} else {
			int result = paymentMapper.minuseaining(pvo.getPaymentNo());
			if (result == 0) {
				log.warn("사용가능 횟수가 0이여서 발송이 되지 않습니다");
			}

			lvo.setEmailAddress(toEmail);
			lvo.setMessageBody(content);
			lvo.setSubject(subject);
			lvo.setProductNo(pvo.getProductNo());
			lvo.setPaymentNo(pvo.getPaymentNo());
			lvo.setUsedCount(1);
			paymentLogMapper.insertLog(lvo);
			
			paymentLogMapper.insertLog(lvo);

			message.setTo(toEmail);
			message.setSubject(subject);
			message.setText(content);
			message.setFrom(from);

			mailSender.send(message);

		}

	}

	@Override
	public int updateResumeConfirm(String userId) {
		ScrabUserVO vo = new ScrabUserVO();
		vo.setCompanyId(getUserId());
		vo.setUserId(userId);
		return scrabUserMapper.updateResumeConfirm(vo);
	}
	

	@Override
	public int createReadResume(String resumeNo) {
		ReadResumeVO vo = new ReadResumeVO();
		vo.setCompanyId(getUserId());
		vo.setResumeNo(resumeNo);
		return readResumeMapper.insertReadResume(vo);
	}

}
