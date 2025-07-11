package kr.or.ddit.member.resume.languageSkill.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.LanguageSkillMapper;
import kr.or.ddit.vo.resume.LanguageSkillVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LanguageSkillServiceImpl implements LanguageSkillService {
	private final LanguageSkillMapper mapper;
	
	@Override
	public List<LanguageSkillVO> readLanguageSkillList(String no) {
		return mapper.selectLanguageSkillList(no);
	}

	@Override
	public LanguageSkillVO readLanguageSkillDetail(LanguageSkillVO vo) {
		return mapper.selectLanguageSkillDetail(vo);
	}

	@Override
	public void createLanguageSkill(LanguageSkillVO vo) {
		mapper.insertLanguageSkill(vo);
	}

	@Override
	public void editLanguageSkill(LanguageSkillVO vo) {
		mapper.updateLanguageSkill(vo);
	}

	@Override
	public void removeLanguageSkill(String no) {
		mapper.deleteLanguageSkill(no);
	}

}
