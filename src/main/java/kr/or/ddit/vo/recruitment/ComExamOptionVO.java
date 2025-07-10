package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "comOptionNo")
public class ComExamOptionVO implements Serializable{

	private String comOptionNo;
	private String comQuestionsNo;
	@NotBlank(message = "보기 내용을 입력해주세요.")
	private String comOptionContent;
	@NotBlank(message = "정답을 체크해주세요.")
	private String comOptionCorrectYn;
	private String comOptionDelDate;
}
