package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "comQuestionsNo")
public class ComExamQuestionsVO implements Serializable{

	private String comQuestionsNo;
	private String comExamNo;
	private String comExamContents;
	private String comExamQuestDelDate;
}
