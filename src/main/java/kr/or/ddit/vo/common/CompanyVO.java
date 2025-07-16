package kr.or.ddit.vo.common;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.or.ddit.validate.constraints.MimeTypeCheck;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) 
public class CompanyVO extends UsersVO implements Serializable{
	@NotBlank
	private String userId;
	@NotBlank
	private String comName;
	private String comInfo;
	@NotBlank
	private String comNum;
	@NotBlank
	private String comEmail;
	private String comUrl;
	@NotBlank
	private String comCreateYear;
	@NotNull
	private Integer comMem;
	private String comLogo;
	@MimeTypeCheck(mainType = "image/")
	private transient MultipartFile comImage;
	private String comPayment;
	private String industryType;
	
	public String getMemName() {
		return comName;
	}
}
