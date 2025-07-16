package kr.or.ddit.company.recruitment.applicantrecord.service;

import java.util.List;
import java.util.Map;

public interface ApplicantRecordService {
	
	public List<Map<String, Object>> getApplicantsByRecruitment(String recruitmentNo);

}
