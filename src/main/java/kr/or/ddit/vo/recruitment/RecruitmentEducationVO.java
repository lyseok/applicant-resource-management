package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitmentEducationCode")
public class RecruitmentEducationVO implements Serializable{

	@NotBlank(groups = UpdateGroup.class)
	private String recruitmentEducationCode;
	@NotBlank
	private String codeDetailNo;
	private String codeDetailName;
	@NotBlank
	private String recruitmentNo;
	private String recruitmentEduDelDate;
}
