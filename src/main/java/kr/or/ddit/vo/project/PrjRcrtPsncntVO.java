package kr.or.ddit.vo.project;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of={"rcrtPsncntNo", "prjAnncNo"})
public class PrjRcrtPsncntVO implements Serializable {
	private String rcrtPsncntNo;
	private String prjAnncNo;
	private String jobCode;
	private Integer rcrtPsncnt;
	private String jobCodeName;
	
	private List<PrjAplcntVO> aplcntList;
}
