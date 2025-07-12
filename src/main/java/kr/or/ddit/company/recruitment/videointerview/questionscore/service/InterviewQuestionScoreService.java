package kr.or.ddit.company.recruitment.videointerview.questionscore.service;

import java.util.List;

import kr.or.ddit.dto.InterviewQuestionScoreListDTO;
import kr.or.ddit.vo.recruitment.InterviewQuestionScoreVO;

public interface InterviewQuestionScoreService {
	public List<InterviewQuestionScoreVO> readInterviewQuestionScoreList();
	public InterviewQuestionScoreVO readInterviewQuestionScore(InterviewQuestionScoreVO vo);
	public void createInterviewQuestionScoreList(InterviewQuestionScoreListDTO dto);
	public void modifyInterviewQuestionScore(InterviewQuestionScoreVO vo);
	public void removeInterviewQuestionScore(String interviewScoreNo);
}
