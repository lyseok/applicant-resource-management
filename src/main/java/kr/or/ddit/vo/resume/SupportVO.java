package kr.or.ddit.vo.resume;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "supportNo")
public class SupportVO implements Serializable {

	private String supportNo;
	private String resumeNo;
	
	@NotBlank(message = "고용지원대상은 필수 입력 항목입니다.")
    @Size(max = 20, message = "고용지원대상은 최대 10자까지 입력 가능합니다.")
	private String disabilityCode;
	private String disabilityCodeName;

    @Size(max = 20, message = "장애등급은 최대 10자까지 입력 가능합니다.")
	private String disabilityLevelCode;
	private String disabilityLevelCodeName;


	private String deleteDate;
}
