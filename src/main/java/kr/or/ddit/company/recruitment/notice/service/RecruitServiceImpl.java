package kr.or.ddit.company.recruitment.notice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.mapper.recruitment.RecruitmentEducationMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentNoticeMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentPositionMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentSkillmapper;
import kr.or.ddit.vo.recruitment.InterviewVO;
import kr.or.ddit.vo.recruitment.RecruitProcessVO;
import kr.or.ddit.vo.recruitment.RecruitmentEducationVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import kr.or.ddit.vo.recruitment.RecruitmentPositionVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitServiceImpl implements RecruitService {

	private final RecruitmentNoticeMapper noticeMapper;
	private final CodeMapProvider codeMapProvider;

	@Override
	public void createRecruitment(RecruitmentNoticeVO recruit) {
		noticeMapper.insertRecruitmentNotice(recruit);
	}

	@Override
	public void modifyRecruitment(RecruitmentNoticeVO recruit) {
		noticeMapper.updateRecruitmentNotice(recruit);

	}

	@Override
	public void deleteRecruitment(String recruimentNo) {
		noticeMapper.deleteRecruitmentNotice(recruimentNo);

	}

	@Override
	public List<RecruitmentNoticeVO> readRecruitList() {
		List<RecruitmentNoticeVO> notiList = noticeMapper.readRecruitmentNoticeList();
		for(RecruitmentNoticeVO notiVo : notiList) {
			setCodeName(notiVo);
		}
		return notiList;
	}

	@Override
	public RecruitmentNoticeVO readRecruitNotice(String recruitNo) {
		RecruitmentNoticeVO notiVo = noticeMapper.selectliveRecruitmentDetail(recruitNo);
		setCodeName(notiVo);
		return notiVo;
	}
	
	private void setCodeName(RecruitmentNoticeVO notiVo) {
		List<RecruitmentPositionVO> positionList = notiVo.getPositionList();
		if(notiVo.getPositionList() != null) {
			for(RecruitmentPositionVO position : positionList) {
				String cmnCode = codeMapProvider.getCodeName(position.getCodeDetailNo()); 
				position.setCodeDetailName(cmnCode);
			}			
		}
		RecruitmentEducationVO education = notiVo.getEducation();
		if(notiVo.getEducation() != null) {			
			String cmnCode = codeMapProvider.getCodeName(education.getCodeDetailNo());
			education.setCodeDetailName(cmnCode);
		}
		
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
