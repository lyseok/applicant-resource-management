package kr.or.ddit.vo.project;

import java.io.Serializable;

import kr.or.ddit.vo.resume.ResumeVO;
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
	
	private ResumeVO resume;
}
