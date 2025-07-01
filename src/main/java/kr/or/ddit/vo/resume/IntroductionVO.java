package kr.or.ddit.vo.resume;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "introductionNo")
public class IntroductionVO implements Serializable{

	private String introductionNo;
	private String userId;
	private String introductionName;
	private String introductionContent;

}
