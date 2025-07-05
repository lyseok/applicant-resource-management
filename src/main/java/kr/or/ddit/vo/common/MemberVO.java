package kr.or.ddit.vo.common;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.constraints.MimeTypeCheck;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) 
public class MemberVO extends UsersVO implements Serializable {
	@NotBlank
	private String userId;
	@NotBlank
	private String memName;
	@NotBlank
	private String memEmail;
	private String memBir;
	@NotBlank
	private String memTel;
	private String memAdd1;
	private String memAdd2;
	private String memImg;
	@MimeTypeCheck(mainType = "image/")
	private MultipartFile memberImage;
}
