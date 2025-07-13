package kr.or.ddit.member.recruitment.recruitmentExam.service;

import java.util.List;

import kr.or.ddit.dto.MyRecruitExamDTO;
import kr.or.ddit.dto.RecruitmentExamAnswerDTO;
import kr.or.ddit.vo.recruitment.RecruitmentExamScoreResultVO;
import kr.or.ddit.vo.recruitment.RecruitmentExamVO;

public interface RecruitmentExamService {
	public List<MyRecruitExamDTO> readMyRecruitExams(String userId);
	
	public RecruitmentExamVO readRecruitExamQuestionWithOptionByNo(String recruitExamNo);

	public RecruitmentExamScoreResultVO gradeAndSave(String userId, List<RecruitmentExamAnswerDTO> answers);
}
