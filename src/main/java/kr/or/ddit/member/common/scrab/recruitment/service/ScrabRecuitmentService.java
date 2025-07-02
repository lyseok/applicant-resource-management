package kr.or.ddit.member.common.scrab.recruitment.service;

import java.util.List;

import kr.or.ddit.vo.common.ScrabRecuitmentVO;

public interface ScrabRecuitmentService {
	
	public List<ScrabRecuitmentVO> readScrabRecuitmentList();
	
	public void insertScrabRecuitment(ScrabRecuitmentVO ScrabRecuitment);
}
