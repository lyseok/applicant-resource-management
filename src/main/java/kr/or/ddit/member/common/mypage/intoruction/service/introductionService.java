package kr.or.ddit.member.common.mypage.intoruction.service;

import java.util.List;

import kr.or.ddit.vo.resume.IntroductionVO;

// 자소서를 작성한 사람만 cud 할 수 있어야 함
public interface introductionService {
	public List<IntroductionVO> readIntroductionList(String userId);
	public IntroductionVO readIntroductionDetail(String no);
	public void createIntroduction(IntroductionVO vo);
	public void editIntroduction(IntroductionVO vo);
	public void removeIntroduction(IntroductionVO vo);
}
