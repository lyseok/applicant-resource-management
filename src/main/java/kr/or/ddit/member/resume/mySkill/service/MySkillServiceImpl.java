package kr.or.ddit.member.resume.mySkill.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.MySkillMapper;
import kr.or.ddit.vo.resume.MySkillVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MySkillServiceImpl implements MySkillService {
	private final MySkillMapper mapper;
	
	@Override
	public List<MySkillVO> readMySkillList(String no) {
		return mapper.selectMySkillList(no);
	}

	@Override
	public MySkillVO readMySkillDetail(MySkillVO vo) {
		return mapper.selectMySkillDetail(vo);
	}

	@Override
	public void createMySkill(MySkillVO vo) {
		mapper.insertMySkill(vo);
	}

	@Override
	public void editMySkill(MySkillVO vo) {
		mapper.updateMySkill(vo);
	}

	@Override
	public void removeMySkill(String no) {
		mapper.deleteMySkill(no);
	}

}
