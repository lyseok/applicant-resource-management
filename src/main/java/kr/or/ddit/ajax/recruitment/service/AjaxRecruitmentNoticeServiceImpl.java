package kr.or.ddit.ajax.recruitment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.dto.RecruitmentNoticeDTO;
import kr.or.ddit.mapper.common.ScrabCompanyMapper;
import kr.or.ddit.mapper.common.ScrabRecruitmentMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentNoticeMapper;
import kr.or.ddit.vo.common.ScrabCompanyVO;
import kr.or.ddit.vo.common.ScrabRecruitmentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class AjaxRecruitmentNoticeServiceImpl implements AjaxRecruitmentNoticeService {
	private final RecruitmentNoticeMapper mapper;
	private final ScrabCompanyMapper scrabCompanyMapper;
	private final ScrabRecruitmentMapper scrabRecruitmentMapper; 
	
	@Override
	public Map<String, Object> searchRecruitmentNoticeList(Map<String, Object> params) {
		Map<String, Object> resp = myScrabList();
		List<RecruitmentNoticeDTO> list = mapper.searchRecruitNoticeDtoList(params);
		int cnt = mapper.countRecruitmentNotice(params);
		
		resp.put("noticeCnt", cnt);
		resp.put("data", list);
		
		log.info("========> params: {}", params);
		
		return resp;
	}
	
	public Map<String, Object> myScrabList() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		List<ScrabCompanyVO> cVoList = scrabCompanyMapper.selectMyScrabCompanyList(username);
		List<ScrabRecruitmentVO> rVoList = scrabRecruitmentMapper.selectMyScrabRecruitmentList(username);
		
		List<String> cList = new ArrayList<String>();
		List<String> rList = new ArrayList<String>();
		
		for(ScrabCompanyVO vo : cVoList) {
			cList.add(vo.getCompanyId());
		}
		
		for(ScrabRecruitmentVO vo : rVoList) {
			rList.add(vo.getRecruitmentNo());
		}
		
		Map<String, Object> scrabMap = new HashMap<String, Object>();

		scrabMap.put("myScrabCompany", cList);
		scrabMap.put("myScrabRecruit", rList);
		
		return scrabMap;
	}

}
