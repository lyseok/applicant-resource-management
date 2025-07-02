package kr.or.ddit.recruitment.live.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

public interface LiveRecruitmentService {
	public List<RecruitmentNoticeVO> readRecruitmentList();

	Optional<RecruitmentNoticeVO> readRecruitmentNotice(String recruitmentNo);
	
	
}
