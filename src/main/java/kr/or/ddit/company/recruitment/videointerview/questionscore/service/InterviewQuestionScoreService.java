package kr.or.ddit.company.recruitment.videointerview.questionscore.service;

import java.util.List;

import kr.or.ddit.vo.recruitment.InterviewQuestionScoreVO;

public interface InterviewQuestionScoreService {
	public List<InterviewQuestionScoreVO> readInterviewQuestionScoreList();
	public InterviewQuestionScoreVO readInterviewQuestionScore(String interviewScoreNo);
	public void createInterviewQuestionScoreList(List<InterviewQuestionScoreVO> vo);
	public void modifyInterviewQuestionScore(InterviewQuestionScoreVO vo);
	public void removeInterviewQuestionScore(String interviewScoreNo);
}
