package kr.or.ddit.vo.resume;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kr.or.ddit.common.annotation.PastString;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "educationNo", "resumeNo" })
public class EducationVO implements Serializable {

	private String educationNo;
	private String resumeNo;
	
	@NotBlank
	@Size(max = 50)
	private String schoolName;

	@NotBlank
	@Size(max = 20)
	private String highestEducationCode;

	@NotBlank
	private String graduateYn;

	@NotBlank
	private String transferYn;

	@PastString(message = "입학일자는 과거 날짜여야 합니다.")
	private String entranceDate;

	@PastString(message = "졸업일자는 과거 날짜여야 합니다.")
	private String graduateDate;

	@Size(max = 10)
	private String location;

	@Size(max = 30)
	private String departmentCode;

	@PastString(message = "삭제일자는 과거 날짜여야 합니다.")
	private String deleteDate;

	private List<SpecialtyVO> specialtyList;

}