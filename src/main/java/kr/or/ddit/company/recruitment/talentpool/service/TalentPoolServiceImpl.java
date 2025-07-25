package kr.or.ddit.company.recruitment.talentpool.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.TalentPoolMapper;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TalentPoolServiceImpl implements TalentPoolService {
	private final TalentPoolMapper mapper;
	
	@Override
	public List<ResumeVO> readResumeByFilter(Map<String, Object> params) {
		List<ResumeVO> resumeList = mapper.selectResumeByFilter(params);
		return resumeList;
	}

}
