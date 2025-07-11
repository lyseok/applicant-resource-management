package kr.or.ddit.vo.resume;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "myExpCode")
public class MyExperienceVO implements Serializable{

	private String myExpCode;	//pk
	private String resumeNo;
	
	private String expCode;		// 경험구분코드 (공통)
	private String expCodeName;
	
	private String expName;
	private String organizationName;
	private String expStartDate;
	private String expEndDate;
	private String deleteDate;
}
