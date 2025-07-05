package kr.or.ddit.recruitment.live.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.recruitment.RecruitmentNoticeMapper;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitmentNoticeServiceImpl implements RecruitmentNoticeService {

	private final RecruitmentNoticeMapper mapper;
	
	@Override
	public List<RecruitmentNoticeVO> readRecruitmentList() {
		// 게시글 전체를 읽는것
		return mapper.readRecruitmentNoticeList();
	}
	
	@Override
	public Optional<RecruitmentNoticeVO> readRecruitmentNotice(String recruitmentNo){
	    // 최신순
	    return mapper.selectLiveRecruitment(recruitmentNo);
	}

	@Override
	public List<RecruitmentNoticeVO> salaryRecruitment(String recruitmentNo) {
		// 연봉별 정렬
		return mapper.salaryRecruitment(recruitmentNo);
	}

	@Override
	public RecruitmentNoticeVO selectliveRecruitmentDetail(String recruitmentNo) {
		// 공채정보 전부
		return mapper.selectliveRecruitmentDetail(recruitmentNo);
	}
	

}
