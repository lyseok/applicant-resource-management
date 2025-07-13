package kr.or.ddit.member.recruitment.recruitmentExam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import kr.or.ddit.common.exception.AlreadyTakenExamException;
import kr.or.ddit.dto.MyRecruitExamDTO;
import kr.or.ddit.dto.RecruitmentExamAnswerDTO;
import kr.or.ddit.mapper.recruitment.ApplicantAnswerMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentExamMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentExamOptionMapper;
import kr.or.ddit.vo.recruitment.ApplicantAnswerVO;
import kr.or.ddit.vo.recruitment.RecruitmentExamScoreResultVO;
import kr.or.ddit.vo.recruitment.RecruitmentExamVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitmentExamServiceImpl implements RecruitmentExamService {
	private final RecruitmentExamMapper recruitmentExamMapper;
	private final RecruitmentExamOptionMapper recruitmentExamOptionMapper;
	private final ApplicantAnswerMapper applicantAnswerMapper;

	@Override
	public List<MyRecruitExamDTO> readMyRecruitExams(String userId) {
		return recruitmentExamMapper.selectMyRecruitExams(userId);
	}

	@Override
	public RecruitmentExamVO readRecruitExamQuestionWithOptionByNo(String recruitExamNo) {
		return recruitmentExamMapper.selectRecruitExamQuestionWithOptionByNo(recruitExamNo);
	}

	@Override
	public RecruitmentExamScoreResultVO gradeAndSave(String userId,@RequestBody List<RecruitmentExamAnswerDTO> answers) {
		String applicantId    = answers.get(0).getApplicantId();
		String recruitExamNo = answers.get(0).getRecruitExamNo();
		int cutline = recruitmentExamMapper.selectCutlineByExamNo(recruitExamNo);
		RecruitmentExamScoreResultVO result = new RecruitmentExamScoreResultVO();
		int taken = applicantAnswerMapper.countByUserAndExam(applicantId, recruitExamNo);
		
		
		if(taken > 0) {
			throw new AlreadyTakenExamException("이미 응시를 완료했습니다.");
		}
		
		int total = 0;
		for(RecruitmentExamAnswerDTO dto : answers) {
			String selectOption = dto.getSelectedOptionNo();
			int score = 0;
			if(!"X".equals(selectOption)) {
				boolean correct = "Y".equals(
					recruitmentExamOptionMapper.selectOptionCorrectYn(selectOption)
				);
				score = correct ? 10 : 0;
			}
			total += score;
			
			ApplicantAnswerVO vo = new ApplicantAnswerVO();
			vo.setApplicantId(dto.getApplicantId());
			vo.setRecruitExamNo( dto.getRecruitExamNo() ); 
			vo.setRecruitQuestionsNo(dto.getRecruitExamQuestNo());
			vo.setApplicantOptionNo(dto.getSelectedOptionNo());
			vo.setApplicantScore(score);
			applicantAnswerMapper.insertApplicantAnswer(vo);
			
		}
		result.setExamTotalScore(total);
		result.setExamcutlineScore(cutline);
		result.setExamPass( total >= cutline);
		return result;
	}

}
