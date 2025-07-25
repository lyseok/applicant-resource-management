package kr.or.ddit.member.common.induclasscode.service;

import java.util.List;

import kr.or.ddit.vo.common.InduClassCodeVO;

public interface MemberInduClassCodeAjaxService {
	public List<InduClassCodeVO> readInduClassCodeList();
	public InduClassCodeVO readInduClassCodeBuPk(String no);
}
