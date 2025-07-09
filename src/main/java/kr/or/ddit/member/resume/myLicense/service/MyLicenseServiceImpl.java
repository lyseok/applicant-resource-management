package kr.or.ddit.member.resume.myLicense.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.MyLicenseMapper;
import kr.or.ddit.vo.resume.MyLicenseVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyLicenseServiceImpl implements MyLicenseService {
	private final MyLicenseMapper mapper;
	
	@Override
	public List<MyLicenseVO> readMyLicenseList(String no) {
		return mapper.selectMyLicenseList(no);
	}

	@Override
	public MyLicenseVO readMyLicenseDetail(MyLicenseVO vo) {
		return mapper.selectMyLicenseDetail(vo);
	}

	@Override
	public void createMyLicense(MyLicenseVO vo) {
		mapper.insertMyLicense(vo);
	}

	@Override
	public void editMyLicense(MyLicenseVO vo) {
		mapper.updateMyLicense(vo);
	}

	@Override
	public void removeMyLicense(String no) {
		mapper.deleteMyLicense(no);
	}

}
