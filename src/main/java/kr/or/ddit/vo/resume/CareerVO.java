package kr.or.ddit.vo.resume;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kr.or.ddit.common.annotation.PastString;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.MemberVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "careerNo")
public class CareerVO implements Serializable {

	private String careerNo;
	private String resumeNo;

	@NotBlank(message = "직무 코드는 필수 입력 항목입니다.")
    private String jobCode;
    private String jobCodeName;

    @PastString(message = "입사일은 과거 날짜여야 합니다.")
    private String startWorkDate;

    @PastString(message = "퇴사일은 과거 날짜여야 합니다.")
    private String retireDate;

    @Pattern(regexp = "Y|N", message = "재직 여부는 Y 또는 N으로 입력해야 합니다.")
    private String tenure; // 재직여부 (예: 2년 3개월)

    @Size(max = 50, message = "부서명은 최대 50자까지 입력 가능합니다.")
    private String department;

    @Size(max = 200, message = "담당 업무는 최대 200자까지 입력 가능합니다.")
    private String responsibility;

    private Boolean freelancer;

    @Size(max = 20)
    private String jobGradeCode;
    private String jobGradeCodeName;

    @Size(max = 20, message = "직책 코드는 최대 20자까지 입력 가능합니다.")
    private String positionCode;
    private String positionCodeName;

    @Size(max = 4, message = "연차는 최대 4자까지 입력 가능합니다.")
    private String careerYear;
    private String careerYearName;

    @Size(max = 20, message = "연봉은 최대 20자까지 입력 가능합니다.")
    private String salary;

    @Size(max = 85, message = "근무지는 최대 85자까지 입력 가능합니다.")
    private String location;

    @PastString(message = "삭제일은 과거 날짜여야 합니다.")
    private String deleteDate;

    private String comId;


	// 추가
	private CompanyVO company;
	private ResumeVO resume;
	private String userName;
	private String userId;
	private MySkillVO myskill;
	private String mySkillName;

}
