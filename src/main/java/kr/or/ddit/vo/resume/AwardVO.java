package kr.or.ddit.vo.resume;

import java.io.Serializable;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import kr.or.ddit.common.annotation.PastString;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "awardCode")
public class AwardVO implements Serializable {
	private String awardCode;
	private String resumeNo;

	@Size(max = 100, message = "수상명은 최대 100자까지 입력 가능합니다.")
	private String awardName;

	@PastString(message = "수상일은 과거 날짜여야 합니다.")
	private String awardDate;

    @Size(max = 100, message = "주최기관명은 최대 100자까지 입력 가능합니다.")
	private String hosting;
	
	private String deleteDate;
}
