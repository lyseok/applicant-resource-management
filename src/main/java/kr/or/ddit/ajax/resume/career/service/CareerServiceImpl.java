package kr.or.ddit.ajax.resume.career.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.CareerMapper;
import kr.or.ddit.vo.resume.CareerVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CareerServiceImpl implements CareerService {
	private final CareerMapper mapper;
	
	@Override
	public List<CareerVO> readCareerList() {
		return mapper.seletCareerList();
	}

	@Override
	public CareerVO readCareerDetail(String no) {
		return mapper.seletCareerDetail(no);
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

}
