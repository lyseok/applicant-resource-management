package kr.or.ddit.ajax.resume.award.service;

import java.util.List;

import kr.or.ddit.vo.resume.AwardVO;

public interface AwardService {
	// 목록 조회
	public List<AwardVO> readAwardList();
	// 단건 조회
	public AwardVO readAwardDetail(String no);
	// 등록
	public void createAward(AwardVO vo);
	// 수정
	public void editAward(AwardVO vo);
	// 삭제
	public void removeAward(String no);
}
