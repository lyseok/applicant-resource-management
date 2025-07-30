package kr.or.ddit.company.statistics.service;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.statistics.CompanyStatisticsMapper;
import kr.or.ddit.vo.common.CompanyVO;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CompanyStatisticsServiceImpl implements CompanyStatisticsService{
	private final CompanyMapper companyMapper;
	private final CompanyStatisticsMapper companyStatisticsMapper;
	@Override
	public CompanyVO readCompanyById() {
		return companyMapper.selectCompanyInfoById(getUserId());
	}
	

	@Override
	public Map<String, Object> readRecruitmentStatusById() {
		return companyStatisticsMapper.selectRecruitmentStatusById(getUserId());
	}
	
	
	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}



}
