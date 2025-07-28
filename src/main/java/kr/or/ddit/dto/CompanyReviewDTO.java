package kr.or.ddit.dto;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.vo.community.CompanyReviewQuestionVO;
import lombok.Data;
@Data
public class CompanyReviewDTO implements Serializable{
	private String careerNo;
	
	@NotBlank(message = "한줄평을 작성해주세요. ")
	private String companyReviewOneLine;
	
	@Valid
	private List<CompanyReviewQuestionVO> companyReviewQuestion;

}
