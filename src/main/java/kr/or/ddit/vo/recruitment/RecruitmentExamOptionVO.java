package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitOptionNo")
public class RecruitmentExamOptionVO implements Serializable{

	private String recruitOptionNo;
	private String recruitExamQuestNo;
	private String recruitExamOptionContent;
	private String recruitExamOptionCorrectYn;
	private String recruitExamOptionDelDate;
}
