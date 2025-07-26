package kr.or.ddit.company.recruitment.talentpool.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.company.email.talentpool.service.JoboffSendService;
import kr.or.ddit.dto.MailDTO;
import kr.or.ddit.mapper.common.ScrabUserMapper;
import kr.or.ddit.mapper.common.TalentPoolMapper;
import kr.or.ddit.mapper.recruitment.ComMailTemMapper;
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
	
	// 저장된 관심 인재 리스트 조회
    public List<String> getSavedTalentList() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String companyId = authentication.getName();
        return scrabUserMapper.selectSavedTalentList(companyId);
    }

    // 증분 저장: 추가/삭제
    public void updateTalentList(List<String> addList, List<String> removeList) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String companyId = authentication.getName();
        if (addList != null && !addList.isEmpty()) {
        	scrabUserMapper.insertTalentUsers(companyId, addList);
        }
        if (removeList != null && !removeList.isEmpty()) {
        	scrabUserMapper.deleteTalentUsers(companyId, removeList);
        }
        
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
		
		for(MailDTO mail : mailList) {
			comMailTem.setTemNo(mail.getTemplate());
			ComMailTemVO data = comMailTemMapper.selectComMailTem(comMailTem);
			log.info("메일데이터 : {}, {}" , mail, data);
			String subject = data.getComName() + "에서 " + mail.getJob() + " 입사제안드립니다.";
			
			sendOfferMail(mail.getUserId(), subject, data.getTemContent());
		}
		return 0;
	}

	@Value("${spring.mail.username}")
	private String from;
	
    public void sendOfferMail(String toEmail, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);

        mailSender.send(message);
    }
}
