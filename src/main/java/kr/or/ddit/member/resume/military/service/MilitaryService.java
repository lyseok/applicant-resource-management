package kr.or.ddit.member.resume.military.service;

import java.util.List;

import kr.or.ddit.vo.resume.MilitaryVO;

public interface MilitaryService {
	// 목록 조회
	public List<MilitaryVO> readMilitaryList(String no);
	// 단건 조회
	public MilitaryVO readMilitaryDetail(MilitaryVO vo);
	// 등록
	public void createMilitary(MilitaryVO vo);
	// 수정
	public void editMilitary(MilitaryVO vo);
	// 삭제
	public void removeMilitary(String no);
}
