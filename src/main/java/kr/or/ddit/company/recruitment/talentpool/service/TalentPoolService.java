package kr.or.ddit.company.recruitment.talentpool.service;

import java.util.Map;

public interface TalentPoolService {
	public Map<String, Object> readResumeByFilter(Map<String, Object> params);
}
