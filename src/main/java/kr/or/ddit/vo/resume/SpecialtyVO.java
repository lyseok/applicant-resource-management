package kr.or.ddit.vo.resume;

import java.io.Serializable;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "educationNo", "resumeNo" })
public class SpecialtyVO implements Serializable {

	private String educationNo;
	private String resumeNo;

	@Size(max = 35)
	private String mainMajor;  // 주전공명

	@Size(max = 35)
	private String subMajor;  // 부전공명

	@Size(max = 10)
	private String subMajorCode; // 부전공 구분 코드

	private String deleteDate; // 삭제일시
}
