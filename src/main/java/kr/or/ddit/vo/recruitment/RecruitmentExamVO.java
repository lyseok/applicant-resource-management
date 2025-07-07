package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitExamNo")
public class RecruitmentExamVO implements Serializable{

	private String recruitExamNo;
	private String processNo;
	private String recruitExamName;
	private Integer recruitExamCutline;
	private String recruitExamStartDate;
	private Integer recruitExamTime;
	private String recruitExamDelDate;
}
