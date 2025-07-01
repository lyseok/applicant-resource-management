package kr.or.ddit.vo.community;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(of="passInformationNo")
public class PassInformationVO implements Serializable {
	private String passInformationNo;
	private String interviewReviewNo;
	private String interviewQuestion;
	private String tip;
	private String interviewPassYn;
}
