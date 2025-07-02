package kr.or.ddit.recruitment.live.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

public interface LiveRecruitmentService {
	public List<RecruitmentNoticeVO> readRecruitmentList();

	public Optional<RecruitmentNoticeVO> readRecruitmentNotice(String recruitmentNo);
	
	 public Optional<RecruitmentNoticeVO> salaryRecruitment(String recruitmentNo);
	 
	 public RecruitmentNoticeVO selectliveRecruitmentDetail(String recruitmentNo);


}
