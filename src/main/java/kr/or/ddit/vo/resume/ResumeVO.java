package kr.or.ddit.vo.resume;

import java.io.Serializable;
import java.util.List;

import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.JobVO;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.common.TopJobVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "resumeNo")
public class ResumeVO implements Serializable{

	private String resumeMainYn;
	private String introductionNo;
	private String resumeNo;
	private String userId;
	private String userName;
	private String photo;
	private String birth;
	private String email;
	private String tel;
	private String address;
	private String veteranReason;
	private String updateDate;
	private String resumeSubmitYn;
	private String resumeDeleteDate;

	private List<CareerVO> careerList;				// 경력
	private List<SupportVO> supportList;			// 고용지원
	private List<AwardVO> awardList;				// 수상
	private List<MyExperienceVO> myExperienceList;	// 보유경험
	private List<MySkillVO> mySkillList;			// 보유기술
	private List<MyLicenseVO> myLicenseList;		// 보유자격
	private List<IntroductionVO> introductionList;	// 자기소개서
	private List<LanguageSkillVO> languageSkillList;// 어학
	private List<PortfolioVO> portfolioList;		// 포트폴리오
	private List<MilitaryVO> militaryList;			// 병역
	private List<EducationVO> educationList;		// 학력
	private MemberVO member;
	private List<JobVO> joblist;
	private List<TopJobVO> topjoblist;
	

}
