package kr.or.ddit.member.common.mypage.scrab.scrabRecruitment.service;

import java.util.List;

import kr.or.ddit.vo.common.ScrabRecruitmentVO;

public interface MemberScrabRecruitmentService {

	public List<ScrabRecruitmentVO> readScrabRecruitmentList();

	public List<ScrabRecruitmentVO> readMyScrabRecruitmentList();

	public void createScrabRecruitment(String srecruit);

	public void removeScrabRecruitment(String srecruit);
}
