package kr.or.ddit.member.recruitment.interview.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.recruitment.InterviewVO;

public interface MemberInterviewService {
	public List<Map<String, Object>> readMyInterviewList();
	public List<Map<String, Object>> readInterviewDetail(String no);

}
