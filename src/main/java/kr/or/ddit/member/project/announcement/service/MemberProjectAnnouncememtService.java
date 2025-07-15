package kr.or.ddit.member.project.announcement.service;

import java.util.List;

import kr.or.ddit.vo.project.PrjAnncBbsVO;

public interface MemberProjectAnnouncememtService {
	public List<PrjAnncBbsVO> prjAnncBbsList();
	public PrjAnncBbsVO readPrjAnncBbs(String prjAnncNo);
	public void createPrjAnncBbs(PrjAnncBbsVO vo);
}
