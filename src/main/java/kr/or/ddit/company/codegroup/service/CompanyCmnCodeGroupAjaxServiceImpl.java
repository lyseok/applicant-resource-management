package kr.or.ddit.company.codegroup.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.CmnCodeGroupMapper;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CompanyCmnCodeGroupAjaxServiceImpl implements CompanyCmnCodeGroupAjaxService{
	private final CmnCodeGroupMapper mapper;
	
	public List<CmnCodeGroupVO> readCmnCodeGroupList(){
		return mapper.selectCmnCodeGroupList();
	}
	
	public CmnCodeGroupVO readCmnCodeGroupByPk(String codeGroupNo) {
		return mapper.selectCmnCodeGroupByPk(codeGroupNo);
	}
}
