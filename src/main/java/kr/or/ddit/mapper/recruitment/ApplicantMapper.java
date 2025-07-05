package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.ApplicantVO;

@Mapper
public interface ApplicantMapper {
	public List<ApplicantMapper> selectApplicantList();
	public ApplicantVO selectApplicant(String applicantId);
	public int insertApplicant(ApplicantVO vo);
	public int updateApplicant(ApplicantVO vo);
	public int deleteApplicant(String applicantId);
}
