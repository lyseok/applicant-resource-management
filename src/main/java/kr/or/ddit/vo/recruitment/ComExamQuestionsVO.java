package kr.or.ddit.vo.recruitment;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "comQuestionsNo")
public class ComExamQuestionsVO implements Serializable{

	private String comQuestionsNo;
	private String comExamNo;
	@NotBlank(message = "문제 내용을 입력해주세요")
	private String comExamContents;
	private String comExamQuestDelDate;
	
	@Valid
	@NotEmpty(message = "보기를 추가 해주세요.")
	@Size(min=2, max=5, message="보기는 {min}개 이상, {max}개 이하로 입력해야 합니다")
	private List<ComExamOptionVO> optionList;
}
