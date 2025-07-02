package kr.or.ddit.member.common.mypage.subIntoruction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.SubIntroductionMapper;
import kr.or.ddit.vo.resume.SubIntroductionVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubIntroductionServiceImpl implements SubIntroductionService {
	private final SubIntroductionMapper mapper;

	@Override
	public List<SubIntroductionVO> readSubIntroductionList() {
		return mapper.selectSubIntroductionList();
	}

	@Override
	public SubIntroductionVO readSubIntroductionDetail(String no) {
		return mapper.selectSubIntroductionDetail(no);
	}

	@Override
	public void createSubIntroduction(SubIntroductionVO vo) {
		mapper.insertSubIntroduction(vo);
		
	}

	@Override
	public void updateSubIntroduction(SubIntroductionVO vo) {
		mapper.updateSubIntroduction(vo);		
	}

	@Override
	public void deleteSubIntroduction(String no) {
		mapper.deleteSubIntroduction(no);
		
	}
	

}
