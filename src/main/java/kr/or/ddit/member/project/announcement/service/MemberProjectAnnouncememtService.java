 package kr.or.ddit.member.project.announcement.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.project.PrjAnncBbsVO;

public interface MemberProjectAnnouncememtService {
	public Map<String, Object> prjAnncBbsList(Map<String, Object> params);
	public List<PrjAnncBbsVO> myPrjAnncBbsList();
	public List<PrjAnncBbsVO> myApplicantPrjAnncBbsList();
	public PrjAnncBbsVO readPrjAnncBbs(String prjAnncNo);
	public PrjAnncBbsVO readPrjAnncBbsApplicant(String prjAnncNo);
	public void createPrjAnncBbs(PrjAnncBbsVO vo);
	
}
