package kr.or.ddit.vo.resume;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "educationNo")
public class EducationVO implements Serializable{

	private String educationNo;
	private String resumeNo;
	private String highestEducationCode;
	private String schoolName;
	private String graduateYn;
	private String transferYn;
	private String entranceDate;
	private String graduateDate;
	private String location;
	private String departmentCode;

}
