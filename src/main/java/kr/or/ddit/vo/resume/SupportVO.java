package kr.or.ddit.vo.resume;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "supportNo")
public class SupportVO implements Serializable{

	private String supportNo;
	private String resumeNo;
	
	private String disabilityCode;
	private String disabilityCodeName;
	
	private String disabilityLevelCode;
	private String disabilityLevelCodeName;
	
	private String deleteDate;
}
