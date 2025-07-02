package kr.or.ddit.vo.common;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.constraints.MimeTypeCheck;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "userId")
public class CompanyVO extends UsersVO implements Serializable{
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
	@NotBlank
	private Integer comMem;
	private String comLogo;
	@MimeTypeCheck(mainType = "image/")
	private MultipartFile comImage;
	private String comPayment;
	private String industryType;
}
