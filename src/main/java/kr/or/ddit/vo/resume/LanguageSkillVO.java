package kr.or.ddit.vo.resume;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "languageSkillNo")
public class LanguageSkillVO implements Serializable {

	private String languageSkillNo;
	private String resumeNo;

	@NotNull(message = "시험구분은 필수 입력 항목입니다.")
	@Size(max = 20, message = "시험구분은 최대 20자까지 입력 가능합니다.")
	private String languageExamCode;
	private String languageExamCodeName;

	@NotNull(message = "어학명은 필수 입력 항목입니다.")
	@Size(max = 20, message = "어학명은 최대 20자까지 입력 가능합니다.")
	private String languageCode;
	private String languageCodeName;

	@NotNull(message = "시험명은 필수 입력 항목입니다.")
	@Size(max = 100, message = "시험명은 최대 100자까지 입력 가능합니다.")
	private String languageExamName;

	@NotNull(message = "취득일자는 필수 입력 항목입니다.")
    @Past(message = "취득일자는 과거 날짜여야 합니다.")
	private String passDate;

	@NotNull(message = "시험 유형은 필수 입력 항목입니다.")
	@Size(max = 1, message = "시험 유형은 최대 1자까지 입력 가능합니다.")
	private String languageExamType;

	@Size(max = 3, message = "시험 점수는 최대 3자리까지 입력 가능합니다.")
	private String languageExamScore;

	@Size(max = 20, message = "급수(레벨)은 최대 20자리까지 입력 가능합니다.")
	private String languageExamLevelCode;
	private String languageExamLevelCodeName;

	private String deleteDate;


}
