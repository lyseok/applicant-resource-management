package kr.or.ddit.company.codegroup.service;

import java.util.List;

import kr.or.ddit.vo.common.CmnCodeGroupVO;


public interface CompanyCmnCodeGroupAjaxService {
	public List<CmnCodeGroupVO> readCmnCodeGroupList();
	public CmnCodeGroupVO readCmnCodeGroupByPk(String codeGroupN) ;
}
