package kr.or.ddit.ajax.resume.award.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.AwardMapper;
import kr.or.ddit.vo.resume.AwardVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AwardServiceImpl implements AwardService {
	private final AwardMapper mapper;
	
	@Override
	public List<AwardVO> readAwardList() {
		return mapper.selectAwardList();
	}

	@Override
	public AwardVO readAwardDetail(String no) {
		return mapper.selectAwardDetail(no);
	}

	@Override
	public void createAward(AwardVO vo) {
		mapper.insertAward(vo);
	}

	@Override
	public void editAward(AwardVO vo) {
		mapper.updateAward(vo);
	}

	@Override
	public void removeAward(String no) {
		mapper.deleteAward(no);
	}

}
