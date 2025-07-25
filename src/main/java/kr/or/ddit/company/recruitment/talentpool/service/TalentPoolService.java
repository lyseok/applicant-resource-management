package kr.or.ddit.company.recruitment.talentpool.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.resume.ResumeVO;

public interface TalentPoolService {
	public List<ResumeVO> readResumeByFilter(Map<String, Object> params);
}
