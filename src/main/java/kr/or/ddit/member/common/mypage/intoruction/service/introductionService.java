package kr.or.ddit.member.common.mypage.intoruction.service;

import java.util.List;

import kr.or.ddit.vo.resume.IntroductionVO;

public interface introductionService {	
	public List<IntroductionVO> readIntroductionList(String userId);
}
