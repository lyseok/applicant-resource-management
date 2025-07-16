package kr.or.ddit.vo.resume;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import kr.or.ddit.common.annotation.PastString;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "porCode")
public class PortfolioVO implements Serializable{

	private String porCode;          // 포트폴리오 번호 (POR_CODE)
    private String resumeNo;         // 이력서 번호 (RESUME_NO)   
    
    @NotNull(message = "포트폴리오명은 필수입력 항목입니다.")
    @Size(max = 20, message = "포트폴리오명은 최대 20자까지 입력 가능합니다.")
    private String porName;          // 포트폴리오 명 (POR_NAME)

    @NotNull(message = "작업 시작일자는 필수입력 항목입니다.")
    @PastString(message = "작업 시작일자는 과거 날짜여야 합니다.")
    private String porStartDate;     // 작업 시작일자 (POR_START_DATE)

    @NotNull(message = "작업 종료일자는 필수입력 항목입니다.")
    @PastString(message = "작업 종료일자는 과거 날짜여야 합니다.")
    private String porEndDate;       // 작업 종료일자 (POR_END_DATE)

    @NotNull(message = "작업 설명은 필수입력 항목입니다.")
    @Size(max = 85, message = "작업 설명은 최대 85자까지 입력 가능합니다.")
    private String porInformation;   // 작업 설명 (POR_INFOMATION)

    @NotNull(message = "포트폴리오 URL은 필수입력 항목입니다.")
    @Size(max = 255, message = "포트폴리오 URL은 최대 255자까지 입력 가능합니다.")
    private String porUrl;      	 // 포트폴리오경로 (PRO_URL)

    private String deleteDate;       // 삭제일시 (DELETE_DATE)
}
