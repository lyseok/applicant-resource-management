package kr.or.ddit.member.recruitment.recruitView.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.dto.RecruitViewDTO;
import kr.or.ddit.mapper.recruitment.RecruitViewMapper;
import kr.or.ddit.vo.recruitment.RecruitViewVO;
import kr.or.ddit.vo.resume.CareerVO;
import kr.or.ddit.vo.resume.EducationVO;
import kr.or.ddit.vo.resume.LanguageSkillVO;
import kr.or.ddit.vo.resume.MilitaryVO;
import kr.or.ddit.vo.resume.MyExperienceVO;
import kr.or.ddit.vo.resume.ResumeVO;
import kr.or.ddit.vo.resume.SpecialtyVO;
import kr.or.ddit.vo.resume.SupportVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecruitViewServiceImpl implements RecruitViewService {
	private final RecruitViewMapper mapper;
	private final CodeMapProvider provider;
	
	@Override
	public List<RecruitViewDTO> readRecruitViewList(String id) {
		List<RecruitViewDTO> list = mapper.selectRecruitViewList(id);
		list.forEach(vo ->{
			setCodeName(vo);			
		});
		return list;
	}

	@Override
	public int createtRecruitView(RecruitViewVO vo) {
		return mapper.insertRecruitView(vo);
	}

	@Override
	public List<RecruitViewDTO> readSearchRecruitViewList(String keyword) {
		List<RecruitViewDTO> list = mapper.selectSearchRecruitViewList(keyword);
		list.forEach(vo ->{
			setCodeName(vo);			
		});
		return list;
	}
	

	// 공통 코드 한글 맵핑
	private void setCodeName(RecruitViewDTO vo) {
		vo.setCityCodeName(provider.getCityName(vo.getCityCode()));
		vo.setDistrictCodeName(provider.getDistrictName(vo.getDistrictCode()));
		vo.setYearCodeName(provider.getCodeName(vo.getYearCode()));
	}

}
