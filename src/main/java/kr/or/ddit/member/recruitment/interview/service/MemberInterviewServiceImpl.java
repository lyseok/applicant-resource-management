package kr.or.ddit.member.recruitment.interview.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.recruitment.InterviewMapper;
import kr.or.ddit.vo.recruitment.InterviewVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberInterviewServiceImpl implements MemberInterviewService {
	private final InterviewMapper mapper;
	
	@Override
	public List<Map<String, Object>> readMyInterviewList() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String userId = authentication.getName();
		return mapper.selectMyInterviewList(userId);
	}

	@Override
	public List<Map<String, Object>> readInterviewDetail(String no) {
		return mapper.selectMemberInterviewDetail(no);
	}

}
