package kr.or.ddit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompanyInfoDTO {
	private String userId;

	@NotBlank
	private String comName;

	private String comInfo;

	@NotBlank(message = "대표전화를 입력해주세요.")
	private String comNum;

	@NotBlank(message = "이메일을 입력해주세요.")
	private String comEmail;

	private String comUrl;

	@NotBlank
	private String comCreateYear;

	@NotNull(message = "직원 수를 입력해주세요") // comMem은 이제 빈 값을 허용하지 않음
	private Integer comMem;
	
	@NotBlank(message = "기업의 업종을 선택해주세요")
	private String industryType;
	
	@NotBlank(message = "4대보험 여부를 선택해주세요")
	private String insuranceYn;
	
	@NotBlank(message = "기업의 형태를 선택해주세요")
	private String comType;
	
	@NotBlank(message = "기업의 규모를 선택해주세요")
	private String comSize;
	private String ceoName;
	private String comAddr;
	private Integer comCapital;
	private String comMainBiz;
	

	
	
}
