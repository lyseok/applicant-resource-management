package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitmentPositionCode")
public class RecruitmentPositionVO implements Serializable{

	private String recruitmentPositionCode;
	private String codeDetailNo;
	private String recruitmentNo;
	private String recruitmentPositionDelDate;
}
