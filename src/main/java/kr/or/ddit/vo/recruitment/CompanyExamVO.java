package kr.or.ddit.vo.recruitment;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "comExamNo")
public class CompanyExamVO implements Serializable{

	private String comExamNo;
	@NotBlank
	private String userId;
	@NotBlank
	private String comExamName;
	private String comExamDelDate;
	@Valid
	private List<ComExamQuestionsVO> questionList;
}
