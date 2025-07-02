package kr.or.ddit.member.common.mypage.subIntoruction.service;

import java.util.List;

import kr.or.ddit.vo.resume.SubIntroductionVO;

public interface SubIntroductionService {	
	public List<SubIntroductionVO> readSubIntroductionList();
	public SubIntroductionVO readSubIntroductionDetail(String no);
	public void createSubIntroduction(SubIntroductionVO vo);
	public void updateSubIntroduction(SubIntroductionVO vo);
	public void deleteSubIntroduction(String no);
}
