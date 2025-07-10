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
	public InterviewQuestionScoreVO readInterviewQuestionScore(String interviewScoreNo) {
		return mapper.selectInterviewQuestionScore(interviewScoreNo);
	}

	@Override
	public void createInterviewQuestionScoreList(List<InterviewQuestionScoreVO> list) {
		for(InterviewQuestionScoreVO vo : list) {
			if(1 > mapper.insertInterviewQuestionScore(vo)) {
				throw new InterviewQuestionScoreInsertException("면접 점수 등록 실패");
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
