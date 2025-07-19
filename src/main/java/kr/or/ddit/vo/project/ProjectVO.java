package kr.or.ddit.vo.project;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="prjNo")
public class ProjectVO implements Serializable {
	private String prjNo;
	private String userId;
	private String projectBoardNo;
	private String projectName;
	private String projectContents;
	
	private String projectStatus;
	private String projectStatusName;
	
	private String createDate;
	private String finishDate;
	private String deleteDate;
	private String projectColor;
	
	private List<PrjMemVO> prjMemList;
	private ChatroomVO chatroom;
}
