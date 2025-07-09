package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.MyLicenseVO;

@Mapper
public interface MyLicenseMapper {
	// 목록 조회
	public List<MyLicenseVO> selectMyLicenseList(String no);
	// 단건 조회
	public MyLicenseVO selectMyLicenseDetail(MyLicenseVO vo);
	// 등록
	public int insertMyLicense(MyLicenseVO vo);
	// 수정
	public int updateMyLicense(MyLicenseVO vo);
	// 삭제
	public int deleteMyLicense(String no);
}
