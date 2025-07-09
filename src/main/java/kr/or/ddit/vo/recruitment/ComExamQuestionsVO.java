package kr.or.ddit.vo.recruitment;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "comQuestionsNo")
public class ComExamQuestionsVO implements Serializable{

	private String comQuestionsNo;
	private String comExamNo;
	@NotBlank
	private String comExamContents;
	private String comExamQuestDelDate;
	@Valid
	private List<ComExamOptionVO> optionList;
}
