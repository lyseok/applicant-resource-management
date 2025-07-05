package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitProcessNo")
public class RecruitProcessVO implements Serializable{

	private String recruitProcessNo;
	private String recruitmentNo;
	private String companyName;
	private String recruitProcessStep;
	private String recruitProcessFinal;
	private String recruitProcessType;

}
