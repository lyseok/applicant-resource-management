package kr.or.ddit.member.common.mypage.scrab.scrabRecruitment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.ScrabRecruitmentMapper;
import kr.or.ddit.vo.common.ScrabRecruitmentVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberScrabRecruitmentServiceImpl implements MemberScrabRecruitmentService {

	private final ScrabRecruitmentMapper mapper;
	
	@Override
	public List<ScrabRecruitmentVO> readScrabRecruitmentList() {
		return mapper.selectScrabRecruitmentList();
	}

	@Override
	public Optional<ScrabRecruitmentVO> searchScrabRecruitmentByPk(ScrabRecruitmentVO srecruit) {
		return Optional.ofNullable(mapper.selectScrabRecruitmentByPk(srecruit));
	}

	@Override
	public void createScrabRecruitment(ScrabRecruitmentVO srecruit) {
		mapper.insertScrabRecruitment(srecruit);
	}

	@Override
	public void modifyScrabRecruitment(ScrabRecruitmentVO srecruit) {
		mapper.updateScrabRecruitment(srecruit);
	}

	@Override
	public void removeScrabRecruitment(ScrabRecruitmentVO srecruit) {
		mapper.deleteScrabRecruitment(srecruit);
	}

}
