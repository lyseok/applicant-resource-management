package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.ApplicantAnswerVO;

@Mapper
public interface ApplicantAnswerMapper {
	public List<ApplicantAnswerVO> selectApplicantAnswerList();
	public ApplicantAnswerVO selectApplicantAnswer(String applicantAnswerNo);
	public int insertApplicantAnswer(ApplicantAnswerVO vo);
	public int updateApplicantAnswer(ApplicantAnswerVO vo);
	public int deleteApplicantAnswer(String applicantAnswerNo);
}
