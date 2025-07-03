package kr.or.ddit.recruitment.live.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

public interface LiveRecruitmentService {
	public List<RecruitmentNoticeVO> readRecruitmentList();
		// 전체
	
	public Optional<RecruitmentNoticeVO> readRecruitmentNotice(String recruitmentNo);
		// 최신순
	
	 public List<RecruitmentNoticeVO> salaryRecruitment(String recruitmentNo);
	 	// 연봉순
	
	 public RecruitmentNoticeVO selectliveRecruitmentDetail(String recruitmentNo);
	 	// 상세정보

}
