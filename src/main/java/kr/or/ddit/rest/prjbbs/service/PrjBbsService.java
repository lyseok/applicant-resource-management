package kr.or.ddit.rest.prjbbs.service;

import java.util.List;

import kr.or.ddit.vo.project.PrjBbsVO;

public interface PrjBbsService {
	public List<PrjBbsVO> getPrjBbsList(String prjNo, int page, int size);
	public PrjBbsVO getPrjBbsWithComments(String prjPostNo);
	PrjBbsVO createPrjBbs(PrjBbsVO prjBbs);
	public PrjBbsVO updatePrjBbs(PrjBbsVO prjBbs);
	public int deletePrjBbs(String prjPostNo);
}
