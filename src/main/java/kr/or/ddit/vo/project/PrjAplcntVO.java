package kr.or.ddit.vo.project;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="prjAplcntNo")
public class PrjAplcntVO implements Serializable {
	private String prjAplcntNo;
	private String resumeNo;
	private String userId;
	private String aplcntStatusCode;
	private String aplcntDate;
	private String rcrtPsncntNo;
	private String prjAnncNo;
}
