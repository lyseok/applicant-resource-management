package kr.or.ddit.mapper.recruitment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.InterviewVO;

@Mapper
public interface InterviewMapper {
	public InterviewVO selectInterviewByNo(String interviewNo);
	public List<Map<String, Object>> selectMyInterviewList(String userId);
	public List<InterviewVO> selectInterviewList();
	public InterviewVO selectInterview(String interviewNo);
	public List<Map<String, Object>> selectMemberInterviewDetail(String interviewNo);
	public int insertInterview(InterviewVO vo);
	public int updateInterview(InterviewVO vo);
	public int deleteInterview(String inteviewNo);
}
