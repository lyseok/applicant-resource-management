package kr.or.ddit.company.recruitment.interview.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.recruitment.InterviewMapper;
import kr.or.ddit.vo.recruitment.InterviewVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyInterviewServiceImpl implements CompanyInterviewService{
	private final InterviewMapper mapper;
	
	@Override
	public List<InterviewVO> readInterviewList() {
		return mapper.selectInterviewList();
	}

	@Override
	public InterviewVO readInterview(String interviewNo) {
		return mapper.selectInterview(interviewNo);
	}

	@Override
	public int createInterview(InterviewVO vo) {
		return mapper.insertInterview(vo);
	}

	@Override
	public int modifyInterview(InterviewVO vo) {
		return mapper.updateInterview(vo);
	}

	@Override
	public int removeInterview(String inteviewNo) {
		return mapper.deleteInterview(inteviewNo);
	}
	
}
