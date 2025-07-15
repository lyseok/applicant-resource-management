package kr.or.ddit.company.common.companyManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.vo.common.CompanyVO;

@Service
public class CompanyManagementServiceImpl implements CompanyManagementService {
    
	@Autowired
	CompanyMapper companyMapper;

	@Override
	public CompanyVO readCompanyManagementById(String userId) {
		return companyMapper.selectCompanyManagementById(userId);
	}
	

	
}
