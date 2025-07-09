package kr.or.ddit.company.recruitment.notice.service;

import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

public interface RecruitService {
	public void createRecruitment(RecruitmentNoticeVO recruit);
	public void modifyRecruitment(RecruitmentNoticeVO recruit);
	public void deleteRecruitment(String recruimentNo);
}
