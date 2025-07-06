package kr.or.ddit.ajax.common.codegroup.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.CmnCodeGroupMapper;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CmnCodeGroupAjaxServiceImpl implements CmnCodeGroupAjaxService{
	private final CmnCodeGroupMapper mapper;
	
	public List<CmnCodeGroupVO> readCmnCodeGroupList(){
		return mapper.selectCmnCodeGroupList();
	}
	
	public CmnCodeGroupVO readCmnCodeGroupByPk(String codeGroupNo) {
		return mapper.selectCmnCodeGroupByPk(codeGroupNo);
	}
	
	public int createCmnCodeGroup(CmnCodeGroupVO cmnCodeGroup) {
		return mapper.insertCmnCodeGroup(cmnCodeGroup);
	}
	
	public int modifyCmnCodeGroup(CmnCodeGroupVO cmnCodeGroup) {
		return mapper.updateCmnCodeGroup(cmnCodeGroup);
	}
	
	public int modifyUseYn(CmnCodeGroupVO cmnCodeGroup) {
		return mapper.updateUseYn(cmnCodeGroup);
	}
	
	public int removeCmnCodeGroup(String codeGroupNo) {
		return mapper.deleteCmnCodeGroup(codeGroupNo);
	}
}
