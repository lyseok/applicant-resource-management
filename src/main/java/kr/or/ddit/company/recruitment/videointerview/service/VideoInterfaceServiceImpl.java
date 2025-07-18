package kr.or.ddit.company.recruitment.videointerview.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.recruitment.VideoInterviewMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideoInterfaceServiceImpl implements VideoInterfaceService {
	private final VideoInterviewMapper mapper;
	
	@Override
	public String readCompanyURL(String interviewNo) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	Map<String, String> data = new HashMap<String, String>();
    	data.put("interviewNo", interviewNo);
    	data.put("userId", username);
		return mapper.getCompanyVideoURL(data);
	}

}
