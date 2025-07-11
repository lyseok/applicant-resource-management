package kr.or.ddit.member.common.mypage.scrab.scrabCompany.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.common.ScrabCompanyVO;

public interface MemberScrabCompanyService {

	public List<ScrabCompanyVO> readScrabCompanyList();
	
	public Optional<ScrabCompanyVO> searchScrabCompanyByPk(ScrabCompanyVO scompany);
	
	public void createScrabCompany(ScrabCompanyVO scompany);
	
	public void modifyScrabCompany(ScrabCompanyVO scompany);
	
	public void removeScrabCompany(ScrabCompanyVO scompany);
}
