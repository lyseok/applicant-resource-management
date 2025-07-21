package kr.or.ddit.vo.project;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="prjAnncNo")
public class PrjBbsCommentVO implements Serializable {
	private String commentNo;
	private String prjPostNo;
	private String prjNo;
	private String userId;
	private String commentContent;
	private String createDate;
	private String deleteDate;
	
	private PrjBbsVO prjBbs;
	private PrjMemVO prjMem;
}
