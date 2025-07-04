package kr.or.ddit.vo.community;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(of="companyReviewNo")
public class CompanyReviewVO implements Serializable {
	private String companyReviewNo;
	private String comId; //기업
	private String jobCode;
	private String workingYn;
	private String companyReviewStatus;
	private String companyReviewOneLine;
	private String status; //삭제여부
	private String userId; //일반
	private List<CompanyReviewQuestionVO> companyReviewQuestion;
}
