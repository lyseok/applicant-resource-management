package kr.or.ddit.vo.resume;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "mySkillCode")
public class MySkillVO implements Serializable{

	private String mySkillCode;
	private String resumeNo;
	private String mySkillName;
	private String deleteDate;
}
