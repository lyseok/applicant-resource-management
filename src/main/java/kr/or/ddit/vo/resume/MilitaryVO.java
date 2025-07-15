package kr.or.ddit.vo.resume;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "militaryNo")
public class MilitaryVO implements Serializable {

	private String militaryNo;
	private String resumeNo;

	@NotNull(message="복무 구분은 필수 입력 항목 입니다.")
    @Size(max = 10, message = "복무 구분 항목은 최대 10자까지 입력 가능합니다.")
	private String serviceCategoryCode;

    @Size(max = 10, message = "군별은 최대 10자까지 입력 가능합니다.")
	private String militaryTypeCode;

    @Size(max = 10, message = "계급은 최대 10자까지 입력 가능합니다.")
	private String militaryRankCode;

    @Size(max = 10, message = "전역사유는 최대 10자까지 입력 가능합니다.")
	private String dischargeCode;

    @Past(message = "복무 시작일자는 과거 날짜여야 합니다.")
	private String militaryStartDate;

    @Past(message = "복무 종료일자는 과거 날짜여야 합니다.")
	private String militaryEndDate;

	@Size(max = 1000)
	private String militaryReason;
    @Size(max = 1000, message = "병역 상세 사유는 최대 1000자까지 입력 가능합니다.")

	private String deleteDate;
}
