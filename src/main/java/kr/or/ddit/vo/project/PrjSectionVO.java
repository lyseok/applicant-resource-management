package kr.or.ddit.vo.project;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="sectNo")
public class PrjSectionVO implements Serializable {
	private String sectNo;
	private String prjNo;
	private String userId;
	private String sectName;
	private Integer sectOrder;
	private String deleteDate;
	private String delUserId;
	
	private List<PrjSectionVO> prjSectionList;
	private PrjMemVO prjMem;
}
