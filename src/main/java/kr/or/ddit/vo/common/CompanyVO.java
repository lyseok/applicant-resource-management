package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "userId")
public class CompanyVO implements Serializable{
	private String userId;
	private String comName;
	private String comInfo;
	private String comNum;
	private String comEmail;
	private String comUrl;
	private String comCreateYear;
	private Integer comMem;
	private String comLogo;
	private String comPayment;
	private String industryType;
}
