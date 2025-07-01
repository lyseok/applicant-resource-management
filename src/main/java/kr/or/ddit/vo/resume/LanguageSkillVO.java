package kr.or.ddit.vo.resume;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "languageSkillNo")
public class LanguageSkillVO implements Serializable{

	private String languageSkillNo;
	private String resumeNo;
	private String languageExamCode;
	private String languageCode;
	private String languageExamName;
	private String passDate;
	private String languageExamType;
	private String languageExamScore;
	private String languageExamLevelCode;

}
