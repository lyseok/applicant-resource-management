package kr.or.ddit.vo.resume;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kr.or.ddit.common.annotation.PastString;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.JobVO;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.common.TopJobVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "resumeNo")
public class ResumeVO implements Serializable {

	@Size(max = 20)
	private String resumeNo; // 이력서 고유번호

	@NotBlank
	private String userId; // 사용자 ID

	@Size(max = 100)
	private String resumeName; // 이력서 제목

	@Pattern(regexp = "Y|N")
	private String resumeMainYn; // 대표 이력서 여부

	@Size(max = 50)
	private String userName; // 사용자 이름

	@Size(max = 200)
	private String photo; // 프로필 사진 경로

	@PastString(message = "생년월일은 과거 날짜여야 합니다.")
	private String birth; // 생년월일

	@Email
	@Size(max = 100)
	private String email; // 이메일

	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
	private String tel; // 연락처

	@Size(max = 200)
	private String address; // 주소

	@Size(max = 200)
	private String veteranReason; // 보훈 대상 사유

	@PastString(message = "수정일자는 과거 날짜여야 합니다.")
	private String updateDate; // 최종 수정일

	@Pattern(regexp = "Y|N")
	private String resumeSubmitYn; // 이력서 제출 여부

	@PastString(message = "삭제일자는 과거 날짜여야 합니다.")
	private String resumeDeleteDate; // 삭제일 (논리삭제)

	@Size(max = 20)
	private String introductionNo; // 자기소개서 고유 번호 (다른 테이블 참조용)

	@Valid
	private List<CareerVO> careerList; // 경력
	@Valid
	private List<SupportVO> supportList; // 고용지원
	@Valid
	private List<AwardVO> awardList; // 수상
	@Valid
	private List<MyExperienceVO> myExperienceList; // 보유경험
	@Valid
	private List<MySkillVO> mySkillList; // 보유기술
	@Valid
	private List<MyLicenseVO> myLicenseList; // 보유자격
	@Valid
	private IntroductionVO introduction; // 자기소개서
	@Valid
	private List<LanguageSkillVO> languageSkillList;// 어학
	@Valid
	private List<PortfolioVO> portfolioList; // 포트폴리오
	@Valid
	private List<MilitaryVO> militaryList; // 병역
	@Valid
	private List<EducationVO> educationList; // 학력
	

	private CompanyVO company;						// 기업정보
	private MemberVO member;
	private List<JobVO> joblist;
	private List<TopJobVO> topjoblist;

}
