package kr.or.ddit.vo.resume;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "militaryNo")
public class MilitaryVO implements Serializable{

	private String militaryNo;
	private String resumeNo;
	private String serviceCategoryCode;
	private String militaryTypeCode;
	private String militaryRankCode;
	private String dischargeCode;
	private String militaryStartDate;
	private String militaryEndDate;
	private String militaryReason;
	private String deleteDate;
}
