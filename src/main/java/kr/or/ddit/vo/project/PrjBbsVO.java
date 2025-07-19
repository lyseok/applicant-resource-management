package kr.or.ddit.vo.project;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="prjPostNo")
public class PrjBbsVO implements Serializable {
	private String prjPostNo;
	private String prjNo;
	private String userId;
	private String title;
	private String createDate;
	private String content;
	private String deleteDate;
	
	private List<PrjBbsCommentVO> bbsCommentList;
	private PrjMemVO prjMem;
}
