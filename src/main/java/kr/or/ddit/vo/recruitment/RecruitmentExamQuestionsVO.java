package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitExamQuestNo")
public class RecruitmentExamQuestionsVO implements Serializable{

	private String recruitExamQuestNo;
	private String recruitExamNo;
	private String recruitExamQuestContent;

}
