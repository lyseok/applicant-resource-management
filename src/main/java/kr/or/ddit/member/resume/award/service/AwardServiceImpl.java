package kr.or.ddit.member.resume.award.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.AwardMapper;
import kr.or.ddit.vo.resume.AwardVO;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AwardServiceImpl implements AwardService {
	private final AwardMapper mapper;
	
	@Override
	public List<AwardVO> readAwardList(String no) {
		return mapper.selectAwardList(no);
	}

	@Override
	public AwardVO readAwardDetail(AwardVO vo) {
		return mapper.selectAwardDetail(vo);
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
