package kr.or.ddit.company.common.companyManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.dto.CompanyInfoDTO;
import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.common.InduCodeMapper;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.InduCodeVO;

@Service
public class CompanyManagementServiceImpl implements CompanyManagementService {
	@Autowired
	CompanyMapper companyMapper;
	@Autowired
	private CodeMapProvider provider;
	

	@Override
	public CompanyVO readCompanyManagementById(String userId) {
		CompanyVO company = companyMapper.selectCompanyInfoById(userId);
		String no = company.getIndustryType();
		String name = provider.getInduName(no);
		company.setIndustryType(name);
	
		String typeName = provider.getCodeName(company.getComType());
		String sizeName = provider.getCodeName(company.getComSize());
		company.setComType(typeName);
		company.setComSize(sizeName);
		return company;
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
	    companyVO.setComType(companyInfoDTO.getComType());
	    companyVO.setComSize(companyInfoDTO.getComSize());
	    companyVO.setCeoName(companyInfoDTO.getCeoName());
	    companyVO.setComAddr(companyInfoDTO.getComAddr());
	    companyVO.setComMainBiz(companyInfoDTO.getComMainBiz());
	    companyVO.setComCapital(companyInfoDTO.getComCapital());
	    return companyMapper.updateCompanyInfoById(companyVO);
	}
	

	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}
	
	
}
