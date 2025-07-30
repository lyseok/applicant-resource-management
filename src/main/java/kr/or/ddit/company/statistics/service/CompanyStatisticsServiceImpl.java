package kr.or.ddit.company.statistics.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.vo.common.CompanyVO;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CompanyStatisticsServiceImpl implements CompanyStatisticsService{
	private final CompanyMapper companyMapper;
	@Override
	public CompanyVO readCompanyById() {
		return companyMapper.selectCompanyInfoById(getUserId());
	}
	
	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}
}
