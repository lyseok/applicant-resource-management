package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.InterviewInformationVO;

@Mapper
public interface InterviewInformationMapper {
	public List<InterviewInformationVO> selectInterviewInfromationList();
	public InterviewInformationVO selectInterviewInformationVO(String reviewNo);
	public int insertInterviewInformation(InterviewInformationVO interviewInformation);
}
