package kr.or.ddit.member.recruitment.recruitView.service;

import java.util.List;

import kr.or.ddit.dto.RecruitViewDTO;
import kr.or.ddit.vo.recruitment.RecruitViewVO;

public interface RecruitViewService {
	public List<RecruitViewDTO> readRecruitViewList(String id);
	public int createtRecruitView(RecruitViewVO vo);
}
