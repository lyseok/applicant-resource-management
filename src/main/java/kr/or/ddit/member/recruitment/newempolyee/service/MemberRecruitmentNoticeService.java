package kr.or.ddit.member.recruitment.newempolyee.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

public interface MemberRecruitmentNoticeService {
	public List<RecruitmentNoticeVO> readRecruitmentNoticeList();
		// 전체
	
	public Optional<RecruitmentNoticeVO> realTimeRecruitment(String recruitmentNo);
		// 최신순
	
	 public List<RecruitmentNoticeVO> salaryRecruitment();
	 	// 연봉순
	
	 public RecruitmentNoticeVO selectliveRecruitmentDetail(String recruitmentNo);
	 	// 상세정보
	 
}
