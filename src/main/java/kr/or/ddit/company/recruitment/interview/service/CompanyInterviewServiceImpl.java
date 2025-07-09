package kr.or.ddit.company.recruitment.interview.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.mapper.recruitment.InterviewMapper;
import kr.or.ddit.vo.recruitment.InterviewVO;
import kr.or.ddit.vo.recruitment.RecruitProcessVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyInterviewServiceImpl implements CompanyInterviewService{
	private final InterviewMapper mapper;
	private final CodeMapProvider codeMapProvider;
	
	@Override
	public List<InterviewVO> readInterviewList() {
		List<InterviewVO> interviewList = mapper.selectInterviewList();
		for(InterviewVO inteVo : interviewList) {
			setCodeName(inteVo);
		}
		return interviewList;
	}

	@Override
	public InterviewVO readInterview(String interviewNo) {
		InterviewVO inteVo = mapper.selectInterview(interviewNo);
		setCodeName(inteVo);
		
		return inteVo;
	}

	@Override
	public int createInterview(InterviewVO vo) {
		return mapper.insertInterview(vo);
	}

	@Override
	public int modifyInterview(InterviewVO vo) {
		return mapper.updateInterview(vo);
	}

	@Override
	public int removeInterview(String inteviewNo) {
		return mapper.deleteInterview(inteviewNo);
	}
	
	private void setCodeName(InterviewVO inteVo) {
		RecruitmentNoticeVO notiVo = inteVo.getRecruitProcess().getRecruitmentNotice();
		RecruitProcessVO reprVo = inteVo.getRecruitProcess();
		
		reprVo.setRecruitProcessTypeName(codeMapProvider.getCodeName(reprVo.getRecruitProcessType()));
		
		String district = codeMapProvider.getDistrictName(notiVo.getDistrictCode());
		notiVo.setDistrictCodeName(district);
		
		String job = codeMapProvider.getJobName(notiVo.getJobCode());
		notiVo.setJobCodeName(job);
		
		String city = codeMapProvider.getCityName(notiVo.getCityCode());
		notiVo.setCityCodeName(city);
		
		String year = codeMapProvider.getCodeName(notiVo.getYearCode());
		notiVo.setYearCodeName(year);
	}
	
}
