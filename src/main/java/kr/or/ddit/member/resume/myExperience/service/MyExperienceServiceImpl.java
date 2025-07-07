package kr.or.ddit.member.resume.myExperience.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.MyExperienceMapper;
import kr.or.ddit.vo.resume.MyExperienceVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyExperienceServiceImpl implements MyExperienceService {
	private final MyExperienceMapper mapper;
	
	@Override
	public List<MyExperienceVO> readMyExperienceList() {
		return mapper.selectMyExperienceList();
	}

	@Override
	public MyExperienceVO readMyExperienceDetail(String no) {
		return mapper.selectMyExperienceDetail(no);
	}

	@Override
	public void createMyExperience(MyExperienceVO vo) {
		mapper.insertMyExperience(vo);
	}

	@Override
	public void editMyExperience(MyExperienceVO vo) {
		mapper.updateMyExperience(vo);
	}

	@Override
	public void removeMyExperience(String no) {
		mapper.deleteMyExperience(no);
	}

}
