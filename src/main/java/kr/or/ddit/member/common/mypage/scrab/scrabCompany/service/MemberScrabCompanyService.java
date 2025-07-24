package kr.or.ddit.member.common.mypage.scrab.scrabCompany.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.common.ScrabCompanyVO;

public interface MemberScrabCompanyService {
	public List<ScrabCompanyVO> readMyScrabCompanyList();
	public List<ScrabCompanyVO> readScrabCompanyList();
	public void createScrabCompany(String scompany);
	public void removeScrabCompany(String scompany);
}
