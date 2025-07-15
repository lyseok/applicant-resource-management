package kr.or.ddit.vo.resume;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import kr.or.ddit.common.annotation.PastString;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "myLicense")
public class MyLicenseVO implements Serializable {

	private String myLicense;
	private String resumeNo;

	@NotBlank(message = "자격증 종류(licenseCode)는 필수 입력 항목입니다.")
    @Size(max = 20, message = "자격증 코드는 최대 20자까지 입력 가능합니다.")
	private String licenseCode;

	@PastString(message = "자격증 취득일은 과거 날짜여야 합니다.")
	private String licensePassDate;

	@PastString
	private String deleteDate;
}
