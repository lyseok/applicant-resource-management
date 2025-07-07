package kr.or.ddit.company.recruitment.newempolyee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentNoticeMapper;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyRecruitmentNoticeServiceImpl implements CompanyRecruitmentNoticeService {

	private final CompanyMapper comMapper;
	private final RecruitmentNoticeMapper rnMapper;
	
	@Override
	public List<RecruitmentNoticeVO> readRecruitmentNoticeList() {
		// 게시글 전체를 읽는것
		return rnMapper.readRecruitmentNoticeList();
	}
	
	@Override
	public Optional<RecruitmentNoticeVO> realTimeRecruitment(String JobCode){
	  
	    return rnMapper.realTimeRecruitment();
	}

	@Override
	public List<RecruitmentNoticeVO> salaryRecruitment() {
		// 연봉별 정렬
		return rnMapper.salaryRecruitment();
	}

	@Override
	public RecruitmentNoticeVO selectliveRecruitmentDetail(String recruitmentNo) {
		// 공채정보 전부
		return rnMapper.selectliveRecruitmentDetail(recruitmentNo);
	}
	

}
