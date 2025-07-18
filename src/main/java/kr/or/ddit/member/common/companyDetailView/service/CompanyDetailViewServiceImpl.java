package kr.or.ddit.member.common.companyDetailView.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.common.CompanyOpProfitMapper;
import kr.or.ddit.mapper.common.CompanySalesMapper;
import kr.or.ddit.vo.common.CompanyVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CompanyDetailViewServiceImpl implements CompanyDetailViewService{
	private final CompanyMapper companyMapper;
	private final CodeMapProvider codeMapProvider;
	private final CompanySalesMapper companySalesMapper;
	private final CompanyOpProfitMapper companyOpProfitMapper;
	

	@Override
	public CompanyVO readCompanyInfoById(String userId) {
		CompanyVO company = companyMapper.selectCompanyInfoById(userId);
		String no = company.getIndustryType();
		String name = codeMapProvider.getInduName(no);
		company.setIndustryType(name);
	
		String typeName = codeMapProvider.getCodeName(company.getComType());
		String sizeName = codeMapProvider.getCodeName(company.getComSize());
		company.setComType(typeName);
		company.setComSize(sizeName);
		return company;
	}
	
	
	
	
}
