package kr.or.ddit.member.common.mypage.intoruction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.IntroductionMapper;
import kr.or.ddit.vo.resume.IntroductionVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class introductionServiceImpl implements introductionService {
	private final IntroductionMapper mapper;
	
	@Override
	public List<IntroductionVO> readIntroductionList(String userId) {
		return mapper.selectIntroductionList(userId);
	}

}
