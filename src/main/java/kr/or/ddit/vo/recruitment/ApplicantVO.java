package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(of = "applicantId")
public class ApplicantVO implements Serializable{

	private String applicantId;
	private String recruitmentNo;
	private String resumeNo;
	private String resumeViewYn;
	private String userId;
	private String applicantDelDate;

	private MemberVO member;
	private ResumeVO resume;
}
