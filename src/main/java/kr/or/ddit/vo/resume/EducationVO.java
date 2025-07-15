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

	@NotBlank(message = "최종학력은 필수입력 항목입니다.")
	@Size(max = 20)
	private String highestEducationCode;

	@NotBlank(message = "졸업여부는 필수입력 항목입니다.")
	private String graduateYn;
	private String graduateYnName;

	@NotBlank(message = "편입여부는 필수입력 항목입니다.")
	private String transferYn;

	@NotBlank(message = "입학일자는 필수 입력 항목 입니다.")
	@PastString(message = "입학일자는 과거 날짜여야 합니다.")
	private String entranceDate;

	@PastString(message = "졸업일자는 과거 날짜여야 합니다.")
	private String graduateDate;

	@NotBlank(message = "지역은 필수 입력 항목입니다.")
	@Size(max = 10)
	private String location;

	@Size(max = 30)
	private String departmentCode;

	@PastString(message = "삭제일자는 과거 날짜여야 합니다.")
	private String deleteDate;

	private List<SpecialtyVO> specialtyList;

}