package kr.or.ddit.member.common.mypage.scrab.scrabCompany.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.ScrabCompanyMapper;
import kr.or.ddit.vo.common.ScrabCompanyVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberScrabCompanyServiceImpl implements MemberScrabCompanyService {

	private final ScrabCompanyMapper mapper;
	
	@Override
	public List<ScrabCompanyVO> readScrabCompanyList() {
		return mapper.selectScrabCompanyList();
	}

	@Override
	public Optional<ScrabCompanyVO> searchScrabCompanyByPk(ScrabCompanyVO scompany) {
		return Optional.ofNullable(mapper.selectScrabCompanyByPk(scompany));
	}

	@Override
	public void createScrabCompany(ScrabCompanyVO scompany) {
		mapper.insertScrabCompany(scompany);
	}

	@Override
	public void modifyScrabCompany(ScrabCompanyVO scompany) {
		mapper.updateScrabCompany(scompany);
	}

	@Override
	public void removeScrabCompany(ScrabCompanyVO scompany) {
		mapper.deleteScrabCompany(scompany);
	}

}
