package kr.or.ddit.company.recruitment.interviewquestion.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.exception.DataUpdateException;
import kr.or.ddit.mapper.recruitment.InterviewQuestionMapper;
import kr.or.ddit.vo.recruitment.InterviewQuestionVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterviewQuestionServiceImpl implements InterviewQuestionService {
	private final InterviewQuestionMapper mapper;
	
	@Override
	public void createInterviewQuestion(InterviewQuestionVO vo) {
	    // 1. 인터뷰 질문 PK로 조회 (값이 있으면 update, 없으면 insert)
	    InterviewQuestionVO dbQ = mapper.selectInterviewQuestionByNo(vo.getInterviewQuestionNo());

	    if (dbQ != null) {
	        // 기존 질문 있음 → update
	        int cnt = mapper.updateInterviewQuestion(vo);
	        if (cnt == 0) {
	            throw new DataUpdateException("면접 문항 정보 수정에 실패했습니다.");
	        }
	    } else {
	        // 없음 → 새로 insert
	        int cnt = mapper.insertInterviewQuestion(vo);
	        if (cnt == 0) {
	            throw new DataUpdateException("면접 문항 정보 등록에 실패했습니다.");
	        }
	    }
	}

}
