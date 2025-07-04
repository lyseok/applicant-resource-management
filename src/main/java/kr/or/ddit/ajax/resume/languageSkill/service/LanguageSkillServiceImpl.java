package kr.or.ddit.ajax.resume.languageSkill.service;

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
	public List<LanguageSkillVO> readLanguageSkillList() {
		return mapper.selectLanguageSkillList();
	}

	@Override
	public LanguageSkillVO readLanguageSkillDetail(String no) {
		return mapper.selectLanguageSkillDetail(no);
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
