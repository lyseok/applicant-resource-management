package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "passerNo")
public class PasserVO implements Serializable{

	private String passerNo;
	private String applicantId;
	private String passAlarmYn;
	private String recruitAcceptYn;
	private String hireDate;
	private String passerDelDate;
}
