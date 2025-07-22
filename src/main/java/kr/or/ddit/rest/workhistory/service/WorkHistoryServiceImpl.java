package kr.or.ddit.rest.workhistory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.project.WorkHistoryMapper;
import kr.or.ddit.vo.project.WorkHistoryVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkHistoryServiceImpl implements WorkHistoryService {
	private final WorkHistoryMapper workHistoryMapper;

    public List<WorkHistoryVO> getWorkHistoryByPrjNo(String prjNo) {
        return workHistoryMapper.selectWorkHistoryByPrjNo(prjNo);
    }
    
    public WorkHistoryVO insertWorkHistory(WorkHistoryVO workHistory) {
        workHistoryMapper.insertWorkHistory(workHistory);
        return workHistory;
    }
}
