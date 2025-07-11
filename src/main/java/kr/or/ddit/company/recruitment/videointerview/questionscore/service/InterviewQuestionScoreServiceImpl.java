package kr.or.ddit.company.recruitment.videointerview.questionscore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.exception.InterviewQuestionScoreInsertException;
import kr.or.ddit.mapper.recruitment.InterviewQuestionScoreMapper;
import kr.or.ddit.vo.recruitment.InterviewQuestionScoreVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterviewQuestionScoreServiceImpl implements InterviewQuestionScoreService {
	private final InterviewQuestionScoreMapper mapper;
	
	@Override
	public List<InterviewQuestionScoreVO> readInterviewQuestionScoreList() {
		return mapper.selectInterviewQuestionScoreList();
	}

	@Override
	public InterviewQuestionScoreVO readInterviewQuestionScore(InterviewQuestionScoreVO vo) {
		return mapper.selectInterviewQuestionScore(vo);
	}

	@Override
	public void createInterviewQuestionScoreList(List<InterviewQuestionScoreVO> list) {
		for(InterviewQuestionScoreVO vo : list) {
			// 1. PK가 아니라면, interviewScoreNo/interviewQuestionNo 조합 등으로 select
	        InterviewQuestionScoreVO existing = mapper.selectInterviewQuestionScore(vo);
	        if (existing != null) {
	            // 이미 있으면 update
	            int updated = mapper.updateInterviewQuestionScore(vo);
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
		}
	}

	@Override
	public void modifyInterviewQuestionScore(InterviewQuestionScoreVO vo) {
		
	}

	@Override
	public void removeInterviewQuestionScore(String interviewScoreNo) {
		
	}

}
