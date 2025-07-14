package kr.or.ddit.member.recruitment.recruitmentExam.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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


	@Transactional
	@Override
	public RecruitmentExamScoreResultVO gradeAndSave(String userId,@RequestBody List<RecruitmentExamAnswerDTO> answers) {
		String applicantId    = answers.get(0).getApplicantId();
		String recruitExamNo = answers.get(0).getRecruitExamNo();
		int cutline = recruitmentExamMapper.selectCutlineByExamNo(recruitExamNo);
		int taken = applicantAnswerMapper.countByUserAndExam(applicantId, recruitExamNo);
		
		
		if(taken > 0) {
			throw new AlreadyTakenExamException("이미 응시를 완료했습니다.");
		}
		
		int questionCount = answers.size();
		int scorePerQuestion = 100 / questionCount;
		int totalScore = 0;
		
		for(RecruitmentExamAnswerDTO dto : answers) {
			String selectedOption = dto.getSelectedOptionNo();
			int score = 0;
			if(!"X".equals(selectedOption)) {
				boolean correct = "Y".equals(
					recruitmentExamOptionMapper.selectOptionCorrectYn(selectedOption)
				);
				score = correct ? scorePerQuestion : 0;
			}
			totalScore += score;
			ApplicantAnswerVO vo = new ApplicantAnswerVO();
			vo.setApplicantId(dto.getApplicantId());
			vo.setRecruitExamNo( dto.getRecruitExamNo() ); 
			vo.setRecruitQuestionsNo(dto.getRecruitExamQuestNo());
			vo.setApplicantOptionNo(dto.getSelectedOptionNo());
			vo.setApplicantScore(score);
			applicantAnswerMapper.insertApplicantAnswer(vo);
			
		}
		
		editStepApplicationYN(recruitExamNo, applicantId);
		
		RecruitmentExamScoreResultVO result = new RecruitmentExamScoreResultVO();
		result.setExamTotalScore(totalScore);
		result.setExamcutlineScore(cutline);
		result.setExamPass( totalScore >= cutline);
		return result;
	}

	@Override
	public RecruitmentExamScoreResultVO readResultByExamAndUser(String applicantId, String recruitExamNo) {
		return applicantAnswerMapper.selectResultByExamAndUser(applicantId, recruitExamNo);
	}

	@Override
	public boolean editStepApplicationYN(String recruitExamNo, String applicantId) {
		Map<String, Object> params = new HashMap<>();
		params.put("recruitExamNo", recruitExamNo);
		params.put("applicantId", applicantId);
		
		int result = recruitmentExamMapper.updateStepApplicationYN(params);
		return result > 0;
		
	}
	
	
	

}
