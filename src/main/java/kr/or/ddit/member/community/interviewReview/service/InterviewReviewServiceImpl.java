package kr.or.ddit.member.community.interviewReview.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.dto.InterviewReviewDTO;
import kr.or.ddit.mapper.community.InterviewInformationMapper;
import kr.or.ddit.mapper.community.InterviewReviewMapper;
import kr.or.ddit.mapper.community.PassInformationMapper;
import kr.or.ddit.mapper.recruitment.InterviewMapper;
import kr.or.ddit.vo.community.InterviewInformationVO;
import kr.or.ddit.vo.community.InterviewReviewVO;
import kr.or.ddit.vo.community.PassInformationVO;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class InterviewReviewServiceImpl implements InterviewReviewService{
	private final InterviewMapper interviewMapper;
	private final InterviewReviewMapper interviewReviewMapper;
	private final InterviewInformationMapper interviewInformationMapper;
	private final PassInformationMapper passInformationMapper;
	private final CodeMapProvider codeMapProvider;
	
	@Override
	public Map<String, Object> readInterviewWithCompanyNameByNo(String interviewNo) {
		return interviewMapper.selectInterviewWithCompanyNameByNo(interviewNo);
	}
	
	@Transactional
	@Override
	public boolean registerInterviewReview(InterviewReviewDTO dto) {
		
		int inserted = 0;
		
		InterviewReviewVO interviewReview = new InterviewReviewVO();
		interviewReview.setInterviewNo(dto.getInterviewNo());
		interviewReview.setComId(dto.getComId());
		interviewReview.setJobCode(dto.getJobCode());
		interviewReview.setInterviewDate(dto.getInterviewDate());
		interviewReview.setUserId(getUserId());
		inserted += createInterviewReview(interviewReview);		
	
		String reviewNo = interviewReview.getInterviewReviewNo();
		
		InterviewInformationVO interviewInformation = new InterviewInformationVO();
		interviewInformation.setInterviewReviewNo(reviewNo);
		interviewInformation.setEvaluation(dto.getEvaluation());
		interviewInformation.setInterviewLevel(dto.getInterviewLevel());
		interviewInformation.setInterviewType(dto.getInterviewType());
		interviewInformation.setInterviewContent(dto.getInterviewContent());
		inserted += createInterviewInformation(interviewInformation);
		
		
		
		PassInformationVO passInformation = new PassInformationVO();
		passInformation.setInterviewReviewNo(reviewNo);
		passInformation.setInterviewQuestion(dto.getInterviewQuestion());
		passInformation.setTip(dto.getTip());
		passInformation.setInterviewPassYn(dto.getInterviewPassYn());
		inserted += createPassInformation(passInformation);
		
		
		int expected = 3;
		return inserted == expected;
		
		
		
	}
	

	@Override
	public int createInterviewReview(InterviewReviewVO interviewReview) {
		return interviewReviewMapper.insertInterviewReview(interviewReview);
	}

	@Override
	public int createInterviewInformation(InterviewInformationVO interviewInformation) {
		return interviewInformationMapper.insertInterviewInformation(interviewInformation);
	}

	@Override
	public int createPassInformation(PassInformationVO passInformation) {
		return passInformationMapper.insertPassInformation(passInformation);
	}



	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}

	@Override
	public List<InterviewReviewVO> readInterviewReviewAllList() {
		List<InterviewReviewVO> interviewReview =  interviewReviewMapper.selectInterviewReviewAllList();
		for (InterviewReviewVO iReview : interviewReview) {
			String jobName = codeMapProvider.getJobName(iReview.getRecruitmentNotice().getJobCode());
			String year = codeMapProvider.getCodeName(iReview.getRecruitmentNotice().getYearCode());
			iReview.getRecruitmentNotice().setJobCodeName(jobName);
			iReview.getRecruitmentNotice().setYearCodeName(year);
		}
		
		return interviewReview;
	}

	
	
	
}
