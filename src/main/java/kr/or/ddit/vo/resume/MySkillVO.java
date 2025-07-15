package kr.or.ddit.vo.resume;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "mySkillCode")
public class MySkillVO implements Serializable {

	private String mySkillCode;
	private String resumeNo;
	
	@NotNull(message = "보유 기술명은 필수입력 항목입니다.")
	@Size(max = 85, message = "보유 기술명은 최대 85자까지 입력 가능합니다.")
	private String mySkillName;
	
	private String deleteDate;
}
