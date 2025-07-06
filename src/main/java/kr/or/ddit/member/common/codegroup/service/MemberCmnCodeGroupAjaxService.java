package kr.or.ddit.member.common.codegroup.service;

import java.util.List;

import kr.or.ddit.vo.common.CmnCodeGroupVO;


public interface MemberCmnCodeGroupAjaxService {
	public List<CmnCodeGroupVO> readCmnCodeGroupList();
	public CmnCodeGroupVO readCmnCodeGroupByPk(String codeGroupN) ;
}
