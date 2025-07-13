package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.InterviewQuestionVO;

@Mapper
public interface InterviewQuestionMapper {
	public List<InterviewQuestionVO> selectInterviewQuestionList();
	public InterviewQuestionVO selectInterviewQuestionByNo(String InterviewQuestionNo);
	public int insertInterviewQuestion(InterviewQuestionVO vo);
	public int updateInterviewQuestion(InterviewQuestionVO vo);
	public int deleteInterviewQuestion(String InterviewQuestionNo);
}
