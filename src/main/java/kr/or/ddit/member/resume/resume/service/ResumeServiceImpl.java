package kr.or.ddit.member.resume.resume.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import kr.or.ddit.mapper.resume.SubIntroductionMapper;
import kr.or.ddit.mapper.resume.SupportMapper;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
	private final ResumeMapper resumeMapper;				// 이력서
	private final CareerMapper careerMapper;				// 경력
	private final SupportMapper supportMapper;				// 고용지원
	private final AwardMapper awardMapper;					// 수상
	private final MyExperienceMapper myExperienceMapper;	// 보유경험
	private final MySkillMapper mySkillMapper;				// 보유 기술
	private final MyLicenseMapper myLicenseMapper;			// 보유 자격
	private final SubIntroductionMapper subIntroductionMapper;	// 자기소개서 > 이력서에서 insert하면 제출자소서에도 추가로 insert 해주는건지?
	private final LanguageSkillMapper languageSkillMapper;	// 어학
	private final PortfolioMapper portfolioMapper;			// 포트폴리오
	private final MilitaryMapper militaryMapper;			// 병역
	private final EducationMapper educationMapper;			// 학력
	
	
	@Override
	public List<ResumeVO> readResumeList() {
		return resumeMapper.selectResumeList();
	}

	@Override
	public ResumeVO readResumeDetail(String no) {
		return resumeMapper.selectResumeDetail(no);
	}

	@Override
	@Transactional
	public void createResume(ResumeVO vo) {
		resumeMapper.insertResume(vo);
		
		// 사용자가 경력을 입력한 경우
		if(ResumeVO.getCareerList() != null) {
			
		}
		
		// 사용자가 학력을 입력한경우
	}

	@Override
	public void editResume(ResumeVO vo) {
		resumeMapper.updateResume(vo);
	}

	@Override
	public void removeResume(String no) {
		resumeMapper.deleteResume(no);
	}

}
