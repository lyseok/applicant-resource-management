package kr.or.ddit.company.recruitment.interviewscore.service;


import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.exception.DataInsertException;
import kr.or.ddit.mapper.recruitment.InterviewScoreMapper;
import kr.or.ddit.vo.recruitment.InterviewScoreVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterviewScoreServiceImpl implements InterviewScoreService {
	private final InterviewScoreMapper mapper;
	@Override
	public void createInterviewScore(InterviewScoreVO interviewScore) {
		
		
	}
	@Override
	public void createInterviewScoreList(List<InterviewScoreVO> interviewScoreList) {
		for(InterviewScoreVO vo : interviewScoreList) {
			getOrCreateInterviewScoreNo(vo);
		}
	}
	
	public void getOrCreateInterviewScoreNo(InterviewScoreVO vo) {
	    // 1. SELECT 먼저
	    String interviewScoreNo = mapper.selectInterviewScoreNo(vo);

	    // 2. 없으면 INSERT
	    if (interviewScoreNo == null) {
			if(1 > mapper.insertInterviewScore(vo)) {
				throw new DataInsertException("데이터 삽입 실패");
			}
	    } else {
	    	vo.setInterviewScoreNo(interviewScoreNo);
	    }
	}
}
