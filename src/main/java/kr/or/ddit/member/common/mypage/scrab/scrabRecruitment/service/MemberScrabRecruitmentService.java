package kr.or.ddit.member.common.mypage.scrab.scrabRecruitment.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.common.ScrabRecruitmentVO;

public interface MemberScrabRecruitmentService {

	public List<ScrabRecruitmentVO> readScrabRecruitmentList();

	public Optional<ScrabRecruitmentVO> searchScrabRecruitmentByPk(ScrabRecruitmentVO srecruit);

	public void createScrabRecruitment(ScrabRecruitmentVO srecruit);

	public void modifyScrabRecruitment(ScrabRecruitmentVO srecruit);

	public void removeScrabRecruitment(ScrabRecruitmentVO srecruit);
}
