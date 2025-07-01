package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "brNumber")
public class BusinessregistrationVO implements Serializable{
	private String brNumber;
	private String userName;
	private String comName;
	private String filePath;
}
