package kr.or.ddit.company.recruitment.talentpool.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.TalentPoolMapper;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TalentPoolServiceImpl implements TalentPoolService {
	private final TalentPoolMapper mapper;
	
	@Override
	public Map<String, Object> readResumeByFilter(Map<String, Object> params) {
		List<ResumeVO> resumeList = mapper.selectResumeByFilter(params);
		int totalCnt = mapper.selectResumeCountByFilter(params); 
		Map<String, Object> resp = new HashMap<String, Object>();
		resp.put("data", resumeList);
		resp.put("totalCnt", totalCnt);
		log.info("{}", params);
		
		
		return resp;
	}

}
