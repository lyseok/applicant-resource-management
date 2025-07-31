package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "readResumeNo")
public class ReadResumeVO implements Serializable{

	private Integer readResumeNo;
	private String companyId;
	private String resumeNo;
	private String readResumeDate;
}
