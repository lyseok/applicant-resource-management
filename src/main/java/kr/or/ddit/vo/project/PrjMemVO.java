package kr.or.ddit.vo.project;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of={"prjNo", "userId"})
public class PrjMemVO implements Serializable {
	private String prjNo;
	private String userId;
	private String authorityCode;
	private String deleteDate;
	private String userName;
	private String userPosition;
	
	private ProjectVO project;
	private List<WorkHistoryVO> workHistoryList;
	private List<PrjSectionVO> prjSectionList;
	private List<PrjTaskVO> prjTaskList;
	private List<TasksManagerVO> tasksManagerList;
	private List<PrjBbsVO> prjBbsList;
	private List<PrjBbsCommentVO> prjBbsCommentList;
	private ChatroomMemVO chatroomMem;
}
