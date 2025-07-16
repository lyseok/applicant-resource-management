package kr.or.ddit.company.common.companyManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.dto.CompanyInfoDTO;
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

	@Override
	public int editCompanyInfo(CompanyInfoDTO companyInfoDTO) {
		
		CompanyVO companyVO = new CompanyVO();
		companyVO.setUserId(getUserId());
		companyVO.setComInfo(companyInfoDTO.getComInfo());
	    companyVO.setComNum(companyInfoDTO.getComNum());
	    companyVO.setComEmail(companyInfoDTO.getComEmail());
	    companyVO.setComUrl(companyInfoDTO.getComUrl());
	    companyVO.setComMem(companyInfoDTO.getComMem());
	    companyVO.setIndustryType(companyInfoDTO.getIndustryType());

	    return companyMapper.updateCompanyInfoById(companyVO);
	}
	

	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}
	
	
}
