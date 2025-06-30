package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "employmentId")
public class EmploymentTestVO implements Serializable{
	private Integer employmentId;
	private String empDetail;
	private String empFinishdate;
	private String welare;
	private String empDesk;
}
