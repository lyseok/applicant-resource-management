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
@EqualsAndHashCode(of = "recruitExamQuestNo")
public class RecruitmentExamQuestionsVO implements Serializable{

	@NotBlank(groups = UpdateGroup.class)
	private String recruitExamQuestNo;
	private String recruitExamNo;
	private String recruitExamQuestContent;
	private String recruitExamQuestDelDate;
	
	private List<RecruitmentExamOptionVO> optionList;
}
