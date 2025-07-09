package kr.or.ddit.company.recruitment.interview.service;

import java.util.List;

import kr.or.ddit.company.recruitment.interview.dto.VideoInterviewSaveDTO;
import kr.or.ddit.vo.recruitment.InterviewVO;

public interface CompanyInterviewService {
	public List<InterviewVO> readInterviewList();
	public InterviewVO readInterview(String interviewNo);
	public int createInterview(VideoInterviewSaveDTO dto);
	public int modifyInterview(VideoInterviewSaveDTO dto);
	public int removeInterview(String inteviewNo);
}
