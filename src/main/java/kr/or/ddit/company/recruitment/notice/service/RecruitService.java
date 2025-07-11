package kr.or.ddit.company.recruitment.notice.service;

import java.util.List;

import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

public interface RecruitService {
	public List<RecruitmentNoticeVO> readRecruitList();
	public RecruitmentNoticeVO readRecruitNotice(String recruitNo);
	public void createRecruitment(RecruitmentNoticeVO recruit);
	public void modifyRecruitment(RecruitmentNoticeVO recruit);
	public void deleteRecruitment(String recruimentNo);
}
