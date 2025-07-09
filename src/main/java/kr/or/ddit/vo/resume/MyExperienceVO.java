package kr.or.ddit.vo.resume;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "myExpCode")
public class MyExperienceVO implements Serializable{

	private String myExpCode;
	private String resumeNo;
	
	private String expCode;
	private String expCodeName;
	
	private String expName;
	private String organizationName;
	private String expStartDate;
	private String expEndDate;
	private String deleteDate;
}
