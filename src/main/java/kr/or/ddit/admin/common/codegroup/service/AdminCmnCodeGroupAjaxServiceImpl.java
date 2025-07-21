package kr.or.ddit.admin.common.codegroup.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.CmnCodeGroupMapper;
import kr.or.ddit.mapper.common.CmnCodeMapper;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.CmnCodeVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminCmnCodeGroupAjaxServiceImpl implements AdminCmnCodeGroupAjaxService{
	private final CmnCodeGroupMapper mapper;
	private final CmnCodeMapper cmapper;
	
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

	@Override
	public List<CmnCodeVO> readCmnCodeListByUc(String upperCodeNo) {
		return cmapper.selectCmnCodeListByUc(upperCodeNo);
	}
}
