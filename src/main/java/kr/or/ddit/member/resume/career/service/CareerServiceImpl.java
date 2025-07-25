package kr.or.ddit.member.resume.career.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.resume.CareerMapper;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.resume.CareerVO;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CareerServiceImpl implements CareerService {
	private final CareerMapper mapper;
	private final CompanyMapper Companymapper;
	
	@Override
	public List<CareerVO> readCareerList(String no) {
		return mapper.selectCareerList(no);
	}

	@Override
	public CareerVO readCareerDetail(CareerVO vo) {
		return mapper.selectCareerDetail(vo);
	}

	@Override
	public void createCareer(CareerVO vo) {
		mapper.insertCareer(vo);
	}

	@Override
	public void editCareer(CareerVO vo) {
		mapper.updateCareer(vo);
	}

	@Override
	public void removeCareer(String no) {
		mapper.deleteCareer(no);
	}

	@Override
	public List<CompanyVO> readCompanyInfoWithCareerList() {
		return Companymapper.selectCompanyWithResumeCareer();
	}

	@Override
	public CompanyVO readCompanyInfoWithCareer(String id) {
		return Companymapper.selectCompanyById(id);
	}

}
