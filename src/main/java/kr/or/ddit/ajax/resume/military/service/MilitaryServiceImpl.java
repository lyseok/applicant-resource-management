package kr.or.ddit.ajax.resume.military.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.MilitaryMapper;
import kr.or.ddit.vo.resume.MilitaryVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MilitaryServiceImpl implements MilitaryService {
	private final MilitaryMapper mapper;
	
	@Override
	public List<MilitaryVO> readMilitaryList() {
		return mapper.selectMilitaryList();
	}

	@Override
	public MilitaryVO readMilitaryDetail(String no) {
		return mapper.selectMilitaryDetail(no);
	}

	@Override
	public void createMilitary(MilitaryVO vo) {
		mapper.insertMilitary(vo);
	}

	@Override
	public void editMilitary(MilitaryVO vo) {
		mapper.updateMilitary(vo);
	}

	@Override
	public void removeMilitary(String no) {
		mapper.deleteMilitary(no);
	}

}
