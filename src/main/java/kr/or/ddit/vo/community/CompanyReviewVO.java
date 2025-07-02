package kr.or.ddit.vo.community;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(of="companyReviewNo")
public class CompanyReviewVO implements Serializable {
	private String companyReviewNo;
	private String userId;
	private String jobCode;
	private String workingYn;
	private String companyReviewStatus;
	private String companyReviewOneLine;
	private List<CompanyReviewQuestionVO> companyReviewQuestion;
}
