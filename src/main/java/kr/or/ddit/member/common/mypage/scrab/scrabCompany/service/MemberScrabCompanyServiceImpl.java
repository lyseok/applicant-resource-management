package kr.or.ddit.member.common.mypage.scrab.scrabCompany.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.exception.DataInsertException;
import kr.or.ddit.mapper.common.ScrabCompanyMapper;
import kr.or.ddit.vo.common.ScrabCompanyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberScrabCompanyServiceImpl implements MemberScrabCompanyService {
	private final ScrabCompanyMapper mapper;

	@Override
	public List<ScrabCompanyVO> readMyScrabCompanyList() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		log.info("username : {}", username);
		return mapper.selectMyScrabCompanyList(username);
	}

	@Override
	public List<ScrabCompanyVO> readScrabCompanyList() {
		return mapper.selectScrabCompanyList();
	}

	@Override
	public void createScrabCompany(String companyId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		ScrabCompanyVO scompany = new ScrabCompanyVO();
		scompany.setUserId(username);
		scompany.setCompanyId(companyId);
		
		int res = mapper.insertScrabCompany(scompany);
		if(res <= 0) {
			throw new DataInsertException("관심 기업 추가 실패");
		}
	}

	@Override
	public void removeScrabCompany(String companyId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		ScrabCompanyVO scompany = new ScrabCompanyVO();
		scompany.setUserId(username);
		scompany.setCompanyId(companyId);
		
		int res = mapper.deleteScrabCompany(scompany);
		if(res <= 0) {
			throw new DataInsertException("관심 기업 삭제 실패");
		}
	}

	@Override
	public int findCompanyScrabYn(String companyId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		ScrabCompanyVO scompany = new ScrabCompanyVO();
		scompany.setUserId(username);
		scompany.setCompanyId(companyId);
		
		int res = mapper.checkMyCompanyScrab(scompany);
		return res;
	}

}
