package kr.or.ddit.ajax.resume.education.service;

import java.util.List;

import kr.or.ddit.vo.resume.EducationVO;

public interface EducationService {
	// 목록 조회
	public List<EducationVO> readEducationList();
	// 단건 조회
	public EducationVO readEducationDetail(EducationVO vo);
	// 등록
	public void createEducation(EducationVO vo);
	// 수정
	public void editEducation(EducationVO vo);
	// 삭제
	public void removeEducation(EducationVO vo);
}
