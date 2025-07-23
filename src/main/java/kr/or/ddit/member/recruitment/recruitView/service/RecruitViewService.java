package kr.or.ddit.member.recruitment.recruitView.service;

import java.util.List;

import kr.or.ddit.dto.RecruitViewDTO;
import kr.or.ddit.vo.recruitment.RecruitViewVO;

public interface RecruitViewService {
	// 목록 조회
	public List<RecruitViewDTO> readRecruitViewList(String id);
	// 등록
	public int createtRecruitView(RecruitViewVO vo);
	// 삭제
	// 검색
	public List<RecruitViewDTO> readSearchRecruitViewList(String keyword);
}
