package kr.or.ddit.member.common.mypage.interviewReview.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.recruitment.InterviewMapper;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class InterviewReviewServiceImpl implements InterviewReviewService{
	private final InterviewMapper interviewMapper;
	
	@Override
	public Map<String, Object> readInterviewWithCompanyNameByNo(String interviewNo) {
		return interviewMapper.selectInterviewWithCompanyNameByNo(interviewNo);
	}
	
}
