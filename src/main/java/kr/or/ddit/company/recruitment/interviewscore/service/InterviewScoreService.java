package kr.or.ddit.company.recruitment.interviewscore.service;

import java.util.List;

import kr.or.ddit.vo.recruitment.InterviewScoreVO;

public interface InterviewScoreService {
	public void createInterviewScoreList(List<InterviewScoreVO> interviewScoreList);
	public void createInterviewScore(InterviewScoreVO interviewScore);
}
