package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "passIntroductionNo")
public class PassIntroductionVO implements Serializable{

	private String passIntroductionNo;
	private String passerCode;
	private String introductionCode;
	private String passIntroductionDelDate;
}
