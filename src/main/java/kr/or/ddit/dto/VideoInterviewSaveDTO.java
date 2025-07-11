package kr.or.ddit.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.vo.recruitment.ApplicantRecordVO;
import lombok.Data;

@Data
public class VideoInterviewSaveDTO {
    private String videoInterviewNo;
    private String interviewNo;
    private String companyInterviewUrl;
    @NotBlank
    private String roomTitle;
    @Min(value = 2, message = "최소 2명 이상이어야 합니다.")
    @Max(value = 8, message = "최대 8명까지만 입력 가능합니다.")
    private Integer maxJoinCount;
    @NotBlank
    private String startDate;
    @NotBlank
    private String endDate;
    
    @Valid
    private List<ApplicantTimeDTO> applicantTimes; // 지원자별 시간 리스트
    
    private List<ApplicantRecordVO> applicantRecordList;
}
