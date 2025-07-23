package kr.or.ddit.member.recruitment.searchNotice.cityNotice.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.mapper.recruitment.RecruitmentNoticeMapper;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberSearchCityNoticeAjaxServiceImpl implements MemberSearchCityNoticeAjaxService {
	
	private final RecruitmentNoticeMapper noticeMapper;
	private final CodeMapProvider codeMapProvider;

	@Override
	public List<RecruitmentNoticeVO> readRecruitList() {
		List<RecruitmentNoticeVO> list = noticeMapper.readRecruitmentNoticeList();

        for (RecruitmentNoticeVO notice : list) {
            notice.setCityCodeName(codeMapProvider.getCityName(notice.getCityCode()));
            notice.setDistrictCodeName(codeMapProvider.getDistrictName(notice.getDistrictCode()));
            notice.setJobCodeName(codeMapProvider.getJobName(notice.getJobCode()));
            notice.setYearCodeName(codeMapProvider.getCodeName(notice.getYearCode()));
        }

        return list;
    }

}

