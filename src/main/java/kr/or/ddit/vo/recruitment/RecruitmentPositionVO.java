package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitmentPositionCode")
public class RecruitmentPositionVO implements Serializable{

	@NotBlank(groups = UpdateGroup.class)
	private String recruitmentPositionCode;
	@NotBlank
	private String codeDetailNo;
	private String codeDetailName;
	private String recruitmentNo;
	private String recruitmentPositionDelDate;
}
