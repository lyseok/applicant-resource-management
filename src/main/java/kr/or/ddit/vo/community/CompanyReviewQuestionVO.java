package kr.or.ddit.vo.community;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="companyReviewQuestionNo")
public class CompanyReviewQuestionVO implements Serializable {
	private String companyReviewQuestionNo;
	private String companyReviewNo;
	private String reviewSubjectCode;
	@NotNull(message = "설문 답을 선택해주세요.")
	private Integer companyReviewScore;
}
