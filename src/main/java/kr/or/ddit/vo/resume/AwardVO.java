package kr.or.ddit.vo.resume;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "awardCode")
public class AwardVO implements Serializable{

	private String awardCode;
	private String resumeNo;
	private String awardName;
	private String awardDate;
	private String hosting;

}
