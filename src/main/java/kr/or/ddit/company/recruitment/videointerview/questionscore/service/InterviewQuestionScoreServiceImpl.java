package kr.or.ddit.company.recruitment.videointerview.questionscore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.exception.DataUpdateException;
import kr.or.ddit.common.exception.InterviewQuestionScoreInsertException;
import kr.or.ddit.dto.InterviewQuestionScoreListDTO;
import kr.or.ddit.mapper.recruitment.ApplicantRecordMapper;
import kr.or.ddit.mapper.recruitment.InterviewQuestionScoreMapper;
import kr.or.ddit.vo.recruitment.InterviewQuestionScoreVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterviewQuestionScoreServiceImpl implements InterviewQuestionScoreService {
	private final InterviewQuestionScoreMapper mapper;
	private final ApplicantRecordMapper applicantRecordMapper;
	
	@Override
	public List<InterviewQuestionScoreVO> readInterviewQuestionScoreList() {
		return mapper.selectInterviewQuestionScoreList();
	}

	@Override
	public InterviewQuestionScoreVO readInterviewQuestionScore(InterviewQuestionScoreVO vo) {
		return mapper.selectInterviewQuestionScore(vo);
	}

	@Override
	public void createInterviewQuestionScoreList(InterviewQuestionScoreListDTO dto) {
		for(InterviewQuestionScoreVO vo : dto.getInterviewQuestionScoreList()) {
			// 1. PK가 아니라면, interviewScoreNo/interviewQuestionNo 조합 등으로 select
	        InterviewQuestionScoreVO existing = mapper.selectInterviewQuestionScore(vo);
	        if (existing != null) {
	            // 이미 있으면 update
	            int updated = mapper.updateInterviewQuestionScore(vo);
	            // 응시여부 업데이트
	            if (updated < 1) {
	                throw new InterviewQuestionScoreInsertException("면접 점수 수정 실패");
	            }
	        } else {
	            // 없으면 insert
	            int inserted = mapper.insertInterviewQuestionScore(vo);
	            if (inserted < 1) {
	                throw new InterviewQuestionScoreInsertException("면접 점수 등록 실패");
	            }
	        }
	        
	        if (1 > applicantRecordMapper.updateApplication(dto.getApplicantId())) {
	        	throw new DataUpdateException("단계별 응시 여부 변경에 실패했습니다");
	        }
		}
	}

	@Override
	public void modifyInterviewQuestionScore(InterviewQuestionScoreVO vo) {
		
	}

	@Override
	public void removeInterviewQuestionScore(String interviewScoreNo) {
		
	}

}
