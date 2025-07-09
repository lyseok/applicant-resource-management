package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitmentEdcucationCode")
public class RecruitmentEducationVO implements Serializable{

	@NotBlank(groups = UpdateGroup.class)
	private String recruitmentEdcucationCode;
	@NotBlank
	private String codeDetailNo;
	@NotBlank
	private String recruitmentNo;
	private String recruitmentPositionDelDate;
}
