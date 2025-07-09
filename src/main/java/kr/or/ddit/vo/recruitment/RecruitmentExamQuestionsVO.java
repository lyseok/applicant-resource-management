package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitExamQuestNo")
public class RecruitmentExamQuestionsVO implements Serializable{

	@NotBlank(groups = UpdateGroup.class)
	private String recruitExamQuestNo;
	@NotBlank
	private String recruitExamNo;
	@NotBlank
	private String recruitExamQuestContent;
	private String recruitExamQuestDelDate;
}
