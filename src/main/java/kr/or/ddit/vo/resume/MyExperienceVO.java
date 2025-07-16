package kr.or.ddit.vo.resume;

import java.io.Serializable;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import kr.or.ddit.common.annotation.PastString;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "myExpCode")
public class MyExperienceVO implements Serializable {

	private String myExpCode; // pk
	private String resumeNo;

	@Size(max = 100, message = "경험구분운 최대 100자까지 입력 가능합니다.")
	private String expCode; // 경험구분코드 (공통)
	private String expCodeName;

	@Size(max = 85, message = "경험명은 최대 85자까지 입력 가능합니다.")
	private String expName;

	@Size(max = 85, message = "기관명은 최대 85자까지 입력 가능합니다.")
	private String organizationName;

	@PastString(message = "경험 종료일자는 과거 날짜여야 합니다.")
	private String expStartDate;

	@PastString(message = "경험 종료일자는 과거 날짜여야 합니다.")
	private String expEndDate;
	
	private String deleteDate;
}
