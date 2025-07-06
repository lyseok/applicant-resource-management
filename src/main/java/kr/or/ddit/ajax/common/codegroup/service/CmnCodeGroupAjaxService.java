package kr.or.ddit.ajax.common.codegroup.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.CmnCodeGroupMapper;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import lombok.RequiredArgsConstructor;


public interface CmnCodeGroupAjaxService {
	public List<CmnCodeGroupVO> readCmnCodeGroupList();
	public CmnCodeGroupVO readCmnCodeGroupByPk(String codeGroupN) ;
	public int createCmnCodeGroup(CmnCodeGroupVO cmnCodeGroup);
	public int modifyCmnCodeGroup(CmnCodeGroupVO cmnCodeGroup);
	public int modifyUseYn(CmnCodeGroupVO cmnCodeGroup);
	public int removeCmnCodeGroup(String codeGroupNo);
}
