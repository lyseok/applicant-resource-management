package kr.or.ddit.vo.project;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="taskNo")
public class PrjTaskVO implements Serializable {
	private String taskNo;
	private String prjNo;
	private String userId;
	private String sectNo;
	private String creatorId;
	private String taskName;
	private String taskStatus;
	private String detailContent;
	private String startDate;
	private String dueDate;
	private String priorityCode;
	private String upperTaskNo;
	private String progressRate;
	private String deleteDate;
	private String deleteUserId;
}
