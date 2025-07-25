package kr.or.ddit.member.common.mypage.scrab.scrabRecruitment.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.exception.DataInsertException;
import kr.or.ddit.common.exception.DataUpdateException;
import kr.or.ddit.mapper.common.ScrabRecruitmentMapper;
import kr.or.ddit.vo.common.ScrabRecruitmentVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberScrabRecruitmentServiceImpl implements MemberScrabRecruitmentService {

	private final ScrabRecruitmentMapper mapper;

	@Override
	public List<ScrabRecruitmentVO> readScrabRecruitmentList() {
		return mapper.selectScrabRecruitmentList();
	}

	@Override
	public List<ScrabRecruitmentVO> readMyScrabRecruitmentList() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return mapper.selectMyScrabRecruitmentList(username);
	}

	@Override
	public void createScrabRecruitment(String srecruit) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		ScrabRecruitmentVO recruitment = new ScrabRecruitmentVO();
		recruitment.setUserId(username);
		recruitment.setRecruitmentNo(srecruit);
		
		int res = mapper.insertScrabRecruitment(recruitment);
		if(res <= 0) {
			throw new DataInsertException("공고 스크랩 추가 실패");
		}
		
	}

	@Override
	public void removeScrabRecruitment(String srecruit) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		ScrabRecruitmentVO recruitment = new ScrabRecruitmentVO();
		recruitment.setUserId(username);
		recruitment.setRecruitmentNo(srecruit);
		
		int res = mapper.deleteScrabRecruitment(recruitment);
		if(res <= 0) {
			throw new DataUpdateException("공고 스크랩 삭제 실패");
		}
	}


}
