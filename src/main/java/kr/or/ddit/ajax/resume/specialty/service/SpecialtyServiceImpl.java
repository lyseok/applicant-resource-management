package kr.or.ddit.ajax.resume.specialty.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.SpecialtyMapper;
import kr.or.ddit.vo.resume.SpecialtyVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {
	private final SpecialtyMapper mapper;
	
	@Override
	public List<SpecialtyVO> readSpecialtyList() {
		return mapper.selectSpecialtyList();
	}

	@Override
	public SpecialtyVO readSpecialtyDetail(SpecialtyVO vo) {
		return mapper.selectSpecialtyDetail(vo);
	}

	@Override
	public void createSpecialty(SpecialtyVO vo) {
		mapper.insertSpecialty(vo);
	}

	@Override
	public void editSpecialty(SpecialtyVO vo) {
		mapper.updateSpecialty(vo);
	}

	@Override
	public void removeSpecialty(SpecialtyVO vo) {
		mapper.deleteSpecialty(vo);
	}

}
