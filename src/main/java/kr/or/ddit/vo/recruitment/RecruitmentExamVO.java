package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitExamNo")
public class RecruitmentExamVO implements Serializable{

	@NotBlank(groups = UpdateGroup.class)
	private String recruitExamNo;
	@NotBlank
	private String processNo;
	@NotBlank
	private String recruitExamName;
	@NotBlank
	private Integer recruitExamCutline;
	@NotBlank
	private String recruitExamStartDate;
	@NotBlank
	private Integer recruitExamTime;
	private String recruitExamDelDate;
}
