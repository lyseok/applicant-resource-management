package kr.or.ddit.member.resume.career.service;

import java.util.List;

import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.resume.CareerVO;
import kr.or.ddit.vo.resume.ResumeVO;

public interface CareerService {
	// 목록 조회
	public List<CareerVO> readCareerList(String no);
	// 단건 조회
	public CareerVO readCareerDetail(CareerVO vo);
	// 등록
	public void createCareer(CareerVO vo);
	// 수정
	public void editCareer(CareerVO vo);
	// 삭제
	public void removeCareer(String no);
	
	// 컴퍼니 id, name 조회
	public List<CompanyVO> readCompanyInfoWithCareer();
}
