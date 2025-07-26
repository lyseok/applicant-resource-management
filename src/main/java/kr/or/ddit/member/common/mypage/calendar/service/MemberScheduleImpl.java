package kr.or.ddit.member.common.mypage.calendar.service;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.ScheduleMapper;
import kr.or.ddit.vo.common.ScheduleVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberScheduleImpl implements MemberSchedule {
	
	private final ScheduleMapper scheduleMapper;

	@Override
	public List<Map<String, Object>> getRecruitCalendar() {
	    List<Map<String, Object>> raw = scheduleMapper.findmemberRecruitSchedule(getUserId());

	    List<Map<String, Object>> events = new ArrayList<>();
	    Set<String> duplicateKeySet = new HashSet<>();

	    // 시험/면접 일정
	    for (Map<String, Object> row : raw) {
	        String stepType = (String) row.get("STEP_TYPE");
	        String preTitle = (String) row.get("RECRUITMENT_TITLE");
	        String recruitNo = (String) row.get("RECRUITMENT_NO");  // 중복 방지용

	        // 중복 방지: 면접/시험 일정이 같은 채용공고일 수도 있으니 구분 키 필요
	        String key = "SCHEDULE_" + recruitNo + "_" + stepType;
	        if (duplicateKeySet.contains(key)) continue;
	        duplicateKeySet.add(key);

	        String interviewType = "Y".equals(row.get("INTERVIEW_TYPE")) ? "화상면접" : "면접";
	        String subTitle = "RERP-001".equals(stepType)
	                ? (String) row.get("RECRUIT_EXAM_NAME")
	                : interviewType;

	        String title = preTitle + " " + subTitle;
	        String start = "RERP-001".equals(stepType)
	                ? (String) row.get("EXAM_DATETIME")
	                : (String) row.get("INTERVIEW_DATETIME");

	        String color = "RERP-001".equals(stepType) ? "#4e73df" : "#1cc88a";

	        Map<String, Object> event = new HashMap<>();
	        event.put("title", title);
	        event.put("start", start);
	        event.put("color", color);
	        events.add(event);
	    }

	    // 스크랩한 회사 채용 공고
	    List<Map<String, Object>> scrabCompany = scheduleMapper.selectCompanyScrab(getUserId());
	    for (Map<String, Object> row : scrabCompany) {
	        String recruitNo = (String) row.get("RECRUITMENT_NO");
	        if (duplicateKeySet.contains(recruitNo)) continue;
	        duplicateKeySet.add(recruitNo);
	        
	        String userId = (String) row.get("USER_ID");

	        Map<String, Object> event = new HashMap<>();
	        event.put("title", "[스크랩기업] " + row.get("RECRUITMENT_TITLE"));
	        event.put("start", row.get("RECRUITMENT_STARTDATE"));
	        event.put("end", row.get("RECRUITMENT_FINISH_DATE"));
	        event.put("color", getColorFromId(userId));
	        events.add(event);
	    }

	    //  스크랩한 채용 공고
	    List<Map<String, Object>> scrabRecruit = scheduleMapper.selectRecruitScrab(getUserId());
	    for (Map<String, Object> row : scrabRecruit) {
	        String recruitNo = (String) row.get("RECRUITMENT_NO");
	        if (duplicateKeySet.contains(recruitNo)) continue;
	        duplicateKeySet.add(recruitNo);
	        
	        String userId = (String) row.get("USER_ID");

	        Map<String, Object> event = new HashMap<>();
	        event.put("title", "[스크랩공고] " + row.get("RECRUITMENT_TITLE"));
	        event.put("start", row.get("RECRUITMENT_STARTDATE"));
	        event.put("end", row.get("RECRUITMENT_FINISH_DATE"));
	        event.put("color", getColorFromId(userId));
	        events.add(event);
	    }

	    return events;
	}
	
	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	public List<Map<String, Object>> getMyPersonalSchedule() {
	    List<ScheduleVO> voList = scheduleMapper.selectMyScheduleById(getUserId());
	    List<Map<String, Object>> result = new ArrayList<>();

	    for (ScheduleVO vo : voList) {
	        Map<String, Object> map = new HashMap<>();
	        map.put("id", vo.getScheduleNo());
	        map.put("title", vo.getScheduleName());
	        map.put("start", vo.getScheduleStartDate().replace(" ", "T"));
	        map.put("end", vo.getScheduleEndDate().replace(" ", "T"));
	        result.add(map);
	    }

	    return result;
	}

	@Override
	public void addSchedule(ScheduleVO vo) {
		vo.setUserId(getUserId());
		scheduleMapper.insertSchedule(vo);
		
	}

	@Override
	public void updateSchedule(ScheduleVO vo) {
		vo.setScheduleStartTimestamp(parseToTimestamp(vo.getScheduleStartDate()));
	    vo.setScheduleEndTimestamp(parseToTimestamp(vo.getScheduleEndDate()));
	    scheduleMapper.updateSchedule(vo);
		
	}

	@Override
	public void deleteSchedule(String scheduleNo) {
		scheduleMapper.deleteSchedule(scheduleNo);
		
	}
	
	// 공통 포맷 처리 유틸 함수
	private Timestamp parseToTimestamp(String datetimeStr) {
	    try {
	        //  ISO_INSTANT = 2025-07-31T13:00:00.000Z
	        Instant instant = Instant.parse(datetimeStr);
	        return Timestamp.from(instant.atZone(ZoneId.of("Asia/Seoul")).toInstant());

	    } catch (DateTimeParseException e1) {
	        try {
	            //  오프셋 포함된 포맷 = 2025-07-29T07:00:00.000+0900
	            DateTimeFormatter offsetFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	            OffsetDateTime odt = OffsetDateTime.parse(datetimeStr, offsetFormatter);
	            return Timestamp.valueOf(odt.toLocalDateTime());
	        } catch (DateTimeParseException e2) {
	            try {
	                // 단순 포맷 = 2025-07-28T22:00
	                DateTimeFormatter simple = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
	                LocalDateTime ldt = LocalDateTime.parse(datetimeStr, simple);
	                return Timestamp.valueOf(ldt);
	            } catch (DateTimeParseException e3) {
	                throw new IllegalArgumentException("날짜 포맷을 인식할 수 없습니다: " + datetimeStr);
	            }
	        }
	    }
	}
	
	private String getColorFromId(String id) {
	    int hash = Math.abs(id.hashCode()); // 음수 방지
	    int r = (hash & 0xFF0000) >> 16;
	    int g = (hash & 0x00FF00) >> 8;
	    int b = (hash & 0x0000FF);
	    return String.format("#%02x%02x%02x", r, g, b);
	}

}
