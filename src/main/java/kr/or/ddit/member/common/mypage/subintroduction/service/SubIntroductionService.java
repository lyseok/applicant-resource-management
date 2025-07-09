package kr.or.ddit.member.common.mypage.subintroduction.service;

import java.util.List;

import kr.or.ddit.vo.resume.SubIntroductionVO;

public interface SubIntroductionService {	
	public List<SubIntroductionVO> readSubIntroductionList(String vo);
	public SubIntroductionVO readSubIntroductionDetail(SubIntroductionVO vo);
	public void createSubIntroduction(SubIntroductionVO vo);
	public void updateSubIntroduction(SubIntroductionVO vo);
	public void deleteSubIntroduction(String no);
}
