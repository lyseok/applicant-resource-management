package kr.or.ddit.vo.resume;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "educationNo")
public class SpecialtyVO implements Serializable{

	private String educationNo;
	private String resumeNo;
	private String mainMajor;
	private String subMajor;
	private String subMajorCode;

}
