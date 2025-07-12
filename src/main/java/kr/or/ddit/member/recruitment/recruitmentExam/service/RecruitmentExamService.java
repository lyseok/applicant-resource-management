package kr.or.ddit.member.recruitment.recruitmentExam.service;

import java.util.List;

import kr.or.ddit.vo.recruitment.RecruitmentExamVO;

public interface RecruitmentExamService {
	public List<RecruitmentExamVO> readMyRecruitExams(String userId);
	
	public List<RecruitmentExamVO> readRecruitExamQuestionWithOptionByNo(String recruitExamNo);
	
}
