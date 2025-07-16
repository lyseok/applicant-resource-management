package kr.or.ddit.company.recruitment.applicantrecord.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.recruitment.ApplicantRecordMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicantRecordServiceImpl implements ApplicantRecordService {
	
	private final ApplicantRecordMapper applMapper;

	@Override
	public List<Map<String, Object>> getApplicantsByRecruitment(String recruitmentNo) {
		
		return applMapper.getApplicantByRecruitment(recruitmentNo);
	}

}
