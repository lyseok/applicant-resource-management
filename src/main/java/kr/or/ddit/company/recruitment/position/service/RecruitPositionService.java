package kr.or.ddit.company.recruitment.position.service;

import kr.or.ddit.vo.recruitment.RecruitmentPositionVO;

public interface RecruitPositionService {
	public void createRecruitPosition(RecruitmentPositionVO recruitPosition);
	public void modifyRecruitPosition(RecruitmentPositionVO recruitPosition);
	public void deleteRecruitPosition(String RecruitPositionNo);
}
