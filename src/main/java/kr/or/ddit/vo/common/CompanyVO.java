package kr.or.ddit.vo.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.or.ddit.validate.constraints.MimeTypeCheck;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) 
public class CompanyVO extends UsersVO implements Serializable {
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
	private String comBackgroundImg;

	@MimeTypeCheck(mainType = "image/")
	private transient MultipartFile comImage;

	private String comPayment;
	private String industryType;

	// 추가1
	private String comType;      // 기업 구분
	private String comSize;      // 기업 규모
	private String insuranceYn;  // 4대보험 여부
	private String ceoName;      // 대표자 이름
	
	private String comAddr;      // 기업 주소
	// 회원가입 때 바인딩할 주소
	private String comAddr1;
	private String comAddr2;
	
	private String comCapital;   // 기업 자본금
	private String comMainBiz;   // 기업 주요 사업

	private List<FilesVO> fileList;

	public String getMemName() {
		return comName;
	}
}
