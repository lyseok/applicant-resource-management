package kr.or.ddit.member.common.mypage.introduction.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.mapper.resume.IntroductionMapper;
import kr.or.ddit.mapper.resume.introductionQuestionMapper;
import kr.or.ddit.vo.resume.IntroductionVO;
import kr.or.ddit.vo.resume.introductionQuestionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class introductionServiceImpl implements introductionService {
	private final IntroductionMapper mapper;
	private final introductionQuestionMapper questionMapper;

	// 자소서를 작성한 사람만 cud 할 수 있어야 함
	@Override
	public List<IntroductionVO> readIntroductionList(String userId) {
		List<IntroductionVO> introductionList = mapper.selectIntroductionList(userId);
		for(IntroductionVO intro : introductionList) {
			questionMapper.readIntroductionQuestionList(intro.getIntroductionNo());
		}
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", introductionList);
		return introductionList;
	}

	@Override
	public IntroductionVO readIntroductionDetail(String no) {
		return mapper.selectIntroductionDetail(no);
	}

	@Override
	@Transactional
	public int createIntroduction(IntroductionVO vo) {
		int result = mapper.insertIntroduction(vo);

		// 하위 항목이 있을경우
		if (vo.getIntroductionQuestionList() != null) {
			for (introductionQuestionVO question : vo.getIntroductionQuestionList()) {
				question.setIntroductionNo(vo.getIntroductionNo());
				questionMapper.createIntroductionQuestion(question);
			}
		}
		return result;
	}

	@Override
	public int editIntroduction(IntroductionVO vo) {
		return mapper.updateIntroduction(vo);
	}

	@Override
	public int removeIntroduction(IntroductionVO vo) {
		IntroductionVO existingVo = mapper.selectIntroductionDetail(vo.getIntroductionNo()); // 또는
																								// findById(vo.getIntroductionNo(),
																								// vo.getUserId());
		log.info("{}", existingVo);
		if (existingVo == null) {
			// 데이터가 존재하지 않으면 사용자 정의 예외를 발생시킵니다.
			throw new IllegalArgumentException("존재하지 않는 자소서 번호입니다.");
		}
		return mapper.deleteIntroduction(vo);
	}

	@Override
	public List<IntroductionVO> readIntroductionSearch(String name) {
		return mapper.selectIntroductionSearch(name);
	}

}
