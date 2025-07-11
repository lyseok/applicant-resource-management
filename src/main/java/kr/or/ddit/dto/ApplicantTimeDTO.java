package kr.or.ddit.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApplicantTimeDTO {
	@NotBlank
    private String applicantId;          // 지원자 아이디
	@NotBlank
    private String evaluationStartTime;  // 면접 시간 (datetime-local 포맷, e.g., 2025-07-14T14:20)
}