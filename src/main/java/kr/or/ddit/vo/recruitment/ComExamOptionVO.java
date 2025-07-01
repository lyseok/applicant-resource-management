package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "comOptionNo")
public class ComExamOptionVO implements Serializable{

	private String comOptionNo;
	private String comQuestionsNo;
	private String comOptionContent;
	private String comOptionCorrectYn;

}
