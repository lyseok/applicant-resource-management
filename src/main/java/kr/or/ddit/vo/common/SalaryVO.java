package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "salaryId")
public class SalaryVO implements Serializable{
	private String salaryId;
	private String userId;
	private String jobCode;
	private String salaryMin;
	private String salaryMax;
	private String createDate;
}
