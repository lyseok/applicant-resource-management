package kr.or.ddit.member.common.companyDetailView.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.CompanyOpProfitDTO;
import kr.or.ddit.dto.CompanySalaryDTO;
import kr.or.ddit.dto.CompanySalesDTO;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.FilesVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

public interface CompanyDetailViewService {
	public CompanyVO readCompanyInfoById(String userId);
	
	public List<CompanySalesDTO> readCompanySalesInfoById(String userId);
	
	public List<CompanyOpProfitDTO> readCompanyProfitInfoById(String userId);
	
	public List<RecruitmentNoticeVO> readRecruitmentNoticeByUserId(String userId);
	
	public List<CompanySalaryDTO> readSalaryStatisticsById(String userId);
	
	public List<FilesVO> readCompanyImageFileListBySourceNo(String sourceNo);
	
}
