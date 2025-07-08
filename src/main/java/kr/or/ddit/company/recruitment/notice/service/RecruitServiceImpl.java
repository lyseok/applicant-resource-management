package kr.or.ddit.company.recruitment.notice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.mapper.recruitment.RecruitmentEducationMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentNoticeMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentPositionMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentSkillmapper;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitServiceImpl implements RecruitService {

	private final RecruitmentNoticeMapper noticeMapper;
	private final RecruitmentPositionMapper positionMapper;
	private final RecruitmentEducationMapper educationMapper;
	private final RecruitmentSkillmapper skillMapper;

	@Override
	@Transactional
	public void createRecruitment(RecruitmentNoticeVO recruit) {
		noticeMapper.insertRecruitmentNotice(recruit);
	}

	@Override
	public void modifyRecruitment(RecruitmentNoticeVO recruit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRecruitment(String recruimentNo) {
		// TODO Auto-generated method stub

	}

}
