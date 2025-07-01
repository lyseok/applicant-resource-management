package kr.or.ddit.vo.resume;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "subIntroductionNo")
public class SubIntroductionVO implements Serializable{

	private String subIntroductionNo;
	private String resumeNo;
	private String introductionNo;

}
