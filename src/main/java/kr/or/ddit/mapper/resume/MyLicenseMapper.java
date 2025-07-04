package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.MyLicenseVO;

@Mapper
public interface MyLicenseMapper {
	// 목록 조회
	public List<MyLicenseVO> selectMyLicenseList();
	// 단건 조회
	public MyLicenseVO selectMyLicenseDetail(String MyLicenseNo);
	// 등록
	public int insertMyLicense(MyLicenseVO MyLicenseVO);
	// 수정
	public int updateMyLicense(MyLicenseVO MyLicenseVO);
	// 삭제
	public int deleteMyLicense(String MyLicenseNo);
}
