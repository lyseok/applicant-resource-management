package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitOptionNo")
public class RecruitmentExamOptionVO implements Serializable{

	@NotBlank(groups = UpdateGroup.class)
	private String recruitOptionNo;
	private String recruitExamQuestNo;
	private String recruitExamOptionContent;
	private String recruitExamOptionCorrectYn;
	private String recruitExamOptionDelDate;
}
