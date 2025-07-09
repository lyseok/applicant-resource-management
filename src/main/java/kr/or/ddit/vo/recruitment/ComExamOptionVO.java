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
	@NotBlank
	private String comOptionContent;
	@NotBlank
	private String comOptionCorrectYn;
	private String comOptionDelDate;
}
