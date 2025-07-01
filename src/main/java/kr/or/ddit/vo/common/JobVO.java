package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="jobCode")
public class JobVO implements Serializable{
	private String jobCode;
	private String topJobCode;
	private String jobName;
}
