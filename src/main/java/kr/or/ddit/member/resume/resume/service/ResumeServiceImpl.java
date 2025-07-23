package kr.or.ddit.member.resume.resume.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.exception.DataInsertException;
import kr.or.ddit.common.file.S3Uploader;
import kr.or.ddit.common.file.service.FileService;
import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.mapper.common.MemberMapper;
import kr.or.ddit.mapper.project.PrjAplcntMapper;
import kr.or.ddit.mapper.recruitment.ApplicantMapper;
import kr.or.ddit.mapper.resume.AwardMapper;
import kr.or.ddit.mapper.resume.CareerMapper;
import kr.or.ddit.mapper.resume.EducationMapper;
import kr.or.ddit.mapper.resume.LanguageSkillMapper;
import kr.or.ddit.mapper.resume.MilitaryMapper;
import kr.or.ddit.mapper.resume.MyExperienceMapper;
import kr.or.ddit.mapper.resume.MyLicenseMapper;
import kr.or.ddit.mapper.resume.MySkillMapper;
import kr.or.ddit.mapper.resume.PortfolioMapper;
import kr.or.ddit.mapper.resume.ResumeMapper;
import kr.or.ddit.mapper.resume.SpecialtyMapper;
import kr.or.ddit.mapper.resume.IntroductionMapper;
import kr.or.ddit.mapper.resume.SupportMapper;
import kr.or.ddit.member.resume.exception.ResumeNotFoundException;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.project.PrjAplcntVO;
import kr.or.ddit.vo.recruitment.ApplicantVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import kr.or.ddit.vo.resume.AwardVO;
import kr.or.ddit.vo.resume.CareerVO;
import kr.or.ddit.vo.resume.EducationVO;
import kr.or.ddit.vo.resume.IntroductionVO;
import kr.or.ddit.vo.resume.LanguageSkillVO;
import kr.or.ddit.vo.resume.MilitaryVO;
import kr.or.ddit.vo.resume.MyExperienceVO;
import kr.or.ddit.vo.resume.MyLicenseVO;
import kr.or.ddit.vo.resume.MySkillVO;
import kr.or.ddit.vo.resume.PortfolioVO;
import kr.or.ddit.vo.resume.ResumeVO;
import kr.or.ddit.vo.resume.SpecialtyVO;
import kr.or.ddit.vo.resume.SupportVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
	private final CodeMapProvider provider;
	
	private final S3Uploader s3Uploader;	// 파일 패스
	private final FileService fileService;	// 파일 업로드
	
	private final ResumeMapper resumeMapper; // 이력서
	private final CareerMapper careerMapper; // 경력
	private final SupportMapper supportMapper; // 고용지원
	private final AwardMapper awardMapper; // 수상
	private final MyExperienceMapper myExperienceMapper; // 보유경험
	private final MySkillMapper mySkillMapper; // 보유 기술
	private final MyLicenseMapper myLicenseMapper; // 보유 자격
	private final IntroductionMapper introductionMapper; // 자기소개서 > 이력서에서 insert하면 제출자소서에도 추가로 insert 해주는건지?
	private final LanguageSkillMapper languageSkillMapper; // 어학
	private final PortfolioMapper portfolioMapper; // 포트폴리오
	private final MilitaryMapper militaryMapper; // 병역
	private final EducationMapper educationMapper; // 학력
	private final SpecialtyMapper specialtyMapper; // 학력 - 하위 전공

	// 프로젝트 지원
	private final PrjAplcntMapper prjAplcntMapper;
	
	// 입사 지원
	private final MemberMapper memberMapper;
	private final ApplicantMapper applicantMapper;
	

	// 리스트 조회
	@Override
	public List<Map<String, Object>> readResumeList(String id) {
		List<Map<String, Object>> resumeList = resumeMapper.selectResumeList(id);

		return resumeList;
	}

	// 상세조회
	@Override
	public ResumeVO readResumeDetail(ResumeVO vo) {
		ResumeVO resume = resumeMapper.selectResumeDetail(vo);
		if (resume == null) {
			throw new ResumeNotFoundException("이력서를 찾을 수 없습니다. (ID: " + vo.getResumeNo() + ")");
		}
		resume.setCareerList(careerMapper.selectCareerList(resume.getResumeNo()));
		resume.setSupportList(supportMapper.selectSupportList(resume.getResumeNo()));
		resume.setAwardList(awardMapper.selectAwardList(resume.getResumeNo()));
		resume.setMyExperienceList(myExperienceMapper.selectMyExperienceList(resume.getResumeNo()));
		resume.setMySkillList(mySkillMapper.selectMySkillList(resume.getResumeNo()));
		resume.setMyLicenseList(myLicenseMapper.selectMyLicenseList(resume.getResumeNo()));
		resume.setLanguageSkillList(languageSkillMapper.selectLanguageSkillList(resume.getResumeNo()));
		resume.setPortfolioList(portfolioMapper.selectPortfolioList(resume.getResumeNo()));
		resume.setMilitaryList(militaryMapper.selectMilitaryList(resume.getResumeNo()));
		resume.setEducationList(educationMapper.selectEducationList(resume.getResumeNo()));
		resume.setIntroduction(introductionMapper.selectIntroductionDetail(resume.getIntroductionNo()));
		log.info("자소서 잘 담겼누?? {}", resume.getIntroduction());
		setCodeName(resume);
		return resume;
	}
	
	@Override
	@Transactional
	public int createResumeWithPhoto(ResumeVO resumeVO, MultipartFile photo) {		
		// 🔽 1. 파일 S3 업로드 처리
		if (photo != null && !photo.isEmpty()) {
			String uploadedUrl;
			try {
				uploadedUrl = s3Uploader.upload(photo);
				fileService.saveUploadFile(photo, uploadedUrl, 3); // fileSource는 예: 2 (이력서 사진)
				
				log.info("uploadedUrl >>>>>>>>>>>> {}", uploadedUrl);
				// resumeVO에 업로드된 경로 저장 (DB에 넣을 값)
				resumeVO.setPhoto(uploadedUrl); // 필드가 없다면 ResumeVO에 추가 필요
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		int resumeCnt = resumeMapper.insertResume(resumeVO);
		log.info("photo >>>>>>>>>>>>>>>>>>>>>> {}", photo);
		

		// 경력
		if (resumeVO.getCareerList() != null) {
			List<CareerVO> filteredList = resumeVO.getCareerList().stream()
					.filter(career -> career.getJobCode() != null && !career.getJobCode().trim().isEmpty())
					.collect(Collectors.toList());

			for (CareerVO career : filteredList) {
				career.setResumeNo(resumeVO.getResumeNo()); // 이력서번호(부모 pk) 세팅;
				log.info("");
				careerMapper.insertCareer(career);
			}
		}

		// 고용지원
		if (resumeVO.getSupportList() != null) {
			for (SupportVO support : resumeVO.getSupportList()) {
				support.setResumeNo(resumeVO.getResumeNo()); // 이력서번호(부모 pk) 세팅;
				supportMapper.insertSupport(support);
			}
		}

		// 수상
		if (resumeVO.getAwardList() != null) {
			for (AwardVO award : resumeVO.getAwardList()) {
				award.setResumeNo(resumeVO.getResumeNo());
				awardMapper.insertAward(award);
			}
		}

		// 보유경험
		if (resumeVO.getMyExperienceList() != null) {
			for (MyExperienceVO myExperience : resumeVO.getMyExperienceList()) {
				myExperience.setResumeNo(resumeVO.getResumeNo());
				myExperienceMapper.insertMyExperience(myExperience);
			}
		}

		// 보유기술
		if (resumeVO.getMySkillList() != null) {
			for (MySkillVO mySkill : resumeVO.getMySkillList()) {
				mySkill.setResumeNo(resumeVO.getResumeNo());
				mySkillMapper.insertMySkill(mySkill);
			}
		}

		// 보유자격
		if (resumeVO.getMyLicenseList() != null) {
			for (MyLicenseVO myLicense : resumeVO.getMyLicenseList()) {
				myLicense.setResumeNo(resumeVO.getResumeNo());
				myLicenseMapper.insertMyLicense(myLicense);
			}
		}

		// 어학
		if (resumeVO.getLanguageSkillList() != null) {
			for (LanguageSkillVO languageSkill : resumeVO.getLanguageSkillList()) {
				languageSkill.setResumeNo(resumeVO.getResumeNo());
				languageSkillMapper.insertLanguageSkill(languageSkill);
			}
		}

		// 포트폴리오
		if (resumeVO.getPortfolioList() != null) {
			for (PortfolioVO portfolio : resumeVO.getPortfolioList()) {
				portfolio.setResumeNo(resumeVO.getResumeNo());
				portfolioMapper.insertPortfolio(portfolio);
			}
		}

		// 병역
		if (resumeVO.getMilitaryList() != null) {
			for (MilitaryVO military : resumeVO.getMilitaryList()) {
				military.setResumeNo(resumeVO.getResumeNo());
				militaryMapper.insertMilitary(military);
			}
		}

		// 사용자가 학력을 입력한경우
		if (resumeVO.getEducationList() != null) {
			for (EducationVO education : resumeVO.getEducationList()) {
				education.setResumeNo(resumeVO.getResumeNo());
				int cnt = educationMapper.insertEducation(education); // educationNo 세팅

				// 3. 전공 insert (specialtyList)
				if (education.getSpecialtyList() != null) {
					for (SpecialtyVO specialty : education.getSpecialtyList()) {
						specialty.setEducationNo(education.getEducationNo());
						specialty.setResumeNo(resumeVO.getResumeNo());
						specialtyMapper.insertSpecialty(specialty);
					}
				}
			}
		}
		return resumeCnt;
	}
	
	
	// 기존 등록 로직
	@Override
	@Transactional
	public int createResume(ResumeVO resumeVO) {
		int resumeCnt = resumeMapper.insertResume(resumeVO);
		
		// 경력
		if (resumeVO.getCareerList() != null) {
			List<CareerVO> filteredList = resumeVO.getCareerList().stream()
					.filter(career -> career.getJobCode() != null && !career.getJobCode().trim().isEmpty())
					.collect(Collectors.toList());
			
			for (CareerVO career : filteredList) {
				career.setResumeNo(resumeVO.getResumeNo()); // 이력서번호(부모 pk) 세팅;
				log.info("");
				careerMapper.insertCareer(career);
			}
		}
		
		// 고용지원
		if (resumeVO.getSupportList() != null) {
			for (SupportVO support : resumeVO.getSupportList()) {
				support.setResumeNo(resumeVO.getResumeNo()); // 이력서번호(부모 pk) 세팅;
				supportMapper.insertSupport(support);
			}
		}
		
		// 수상
		if (resumeVO.getAwardList() != null) {
			for (AwardVO award : resumeVO.getAwardList()) {
				award.setResumeNo(resumeVO.getResumeNo());
				awardMapper.insertAward(award);
			}
		}
		
		// 보유경험
		if (resumeVO.getMyExperienceList() != null) {
			for (MyExperienceVO myExperience : resumeVO.getMyExperienceList()) {
				myExperience.setResumeNo(resumeVO.getResumeNo());
				myExperienceMapper.insertMyExperience(myExperience);
			}
		}
		
		// 보유기술
		if (resumeVO.getMySkillList() != null) {
			for (MySkillVO mySkill : resumeVO.getMySkillList()) {
				mySkill.setResumeNo(resumeVO.getResumeNo());
				mySkillMapper.insertMySkill(mySkill);
			}
		}
		
		// 보유자격
		if (resumeVO.getMyLicenseList() != null) {
			for (MyLicenseVO myLicense : resumeVO.getMyLicenseList()) {
				myLicense.setResumeNo(resumeVO.getResumeNo());
				myLicenseMapper.insertMyLicense(myLicense);
			}
		}
		
		// 어학
		if (resumeVO.getLanguageSkillList() != null) {
			for (LanguageSkillVO languageSkill : resumeVO.getLanguageSkillList()) {
				languageSkill.setResumeNo(resumeVO.getResumeNo());
				languageSkillMapper.insertLanguageSkill(languageSkill);
			}
		}
		
		// 포트폴리오
		if (resumeVO.getPortfolioList() != null) {
			for (PortfolioVO portfolio : resumeVO.getPortfolioList()) {
				portfolio.setResumeNo(resumeVO.getResumeNo());
				portfolioMapper.insertPortfolio(portfolio);
			}
		}
		
		// 병역
		if (resumeVO.getMilitaryList() != null) {
			for (MilitaryVO military : resumeVO.getMilitaryList()) {
				military.setResumeNo(resumeVO.getResumeNo());
				militaryMapper.insertMilitary(military);
			}
		}
		
		// 사용자가 학력을 입력한경우
		if (resumeVO.getEducationList() != null) {
			for (EducationVO education : resumeVO.getEducationList()) {
				education.setResumeNo(resumeVO.getResumeNo());
				int cnt = educationMapper.insertEducation(education); // educationNo 세팅
				
				// 3. 전공 insert (specialtyList)
				if (education.getSpecialtyList() != null) {
					for (SpecialtyVO specialty : education.getSpecialtyList()) {
						specialty.setEducationNo(education.getEducationNo());
						specialty.setResumeNo(resumeVO.getResumeNo());
						specialtyMapper.insertSpecialty(specialty);
					}
				}
			}
		}
		return resumeCnt;
	}

	// 논리적 삭제
	@Override
	public int editResumeRemove(ResumeVO resumeVO) {
		int result = resumeMapper.updateResumeDelete(resumeVO);
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", result);
		if(result > 0) {
			 return result;
		} else {
			throw new IllegalArgumentException("이력서를 삭제 실패했습니다.");
		}
	}

	
	// 이력서 수정, 근데 이제 삭제를 먼저 하고 insert 하는! resumeForm 입력 순서대로 정렬!
	@Override
	@Transactional
	public int editResume(
		ResumeVO resumeVO
		, MultipartFile photo
	) {
		// 이력서 번호가 없으면 리턴
		if(resumeVO.getResumeNo() == null) {
			return 0;
		}	
		// 🔽 1. 파일 S3 업로드 처리
		if (photo != null && !photo.isEmpty()) {
			String uploadedUrl;
			try {
				uploadedUrl = s3Uploader.upload(photo);
				fileService.saveUploadFile(photo, uploadedUrl, 3); // fileSource는 예: 2 (이력서 사진)
				
				log.info("uploadedUrl >>>>>>>>>>>> {}", uploadedUrl);
				// resumeVO에 업로드된 경로 저장 (DB에 넣을 값)
				resumeVO.setPhoto(uploadedUrl); // 필드가 없다면 ResumeVO에 추가 필요
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 기본정보는 여러개가 아니라 update로 처리해도 ㄱㅊ, 자소서는 이떄 introductionNO로 들어가고 추후js로 비동기 요청보냄!
		int result = resumeMapper.updateResume(resumeVO);
		// log.info("이력서 업데이트 > 리슘 서비스 - 자소서 번호 확인 !!! ▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶" + resumeVO.getIntroductionNo());


		// 학력 >> 하위테이블(specialty) 먼저 지우고 학력 삭제 가능 + 같은 이력서 번호로 등록된 기존 데이터 전부 지우고 사용자가 보낸 객체로 다시 인서트 작업
		if (resumeVO.getEducationList() != null) {
			specialtyMapper.deleteSpecialty(resumeVO.getResumeNo());
			educationMapper.deleteEducation(resumeVO.getResumeNo());
			for (EducationVO education : resumeVO.getEducationList()) {
				education.setResumeNo(resumeVO.getResumeNo());
				educationMapper.insertEducation(education);
				// 3. 전공 insert (specialtyList)
				if (education.getSpecialtyList() != null) {
					for (SpecialtyVO specialty : education.getSpecialtyList()) {						
						specialty.setEducationNo(education.getEducationNo());
						specialty.setResumeNo(resumeVO.getResumeNo());
						specialtyMapper.insertSpecialty(specialty);
					}
				}
			}
		}

		// 포트폴리오
		if (resumeVO.getPortfolioList() != null || resumeVO.getPortfolioList() == null) {
			log.info("{}", resumeVO.getPortfolioList());
			portfolioMapper.deletePortfolio(resumeVO.getResumeNo());
			if(resumeVO.getPortfolioList() != null ) {
				for (PortfolioVO portfolio : resumeVO.getPortfolioList()) {
	
					portfolio.setResumeNo(resumeVO.getResumeNo());
					portfolioMapper.insertPortfolio(portfolio);
				}
			}
		}
		
		// 경력 - 무조건 삭제 후, 있으면 insert (stream 때문에 delete를 앞으로 뻄)
		careerMapper.deleteCareer(resumeVO.getResumeNo());

		List<CareerVO> careerList = resumeVO.getCareerList();
		if (careerList != null && !careerList.isEmpty()) {
			List<CareerVO> filteredList = careerList.stream()
				.filter(c -> c.getJobCode() != null && !c.getJobCode().trim().isEmpty())
				.collect(Collectors.toList());

			for (CareerVO career : filteredList) {
				career.setResumeNo(resumeVO.getResumeNo());
				careerMapper.insertCareer(career);
			}
		}


		// 보유기술
		if (resumeVO.getMySkillList() != null || resumeVO.getMySkillList() == null) {
			mySkillMapper.deleteMySkill(resumeVO.getResumeNo());
			if(resumeVO.getMySkillList() != null) {
				for (MySkillVO mySkill : resumeVO.getMySkillList()) {
	
					mySkill.setResumeNo(resumeVO.getResumeNo());
					mySkillMapper.insertMySkill(mySkill);
				}
			}
		}

		// 보유경험
		if (resumeVO.getMyExperienceList() != null || resumeVO.getMyExperienceList() == null) {
			myExperienceMapper.deleteMyExperience(resumeVO.getResumeNo());
			if(resumeVO.getMyExperienceList() != null) {
				for (MyExperienceVO myExperience : resumeVO.getMyExperienceList()) {
	
					myExperience.setResumeNo(resumeVO.getResumeNo());
					myExperienceMapper.insertMyExperience(myExperience);
				}
			}
		}
		
		// 고용지원
		if (resumeVO.getSupportList() != null || resumeVO.getSupportList() == null) {
			supportMapper.deleteSupport(resumeVO.getResumeNo());
			if(resumeVO.getSupportList() != null) {
				for (SupportVO support : resumeVO.getSupportList()) {
					
					support.setResumeNo(resumeVO.getResumeNo());
					supportMapper.insertSupport(support);
				}
			}
		}

		// 보유자격
		if (resumeVO.getMyLicenseList() != null || resumeVO.getMyLicenseList() == null) {
			myLicenseMapper.deleteMyLicense(resumeVO.getResumeNo());
			if(resumeVO.getMyLicenseList() != null) {
				for (MyLicenseVO myLicense : resumeVO.getMyLicenseList()) {	
					myLicense.setResumeNo(resumeVO.getResumeNo());
					myLicenseMapper.insertMyLicense(myLicense);
				}
			}
		}

		// 어학
		if (resumeVO.getLanguageSkillList() != null || resumeVO.getLanguageSkillList() == null) {
			languageSkillMapper.deleteLanguageSkill(resumeVO.getResumeNo());
			if(resumeVO.getLanguageSkillList() != null) {
				for (LanguageSkillVO languageSkill : resumeVO.getLanguageSkillList()) {
					languageSkill.setResumeNo(resumeVO.getResumeNo());
					languageSkillMapper.insertLanguageSkill(languageSkill);
				}
			}
		}

		// 수상
		if (resumeVO.getAwardList() != null || resumeVO.getAwardList() == null) {
			awardMapper.deleteAward(resumeVO.getResumeNo());
			if(resumeVO.getAwardList() != null) {
				for (AwardVO award : resumeVO.getAwardList()) {
					award.setResumeNo(resumeVO.getResumeNo());
					awardMapper.insertAward(award);
				}
			}
		}

		// 병역
		if (resumeVO.getMilitaryList() != null || resumeVO.getMilitaryList() == null) {
			militaryMapper.deleteMilitary(resumeVO.getResumeNo());
			if(resumeVO.getMilitaryList() != null) {
				for (MilitaryVO military : resumeVO.getMilitaryList()) {
					military.setResumeNo(resumeVO.getResumeNo());
					militaryMapper.insertMilitary(military);
				}
			}
		}

		log.info("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ 이력서 수정 업데이트 결과 행 수: {}", result);

		return result;
	}

	@Override
	public int removeResume(String no) {
		return resumeMapper.deleteResume(no);
	}

	@Transactional
	@Override
	public void applicantCopyLogic(PrjAplcntVO prjAplcnt) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		prjAplcnt.setUserId(username);
		log.info("================>>>>>>>>>> {}", prjAplcnt);

		String aplcntNo = prjAplcntMapper.duplicationPrjRcrtPsncnt(prjAplcnt);
		if (aplcntNo != null && !aplcntNo.isBlank()) {
			throw new DataInsertException("이미 해당 프로젝트에 지원하셨습니다.");
		}

		ResumeVO beforeVo = new ResumeVO();
		beforeVo.setUserId(username);
		beforeVo.setResumeNo(prjAplcnt.getResumeNo());

		log.info("================>>>>>>>>>> {}", beforeVo);

		ResumeVO copyVo = readResumeDetail(beforeVo);
		copyVo.setResumeSubmitYn("Y");
		int res = createResume(copyVo);
		if (res == 0) {
			throw new DataInsertException("이력서 복사 실패");
		}
		prjAplcnt.setResumeNo(copyVo.getResumeNo());
		prjAplcnt.setAplcntStatusCode("PRST-001"); // 지원완료

		res = prjAplcntMapper.insertPrjRcrtPsncnt(prjAplcnt);
		if (res == 0) {
			throw new DataInsertException("프로젝트 지원 실패");
		}

	}

	@Transactional
	@Override
	public void recruitApplicate(ApplicantVO applicant){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		MemberVO memVo= memberMapper.selectMemberById(username);
		applicant.setUserId(username);
		log.info("================>>>>>>>>>> {}", applicant);

		String applicantNo = applicantMapper.duplicationApplicant(applicant);
		if (applicantNo != null && !applicantNo.isBlank()) {
			throw new DataInsertException("이미 해당 채용공고에 지원하셨습니다.");
		}

		ResumeVO beforeVo = new ResumeVO();
		beforeVo.setUserId(username);
		beforeVo.setResumeNo(applicant.getResumeNo());
		
		ResumeVO copyVo = readResumeDetail(beforeVo);
		copyVo.setResumeSubmitYn("Y");
		int res = createResume(copyVo);
		if (res == 0) {
			throw new DataInsertException("이력서 복사 실패");
		}
		applicant.setResumeNo(copyVo.getResumeNo());

		res = applicantMapper.insertApplicant(applicant);
		if (res == 0) {
			throw new DataInsertException("채용공고 지원 실패");
		}
	}

	@Override
	public int readUserResumeNoCount(String userId) {
		return resumeMapper.selectUserResumeNoCount(userId);
	}


	@Override
	public List<Map<String, Object>> readResumeSearch(ResumeVO vo) {
		List<Map<String, Object>> resumeList = resumeMapper.selectResumeSearch(vo); 
		return resumeList;
	}
	
	
	// 공통 코드 한글 맵핑
	private void setCodeName(ResumeVO resumeVO) {
		// 리스트 꺼내기
		List<CareerVO> carrerList = resumeVO.getCareerList();
		List<SupportVO> supportList = resumeVO.getSupportList();
		List<MyExperienceVO> myExperienceList = resumeVO.getMyExperienceList();
		List<LanguageSkillVO> languageSkillList = resumeVO.getLanguageSkillList();
		List<MilitaryVO> militaryList = resumeVO.getMilitaryList();
		List<EducationVO> educationList = resumeVO.getEducationList();
		educationList.forEach(i -> {
			List<SpecialtyVO> list = i.getSpecialtyList();
			list.forEach(ii -> {
				ii.setSubMajorCodeName(provider.getCodeName(ii.getSubMajorCode()));
//				log.info("{}", ii.getSubMajorCode());
			});
		});

		// 데이터 setting
		for (CareerVO car : carrerList) {
			car.setJobCodeName(provider.getJobName(car.getJobCode()));
			car.setJobGradeCodeName(provider.getCodeName(car.getJobGradeCode()));
			car.setPositionCodeName(provider.getCodeName(car.getPositionCode()));
			car.setCareerYearName(provider.getCodeName(car.getCareerYear()));
//			log.info("JobCode ------->>> {}", car.getJobCodeName());
//			log.info("JobGradeCode ------->>> {}", car.getJobGradeCodeName());
//			log.info("PositionCode ------->>> {}", car.getPositionCodeName());
//			log.info("CareerYear ------->>> {}", car.getCareerYearName());
//			log.info(" ");
		}
//		log.info(" ");

		for (SupportVO sup : supportList) {
			sup.setDisabilityCodeName(provider.getCodeName(sup.getDisabilityCode()));
			sup.setDisabilityLevelCodeName(provider.getCodeName(sup.getDisabilityLevelCode()));
//			log.info("Disability ------->>> {}", sup.getDisabilityCodeName());
//			log.info("DisabilityLevel ------->>> {}", sup.getDisabilityLevelCodeName());
//			log.info(" ");
		}
//		log.info(" ");

		for (MyExperienceVO exp : myExperienceList) {
			exp.setExpCodeName(provider.getCodeName(exp.getExpCode()));
//			log.info("ExpCode ------->>> {}", exp.getExpCodeName());
		}
//		log.info(" ");

		for (LanguageSkillVO lang : languageSkillList) {
			lang.setLanguageCodeName(provider.getCodeName(lang.getLanguageCode()));
			lang.setLanguageExamCodeName(provider.getCodeName(lang.getLanguageExamCode()));
			lang.setLanguageExamLevelCodeName(provider.getCodeName(lang.getLanguageExamLevelCode()));
//			log.info("LanguageCode ------->>> {}", lang.getLanguageCodeName());
//			log.info("LanguageExamCode ------->>> {}", lang.getLanguageExamCodeName());
//			log.info("LanguageExamLevelCode ------->>> {}", lang.getLanguageExamLevelCodeName());
//			log.info(" ");
		}
//		log.info(" ");

		for (MilitaryVO mil : militaryList) {
			mil.setServiceCategoryCodeName(provider.getCodeName(mil.getServiceCategoryCode()));
			mil.setMilitaryTypeCodeName(provider.getCodeName(mil.getMilitaryTypeCode()));
			mil.setMilitaryRankCodeName(provider.getCodeName(mil.getMilitaryRankCode()));
			mil.setDischargeCodeName(provider.getCodeName(mil.getDischargeCode()));
//			log.info("ServiceCategory ------->>> {}", mil.getServiceCategoryCode());
//			log.info("MilitaryType ------->>> {}", mil.getMilitaryTypeCode());
//			log.info("MilitaryRank ------->>> {}", mil.getMilitaryTypeCode());
//			log.info("Discharge ------->>> {}", mil.getMilitaryTypeCode());
//			log.info(" ");
		}
//		log.info(" ");

		for (EducationVO edu : educationList) {
//			edu.setDepartmentCode(provider.getCodeName(edu.getDepartmentCode()));
			edu.setHighestEducationCodeName(provider.getCodeName(edu.getHighestEducationCode()));
			edu.setGraduateYnName(provider.getCodeName(edu.getGraduateYn()));
			edu.setLocationName(provider.getDistrictName(edu.getLocation()));
//			log.info("MilitaryRank ------->>> {}", edu.getDepartmentCode());
//			log.info("Discharge ------->>> {}", edu.getHighestEducationCode());
			log.info("GraduateYn ------->>> {}", edu.getGraduateYn());
		}
//		log.info(" ");

	}

}
