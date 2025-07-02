package kr.or.ddit.recruitment.live.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.recruitment.LiveRecruitmentMapper;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LiveRecruitmentServiceImpl implements LiveRecruitmentService {

	private final LiveRecruitmentMapper mapper;
	
	@Override
	public List<RecruitmentNoticeVO> readRecruitmentList() {
		
		return mapper.readRecruitmentNoticeList();
	}
	
	@Override
	public Optional<RecruitmentNoticeVO> readRecruitmentNotice(String recruitmentNo){
	    // mapper.readRecruitmentNotice가 이미 Optional을 반환한다고 가정
	    return mapper.readRecruitmentNotice(recruitmentNo);
	}
	

}
