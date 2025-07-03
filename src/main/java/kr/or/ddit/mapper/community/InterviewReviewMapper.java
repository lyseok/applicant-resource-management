package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.InterviewInformationVO;
import kr.or.ddit.vo.community.InterviewReviewVO;
import kr.or.ddit.vo.community.PassInformationVO;
import kr.or.ddit.vo.recruitment.InterviewVO;

@Mapper
public interface InterviewReviewMapper {
	
	public InterviewReviewVO selectInterviewReviewByPk(String reivewNo);
	public List<InterviewVO> selectInterviewList(String userId);
	public List<InterviewReviewVO> selInterviewReviewList();
	public List<InterviewReviewVO> selectInterviewReviewListUser(String userId);
	public List<InterviewReviewVO> selectInterviewReviewListCom(String comName);
	
	public int insertInterviewReview(InterviewReviewVO interviewReview);
	public int deleteInterviewReview(String reviewNo);
	public int updateDeleteStatusMyInterviewReview(String reviewNo);
	
}
