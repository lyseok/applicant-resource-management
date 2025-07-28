package kr.or.ddit.company.common.calendar.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyScheduleServiceImpl implements CompanyScheduleService {

	private final ScheduleMapper scheduleMapper;

	// 변환 함수
	private String toStringValue(Object val) {
		return (val == null) ? null : String.valueOf(val);
	}

	@Override
	public List<Map<String, Object>> getCompanySchedule() {
		List<Map<String, Object>> raw = scheduleMapper.selectCompanySchedule(getUserId());

		List<Map<String, Object>> events = new ArrayList<>();
		Set<String> duplicateKeySet = new HashSet<>();
		LocalDate today = LocalDate.now();

		for (Map<String, Object> row : raw) {
			String stepType = String.valueOf(row.get("REPR_RECRUIT_PROCESS_TYPE"));
			String preTitle = String.valueOf(row.get("RENO_RECRUITMENT_TITLE"));
			String recruitNo = String.valueOf(row.get("RENO_RECRUITMENT_NO"));
			String examName = String.valueOf(row.get("REEX_RECRUIT_EXAM_NAME"));
			String examDate = String.valueOf(row.get("REEX_RECRUIT_EXAM_START_DATE"));
			String interviewDate = String.valueOf(row.get("ITVI_INTEIVEW_DATE"));
			String interviewType = "Y".equals(String.valueOf(row.get("ITVI_INTERVIEW_TYPE"))) ? "화상면접" : "면접";
			String recruitStart = String.valueOf(row.get("RENO_RECRUITMENT_STARTDATE"));
			String recruitFinish = String.valueOf(row.get("RENO_RECRUITMENT_FINISH_DATE"));

			// 마감 여부 (문자열 비교로 단순 처리)
			boolean isClosed = false;
			try {
				if (recruitFinish != null && !recruitFinish.equals("null") && !recruitFinish.isBlank()) {
					LocalDate finish = LocalDate.parse(recruitFinish.substring(0, 10)); // 앞 10자리만 (yyyy-MM-dd)
					if (finish.isBefore(today))
						isClosed = true;
				}
			} catch (Exception ignore) {
			}

			// --- 채용공고 기간 이벤트 ---
			String recruitKey = "NOTICE_" + recruitNo;
			if (!duplicateKeySet.contains(recruitKey)) {
				Map<String, Object> noticeEvent = new HashMap<>();
				noticeEvent.put("title", preTitle + " (채용공고)");
				noticeEvent.put("start", recruitStart);
				noticeEvent.put("end", recruitFinish);
				noticeEvent.put("color", isClosed ? "#b0b0b0" : "#ff9f40");
				Map<String, Object> noticeExtra = new HashMap<>();
				noticeExtra.put("type", "공고");
				noticeExtra.put("recruitStart", recruitStart);
				noticeExtra.put("recruitFinish", recruitFinish);
				noticeExtra.put("isClosed", isClosed);
				noticeEvent.put("extra", noticeExtra);
				events.add(noticeEvent);
				duplicateKeySet.add(recruitKey);
			}

			// --- 시험/면접 일정 ---
			String key = recruitNo + "_" + stepType;
			if (duplicateKeySet.contains(key))
				continue;
			duplicateKeySet.add(key);

			String subTitle = "RERP-001".equals(stepType) ? examName : interviewType;
			String start = "RERP-001".equals(stepType) ? examDate : interviewDate;
			String type = "RERP-001".equals(stepType) ? "시험" : "면접";

			String color = switch (stepType) {
			case "RERP-001" -> "#4e73df";
			case "RERP-002" -> "#1cc88a";
			default -> "#f6c23e";
			};
			if (isClosed)
				color = "#b0b0b0";

			Map<String, Object> event = new HashMap<>();
			event.put("title", preTitle + " " + subTitle);
			event.put("start", start);
			event.put("color", color);

			Map<String, Object> extra = new HashMap<>();
			extra.put("type", type);
			extra.put("recruitStart", recruitStart);
			extra.put("recruitFinish", recruitFinish);
			extra.put("isClosed", isClosed);
			event.put("extra", extra);

			events.add(event);
		}
		return events;
	}

	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
