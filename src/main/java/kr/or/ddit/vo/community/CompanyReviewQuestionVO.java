package kr.or.ddit.vo.community;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="companyReviewQuestionNo")
public class CompanyReviewQuestionVO implements Serializable {
	private String companyReviewQuestionNo;
	private String companyReviewNo;
	private String reviewSubjectCode;
	private String companyReviewContent;
}
