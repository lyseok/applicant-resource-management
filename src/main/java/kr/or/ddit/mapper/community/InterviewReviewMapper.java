package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.InterviewInformationVO;
import kr.or.ddit.vo.community.InterviewReviewVO;
import kr.or.ddit.vo.community.PassInformationVO;
import kr.or.ddit.vo.recruitment.InterviewVO;

@Mapper
public interface InterviewReviewMapper {
	public List<InterviewVO> selectInterviewList(String id);
	public InterviewReviewVO selectInterviewReview();
	public List<InterviewReviewVO> selectInterviewReviewList(String id);
	
	
	public int insertInterviewReview(InterviewReviewVO interviewReview);
	public int insertInterviewInformation(InterviewInformationVO interviewInformation);
	public int insertPassInformation(PassInformationVO passInformation);
	
	public int deleteInterviewReview(String id);
}
