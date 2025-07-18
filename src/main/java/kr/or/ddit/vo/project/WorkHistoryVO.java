package kr.or.ddit.vo.project;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="workHistNo")
public class WorkHistoryVO implements Serializable {
	private String workHistNo;
	private String prjNo;
	private String userId;
	private String workDate;
	private String workTable;
	private String workType;
	private String workTarget;
	private String workContent;
}
