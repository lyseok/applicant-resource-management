package kr.or.ddit.ajax.resume.education.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.EducationMapper;
import kr.or.ddit.vo.resume.EducationVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {
	private final EducationMapper mapper;
	
	@Override
	public List<EducationVO> readEducationList() {
		return mapper.selectEducationList();
	}

	@Override
	public EducationVO readEducationDetail(EducationVO vo) {
		return mapper.selectEducationDetail(vo);
	}

	@Override
	public void createEducation(EducationVO vo) {
		mapper.insertEducation(vo);
	}

	@Override
	public void editEducation(EducationVO vo) {
		mapper.updateEducation(vo);
	}

	@Override
	public void removeEducation(EducationVO vo) {
		mapper.deleteEducation(vo);
	}

}
