package kr.or.ddit.vo.resume;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "porCode")
public class PortfolioVO implements Serializable{

	private String porCode;          // 포트폴리오 번호 (POR_CODE)
    private String resumeNo;         // 이력서 번호 (RESUME_NO)
    private String porName;          // 포트폴리오 명 (POR_NAME)
    private String porStartDate;     // 작업 시작일자 (POR_START_DATE)
    private String porEndDate;       // 작업 종료일자 (POR_END_DATE)
    private String porInformation;    // 작업 설명 (POR_INFOMATION)
    private String deleteDate;       // 삭제일시 (DELETE_DATE)
}
