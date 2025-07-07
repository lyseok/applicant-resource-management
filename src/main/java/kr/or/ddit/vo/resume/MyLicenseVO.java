package kr.or.ddit.vo.resume;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "myLicense")
public class MyLicenseVO implements Serializable{

	private String myLicense;
	private String resumeNo;
	private String licenseCode;
	private String licensePassDate;
	private String deleteDate;
}
