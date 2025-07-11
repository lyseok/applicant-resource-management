package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.InterviewQuestionScoreVO;

@Mapper
public interface InterviewQuestionScoreMapper {
	public List<InterviewQuestionScoreVO> selectInterviewQuestionScoreList();
	public InterviewQuestionScoreVO selectInterviewQuestionScore(InterviewQuestionScoreVO vo);
	public int insertInterviewQuestionScore(InterviewQuestionScoreVO vo);
	public int updateInterviewQuestionScore(InterviewQuestionScoreVO vo);
	public int deleteInterviewQuestionScore(String interviewScoreNo);
}
