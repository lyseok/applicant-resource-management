package kr.or.ddit.vo.resume;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "introductionNo")
public class IntroductionVO implements Serializable{
	private String introductionNo;
	private String userId;
	
	@NotBlank(message = "자소서 명은 필수 입력 항목입니다.")
	@Size(max = 85, message = "자소서 명은 최대 85자까지 입력 가능합니다.")
	private String introductionName;
	
	private String introductionSubmitYn;
	private String introductionCreateDate;
	private String introductionDeleteDate;
	
	@Valid
	List<introductionQuestionVO> introductionQuestionList;
	
	
	

}
