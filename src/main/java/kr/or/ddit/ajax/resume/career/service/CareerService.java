package kr.or.ddit.ajax.resume.career.service;

import java.util.List;

import kr.or.ddit.vo.resume.CareerVO;

public interface CareerService {
	// 목록 조회
	public List<CareerVO> readCareerList();
	// 단건 조회
	public CareerVO readCareerDetail(String no);
	// 등록
	public void createCareer(CareerVO vo);
	// 수정
	public void editCareer(CareerVO vo);
	// 삭제
	public void removeCareer(String no);
}
