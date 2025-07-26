package kr.or.ddit.vo.common;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "scheduleNo")
public class ScheduleVO implements Serializable{
	private String scheduleNo;
	private String userId;
	private String scheduleName;
	private String scheduleContent;
	private String scheduleStartDate;
	private String scheduleEndDate;
	
	private Timestamp scheduleStartTimestamp;
	private Timestamp scheduleEndTimestamp;
}
