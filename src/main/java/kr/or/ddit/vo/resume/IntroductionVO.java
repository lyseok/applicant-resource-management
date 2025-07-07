package kr.or.ddit.vo.resume;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "introductionNo")
public class IntroductionVO implements Serializable{
	private String introductionDeleteDate;
	private String introductionNo;
	private String userId;
	@NotBlank(message = "자소서 명은 필수 입력 항목입니다.")
	@Size(max = 85, message = "자소서 명은 최대 85자까지 입력 가능합니다.")
	private String introductionName;
	@NotBlank(message = "자소서 내용은 필수 입력 항목입니다. ")
	@Size(max=2000, message = "자소서 내용은 최대 2000자 까지 입력 가능합니다.")
	private String introductionContent;
	@NotBlank(message = "자소서 문항은 필수 입력 항목입니다")
	@Size(max=150, message="자소서 문항은 최대 150자 까지 입력 가능합니다.")
	private String introductionQuestion;
	private String introductionCreateDate;

}
