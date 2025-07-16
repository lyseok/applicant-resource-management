package kr.or.ddit.vo.recruitment;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitExamNo")
public class RecruitmentExamVO implements Serializable{

	@NotBlank(groups = UpdateGroup.class)
	private String recruitExamNo;
	private String processNo;
	private String recruitExamName;
	@NotNull
	private Integer recruitExamCutline;
	private String recruitExamStartDate;
	@NotNull
	private Integer recruitExamTime;
	private String recruitExamDelDate;
	
	private String comExamNo;
	
	private List<RecruitmentExamQuestionsVO> questionList;
}
