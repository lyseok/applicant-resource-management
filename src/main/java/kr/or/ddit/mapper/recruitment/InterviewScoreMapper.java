package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.InterviewScoreVO;

@Mapper
public interface InterviewScoreMapper {
	public List<InterviewScoreVO> selectInterviewScoreList();
	public InterviewScoreVO selectInterviewScore(String InterviewScoreNo);
	public int insertInterviewScore(InterviewScoreVO vo);
	public int updateInterviewScore(InterviewScoreVO vo);
	public int deleteInterviewScore(String InterviewScoreNo);
	
}
