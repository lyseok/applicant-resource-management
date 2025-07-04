package kr.or.ddit.ajax.resume.support.service;

import java.util.List;

import kr.or.ddit.vo.resume.SupportVO;

public interface SupportService {
	// 목록 조회
	public List<SupportVO> readSupportList();
	// 단건 조회
	public SupportVO readSupportDetail(String no);
	// 등록
	public void createSupport(SupportVO vo);
	// 수정
	public void editSupport(SupportVO vo);
	// 삭제
	public void removeSupport(String no);
}
