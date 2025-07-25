package kr.or.ddit.company.common.company.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.or.ddit.conf.RestSpringSecurityConfig;
import kr.or.ddit.mapper.common.BusinessRegistrationMapper;
import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.common.UserMapper;
import kr.or.ddit.member.common.exception.PKDuplicatedException;
import kr.or.ddit.vo.common.BusinessregistrationVO;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.UsersVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final RestSpringSecurityConfig restSpringSecurityConfig;
	
	private final UserMapper userMapper;
	private final CompanyMapper companyMapper;
	private final PasswordEncoder passwordEncoder;
	private final BusinessRegistrationMapper businessMapper;

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
	public void registerCompany(CompanyVO company) {
		String encoded = passwordEncoder.encode(company.getUserPassword());
		company.setUserPassword(encoded);
		
		userMapper.insertUser(company);
		int cnt = companyMapper.insertCompany(company);
		
		if(cnt > 0) {
			company.getBusiness().setUserName(company.getUserId());
			company.getBusiness().setComName(company.getComName());
			businessMapper.insertBusinessregistration(company.getBusiness());
		}
	}

	@Override
	public CompanyVO selectCompanyById(String userId) {
		// TODO Auto-generated method stub
		return companyMapper.selectCompanyById(userId);
	}

}
