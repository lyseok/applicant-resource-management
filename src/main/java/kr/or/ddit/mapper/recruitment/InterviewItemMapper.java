package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.InterviewItemVO;

@Mapper
public interface InterviewItemMapper {
	public List<InterviewItemVO> selectInterviewItemList();
	public InterviewItemVO selectInterviewItem(String InterviewItemNo);
	public int insertInterviewItem(InterviewItemVO vo);
	public int updateInterviewItem(InterviewItemVO vo);
	public int deleteInterviewItem(String InterviewItemNo);
}
