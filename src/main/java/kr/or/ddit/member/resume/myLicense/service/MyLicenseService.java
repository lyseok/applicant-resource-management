package kr.or.ddit.member.resume.myLicense.service;

import java.util.List;

import kr.or.ddit.vo.resume.MyLicenseVO;

public interface MyLicenseService {
	// 목록 조회
	public List<MyLicenseVO> readMyLicenseList(String no);
	// 단건 조회
	public MyLicenseVO readMyLicenseDetail(MyLicenseVO vo);
	// 등록
	public void createMyLicense(MyLicenseVO vo);
	// 수정
	public void editMyLicense(MyLicenseVO vo);
	// 삭제
	public void removeMyLicense(String no);
}
