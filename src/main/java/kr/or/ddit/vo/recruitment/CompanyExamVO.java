package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "comExamNo")
public class CompanyExamVO implements Serializable{

	private String comExamNo;
	private String userId;
	private String comExamName;
	private String comExamDelDate;
}
