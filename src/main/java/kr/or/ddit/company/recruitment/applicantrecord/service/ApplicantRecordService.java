package kr.or.ddit.company.recruitment.applicantrecord.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.recruitment.ApplicantRecordVO;
import kr.or.ddit.vo.recruitment.ApplicantVO;
import kr.or.ddit.vo.recruitment.PasserVO;
import kr.or.ddit.vo.resume.ResumeVO;

public interface ApplicantRecordService {
	
	public List<Map<String, Object>> getApplicantsByRecruitment(String recruitmentNo);
	public void updateNextStep(ApplicantRecordVO vo);
	public void updateFailed(ApplicantRecordVO vo);
	public List<PasserVO> selectPasserByRecruitment(String recruitmentNo);
	public List<ResumeVO> getResumeByApplicantId(List<String> applicantId);
	public void updateResumeView(String applicantId);
	public void updateHireDate(PasserVO vo);
}
