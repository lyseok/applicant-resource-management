package kr.or.ddit.company.recruitment.applicantrecord.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.recruitment.ApplicantRecordVO;

public interface ApplicantRecordService {
	
	public List<Map<String, Object>> getApplicantsByRecruitment(String recruitmentNo);
	public void updateNextStep(ApplicantRecordVO vo);
}
