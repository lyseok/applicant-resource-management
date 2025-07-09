package kr.or.ddit.company.recruitment.process.service;

import kr.or.ddit.vo.recruitment.RecruitProcessVO;

public interface RecruitProcessService {
	public void createRecruitProcess(RecruitProcessVO recruitProcess);
	public void modifyRecruitProcess(RecruitProcessVO recruitProcess);
	public void removeRecruitProcess(String recruitProcessNo);
}
