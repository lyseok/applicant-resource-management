package kr.or.ddit.company.common.company.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.common.UserMapper;
import kr.or.ddit.member.common.exception.PKDuplicatedException;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.UsersVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
	
	private final UserMapper userMapper;
	private final CompanyMapper companyMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public List<CompanyVO> readCompanyList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompanyVO readCompany() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyCompany() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeCompany() {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void registerCompany(UsersVO user, CompanyVO company) {
		
	}



}
