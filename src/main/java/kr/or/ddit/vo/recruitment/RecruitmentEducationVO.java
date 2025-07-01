package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitmentEdcucationCode")
public class RecruitmentEducationVO implements Serializable{

	private String recruitmentEdcucationCode;
	private String codeDetailNo;
	private String recruitmentNo;

}
