package kr.or.ddit.ajax.resume.specialty.service;

import java.util.List;

import kr.or.ddit.vo.resume.SpecialtyVO;

public interface SpecialtyService {
	// 목록 조회
	public List<SpecialtyVO> readSpecialtyList();
	// 단건 조회
	public SpecialtyVO readSpecialtyDetail(SpecialtyVO vo);
	// 등록
	public void createSpecialty(SpecialtyVO vo);
	// 수정
	public void editSpecialty(SpecialtyVO vo);
	// 삭제
	public void removeSpecialty(SpecialtyVO vo);
}
