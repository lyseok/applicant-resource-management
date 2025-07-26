package kr.or.ddit.member.common.mypage.calendar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        for (Map<String, Object> row : raw) {
            String stepType = (String) row.get("STEP_TYPE");
            String preTitle = (String) row.get("RECRUITMENT_TITLE");
            
            // 인터뷰 타입이 Y면 화상면접, 아니면 면접
            String interviewType = "Y".equals(row.get("INTERVIEW_TYPE")) ? "화상면접" : "면접";

            String subTitle = "RERP-001".equals(stepType)
                    ? (String) row.get("RECRUIT_EXAM_NAME")
                    : interviewType;  // 수정된 부분

            String title = preTitle + " " + subTitle;

            String start = "RERP-001".equals(stepType)
                    ? (String) row.get("EXAM_DATETIME")
                    : (String) row.get("INTERVIEW_DATETIME");

            String color = "RERP-001".equals(stepType) ? "#4e73df" : "#1cc88a"; // 시험=파랑, 면접=초록

            Map<String, Object> event = new HashMap<>();
            event.put("title", title);
            event.put("start", start);
            event.put("color", color);
            events.add(event);
        }
        
     // 1. 스크랩한 회사 채용 공고
        List<Map<String, Object>> scrabCompany = scheduleMapper.selectCompanyScrab(getUserId());
        for (Map<String, Object> row : scrabCompany) {
            Map<String, Object> event = new HashMap<>();
            event.put("title", "[스크랩기업] " + row.get("RECRUITMENT_TITLE"));
            event.put("start", row.get("RECRUITMENT_STARTDATE"));
            event.put("end", row.get("RECRUITMENT_FINISH_DATE"));
            event.put("color", "#4e73df"); // 파란색
            events.add(event);
        }
        
     // 2. 스크랩한 채용 공고
        List<Map<String, Object>> scrabRecruit = scheduleMapper.selectRecruitScrab(getUserId());
        for (Map<String, Object> row : scrabRecruit) {
            Map<String, Object> event = new HashMap<>();
            event.put("title", "[스크랩공고] " + row.get("RECRUITMENT_TITLE"));
            event.put("start", row.get("RECRUITMENT_STARTDATE"));
            event.put("end", row.get("RECRUITMENT_FINISH_DATE"));
            event.put("color", "#1cc88a"); // 초록색
            events.add(event);
        }

        return events;
	}
	
	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	@Override
	public List<ScheduleVO> getMyPersonalSchedule() {
		return scheduleMapper.selectMyScheduleById(getUserId());
	}

	@Override
	public void addSchedule(ScheduleVO vo) {
		scheduleMapper.insertSchedule(vo);
		
	}

	@Override
	public void updateSchedule(ScheduleVO vo) {
		scheduleMapper.updateSchedule(vo);
		
	}

	@Override
	public void deleteSchedule(String scheduleNo) {
		scheduleMapper.deleteSchedule(scheduleNo);
		
	}

}
