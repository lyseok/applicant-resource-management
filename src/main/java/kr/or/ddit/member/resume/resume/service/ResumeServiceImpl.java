package kr.or.ddit.member.resume.resume.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.AwardMapper;
import kr.or.ddit.mapper.resume.CareerMapper;
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
	private final ResumeMapper resumeMapper;
	private final CareerMapper careerMapper;
	private final SupportMapper supportMapper;
	private final AwardMapper awardMapper;
	private final MyExperienceMapper myExperienceMapper;
	private final MySkillMapper mySkillMapper;
	private final MyLicenseMapper myLicenseMapper;
	private final SubIntroductionMapper subIntroductionMapper;
	private final LanguageSkillMapper languageSkillMapper;
	private final PortfolioMapper portfolioMapper;
	private final MilitaryMapper militaryMapper;
	
	
	
	@Override
	public List<ResumeVO> readResumeList() {
		return resumeMapper.selectResumeList();
	}

	@Override
	public ResumeVO readResumeDetail(String no) {
		return resumeMapper.selectResumeDetail(no);
	}

	@Override
	public void createResume(ResumeVO vo) {
		resumeMapper.insertResume(vo);
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
