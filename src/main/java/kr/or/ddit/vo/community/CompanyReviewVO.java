package kr.or.ddit.vo.community;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.vo.common.CompanyVO;
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
	@NotBlank(message = "한줄평을 적어주세요.")
	private String companyReviewOneLine;
	private String status; //삭제여부
	private String userId; //일반
	@Valid
	private List<CompanyReviewQuestionVO> companyReviewQuestion;
	private CompanyVO company;
}
