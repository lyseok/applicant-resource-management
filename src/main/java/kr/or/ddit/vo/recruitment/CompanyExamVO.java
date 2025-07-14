package kr.or.ddit.vo.recruitment;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "comExamNo")
public class CompanyExamVO implements Serializable{

	private String comExamNo;
	private String userId;
	@NotBlank(message = "시험 명을 입력해주세요")
	private String comExamName;
	private String comExamCreateDate;
	private String comExamDelDate;

	@Valid
	private List<ComExamQuestionsVO> questionList;
}
