package kr.or.ddit.admin.common.codegroup.service;

import java.util.List;

import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.CmnCodeVO;


public interface AdminCmnCodeGroupAjaxService {
	public List<CmnCodeGroupVO> readCmnCodeGroupList();
	public CmnCodeGroupVO readCmnCodeGroupByPk(String codeGroupNo);
	public List<CmnCodeVO> readCmnCodeListByUc(String upperCodeNo);
	public int createCmnCodeGroup(CmnCodeGroupVO cmnCodeGroup);
	public int modifyCmnCodeGroup(CmnCodeGroupVO cmnCodeGroup);
	public int modifyUseYn(CmnCodeGroupVO cmnCodeGroup);
	public int removeCmnCodeGroup(String codeGroupNo);
}
