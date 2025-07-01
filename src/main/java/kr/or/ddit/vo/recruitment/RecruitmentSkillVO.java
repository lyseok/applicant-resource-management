package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitSkillCode")
public class RecruitmentSkillVO implements Serializable{

	private String recruitSkillCode;
	private String recruitmentNo;
	private String recruitSkillName;

}
