package kr.or.ddit.company.recruitment.notice.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.common.UsersVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

public interface RecruitService {
	public List<RecruitmentNoticeVO> readRecruitList();
	public List<Map<String, Object>> readMyNotice();
	public RecruitmentNoticeVO readRecruitNotice(String recruitNo);
	public void createRecruitment(RecruitmentNoticeVO recruit);
	public void modifyRecruitment(RecruitmentNoticeVO recruit);
	public void deleteRecruitment(String recruimentNo);
	public void setDeadLine(String recruitmentNo);
	
	public UsersVO searchUser();
}
