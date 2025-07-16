package kr.or.ddit.mapper.recruitment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.ApplicantRecordVO;

@Mapper
public interface ApplicantRecordMapper {
	public List<ApplicantRecordVO> selectApplicantRecordList();
	public ApplicantRecordVO selectApplicantRecord(String ApplicantRecordNo);
	public int insertApplicantRecord(ApplicantRecordVO vo);
	public int updateApplicantRecord(ApplicantRecordVO vo);
	public int deleteApplicantRecord(String ApplicantRecordNo);
	public String selectMemberVideoURL(String ApplicantRecordNo);
	
	public List<Map<String, Object>> getApplicantByRecruitment(String recruitmentNo);
	public int updateInterviewURL(ApplicantRecordVO vo);
	public int updateApplication(String ApplicantRecordNo);
}
