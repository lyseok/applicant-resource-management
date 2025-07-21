package kr.or.ddit.member.common.mypage.interviewReview.service;

import java.util.Map;

import kr.or.ddit.dto.InterviewReviewDTO;
import kr.or.ddit.vo.community.InterviewInformationVO;
import kr.or.ddit.vo.community.InterviewReviewVO;
import kr.or.ddit.vo.community.PassInformationVO;

public interface InterviewReviewService {
	public Map<String, Object> readInterviewWithCompanyNameByNo(String interviewNo);
	
	public int createInterviewReview(InterviewReviewVO interviewReview);
	
	public int createInterviewInformation(InterviewInformationVO interviewInformation);
	
	public int createPassInformation(PassInformationVO passInformation);
	
	public boolean registerInterviewReview(InterviewReviewDTO interviewReviewDTO);
	
}
