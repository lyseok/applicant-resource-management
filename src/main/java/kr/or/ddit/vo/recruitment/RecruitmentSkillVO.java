package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitSkillCode")
public class RecruitmentSkillVO implements Serializable{

	@NotBlank(groups = UpdateGroup.class)
	private String recruitSkillCode;
	@NotBlank
	private String recruitmentNo;
	@NotBlank
	private String recruitSkillName;
	private String recruitSkillDelDate;
}
