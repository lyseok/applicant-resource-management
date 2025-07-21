package kr.or.ddit.rest.workhistory.service;

import java.util.List;

import kr.or.ddit.vo.project.WorkHistoryVO;

public interface WorkHistoryService {
	public List<WorkHistoryVO> getWorkHistoryByPrjNo(String prjNo);
	public WorkHistoryVO insertWorkHistory(WorkHistoryVO workHistory);
}
