package kr.or.ddit.member.common.companyDetailView.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.dto.CompanyOpProfitDTO;
import kr.or.ddit.dto.CompanySalesDTO;
import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.common.CompanyOpProfitMapper;
import kr.or.ddit.mapper.common.CompanySalesMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentNoticeMapper;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CompanyDetailViewServiceImpl implements CompanyDetailViewService{
	private final CompanyMapper companyMapper;
	private final CodeMapProvider codeMapProvider;
	private final CompanySalesMapper companySalesMapper;
	private final CompanyOpProfitMapper companyOpProfitMapper;
	private final RecruitmentNoticeMapper recruitmentNoticeMapper;
	

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


	@Override
	public List<CompanySalesDTO> readCompanySalesInfoById(String userId) {
		List<CompanySalesDTO> sales = companySalesMapper.selectCompanySalesInfoById(userId);
		return sales;
	}


	@Override
	public List<CompanyOpProfitDTO> readCompanyProfitInfoById(String userId) {
		List<CompanyOpProfitDTO> profits = companyOpProfitMapper.selectCompanyOpProfitInfoById(userId);
		return profits;
	}


	@Override
	public List<RecruitmentNoticeVO> readRecruitmentNoticeByUserId(String userId) {
		
		List<RecruitmentNoticeVO> notices = recruitmentNoticeMapper.selectRecruitNoticeByUserId(userId);
		
		for (RecruitmentNoticeVO notice : notices) {
		 
			String jobName = codeMapProvider.getJobName(notice.getJobCode());
			String year = codeMapProvider.getCodeName(notice.getYearCode());
			String city = codeMapProvider.getCityName(notice.getCityCode());
			notice.setJobCodeName(jobName);
			notice.setYearCodeName(year);
			notice.setCityCodeName(city);
		}
		
		return notices;
	}
	
	
	
	
	
	
}
