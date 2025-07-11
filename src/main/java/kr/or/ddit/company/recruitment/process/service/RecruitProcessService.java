package kr.or.ddit.company.recruitment.process.service;

import java.util.List;

import kr.or.ddit.company.recruitment.process.dto.ProcessEntry;
import kr.or.ddit.vo.recruitment.RecruitProcessVO;

public interface RecruitProcessService {
	public void createRecruitProcess(String recruitmentNo, List<ProcessEntry> entries);
	public void modifyRecruitProcess(RecruitProcessVO recruitProcess);
	public void removeRecruitProcess(String recruitProcessNo);
}
