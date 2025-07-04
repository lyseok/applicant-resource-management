package kr.or.ddit.member.common.mypage.introduction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.IntroductionMapper;
import kr.or.ddit.vo.resume.IntroductionVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class introductionServiceImpl implements introductionService {
	private final IntroductionMapper mapper;

	// 자소서를 작성한 사람만 cud 할 수 있어야 함
	@Override
	public List<IntroductionVO> readIntroductionList(String userId) {
		return mapper.selectIntroductionList(userId);
	}

	@Override
	public IntroductionVO readIntroductionDetail(String no) {
		return mapper.selectIntroductionDetail(no);
	}

	@Override
	public void createIntroduction(IntroductionVO vo) {
		mapper.insertIntroduction(vo);		
	}

	@Override
	public void editIntroduction(IntroductionVO vo) {
		mapper.updateIntroduction(vo);		
	}

	@Override
	public void removeIntroduction(IntroductionVO vo) {
		mapper.deleteIntroduction(vo);
		
	}

}
