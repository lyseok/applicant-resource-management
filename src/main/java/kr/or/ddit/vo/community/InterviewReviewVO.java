package kr.or.ddit.vo.community;

import java.io.Serializable;

import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="interviewReviewNo")
public class InterviewReviewVO implements Serializable {
	private String interviewReviewNo;
	private String interviewNo; //NO 수정
	private String comId;
	private String jobCode;
	private String interviewDate;
	private String interviewReviewDate;
	private String userId; // 추가
	private String status; //추가
	
	private InterviewInformationVO interviewInformation;
	private PassInformationVO passInformation;
	private RecruitmentNoticeVO recruitmentNotice;
	private CompanyVO company;
}
