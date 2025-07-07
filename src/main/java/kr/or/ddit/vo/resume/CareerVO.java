package kr.or.ddit.vo.resume;

import java.io.Serializable;

import kr.or.ddit.vo.common.CompanyVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "careerNo")
public class CareerVO implements Serializable{

	private String careerNo;
	private String resumeNo;
	private String jobCode;
	private String startWorkDate;
	private String retireDate;
	private String tenure;
	private String department;
	private String responsibility;
	private String freelancer;
	private String jobGradeCode;
	private String positionCode;
	private String careerYear;
	private String salary;
	private String location;
	private String deleteDate;
	private String comId;
	
	//추가
	private CompanyVO company;
}
