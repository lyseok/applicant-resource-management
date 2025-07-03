package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.ApplicantRecordVO;

@Mapper
public interface ApplicantRecordMapper {
	public List<ApplicantRecordVO> selectApplicantRecordList();
	public ApplicantRecordVO selectApplicantRecord(String ApplicantRecordNo);
	public int insertApplicantRecord(ApplicantRecordVO vo);
	public int updateApplicantRecord(ApplicantRecordVO vo);
	public int deleteApplicantRecord(String ApplicantRecordNo);
}
