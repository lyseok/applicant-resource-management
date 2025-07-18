package kr.or.ddit.member.recruitment.videointerview.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.exception.DataUpdateException;
import kr.or.ddit.common.exception.VideoInterviewUrlNotFoundException;
import kr.or.ddit.mapper.recruitment.ApplicantRecordMapper;
import kr.or.ddit.vo.recruitment.ApplicantRecordVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberVideoInterviewServiceImpl implements MemberVideoInterviewService {
	private final ApplicantRecordMapper mapper;
	
	@Override
	public String memberVideoInterviewURL(String no) {
	    ApplicantRecordVO record = mapper.selectApplicantRecord(no);
	    if (record == null) {
	        throw new VideoInterviewUrlNotFoundException("지원자 정보가 없습니다.");
	    }

	    String url = record.getInterviewUrl();
	    String stepYn = record.getStepApplicationYn();
	    String evalStartStr = record.getEvaluationStartTime();

	    // URL 체크
	    if (url == null || url.trim().isEmpty()) {
	        throw new VideoInterviewUrlNotFoundException("화상면접 URL이 존재하지 않습니다.");
	    }

	    // STEP_APPLICATION_YN 'N' 또는 NULL만 허용
	    if (stepYn != null && !"N".equals(stepYn)) {
	        throw new VideoInterviewUrlNotFoundException("이미 응시한 면접입니다.");
	    }

	    // String → LocalDateTime 변환 (형식에 따라 수정 필요)
	    if (evalStartStr == null || evalStartStr.trim().isEmpty()) {
	        throw new VideoInterviewUrlNotFoundException("면접 시작 시간이 등록되어 있지 않습니다.");
	    }
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // DB 포맷 맞게 수정
	    LocalDateTime evalStart = LocalDateTime.parse(evalStartStr, formatter);

	    // 10분 전 체크
	    long nowMillis = System.currentTimeMillis();
	    long evalStartMillis = evalStart.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	    long diff = evalStartMillis - nowMillis;
	    if (diff > 10 * 60 * 1000) {
	        throw new VideoInterviewUrlNotFoundException("면접 접속 시간이 아닙니다.");
	    }

	    return url;
    }
}
