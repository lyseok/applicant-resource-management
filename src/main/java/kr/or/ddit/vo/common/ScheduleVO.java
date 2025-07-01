package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "scheduleNo")
public class ScheduleVO implements Serializable{
	private String scheduleNo;
	private String userId;
	private String scheduleName;
	private String scheduleContent;
}
