package kr.or.ddit.member.recruitment.videointerview.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.exception.DataUpdateException;
import kr.or.ddit.common.exception.VideoInterviewUrlNotFoundException;
import kr.or.ddit.mapper.recruitment.ApplicantRecordMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberVideoInterviewServiceImpl implements MemberVideoInterviewService {
	private final ApplicantRecordMapper mapper;
	
	public String memberVideoInterviewURL(String no) {
	    String url = mapper.selectMemberVideoURL(no);

	    // url이 null이거나 빈 문자열이면 예외 던지기
	    if (url == null || url.trim().isEmpty()) {
	        throw new VideoInterviewUrlNotFoundException("화상면접 URL이 존재하지 않습니다.");
	    }

	    if(1 > mapper.updateApplication(no)) {
	    	throw new DataUpdateException("단계별 응시 여부 변경에 실패했습니다");
	    }
	    return url;
	}
}
