 package kr.or.ddit.member.community.companyReview.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.CompanyReviewDTO;
import kr.or.ddit.dto.CompanyReviewStatsDTO;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.community.CompanyReviewVO;
import kr.or.ddit.vo.resume.CareerVO;
import kr.or.ddit.vo.resume.ResumeVO;

public interface MemberCompanyReviewService {
	
	public List<CompanyVO> readCompanyInfoList();
	
	public CompanyVO readCompany(String id);
	
	public List<CompanyReviewVO> readCompanyReviewList();
	
	public void createCompanyReview(CompanyReviewDTO  companyReview);
	
	public boolean updateRemoveStatusMyCompanyReview(CompanyReviewVO companyReview);
	
	public List<CompanyReviewVO> readMyCompanyReviewList(String userId);
		
	public List<CompanyReviewVO> readReivewQAList(String comId);
	
	public MemberVO readMemberById(String id);
	
	public List<ResumeVO> readResumeWithCareers();
	public CareerVO readCareerDetail(String careerNo);
	
	public CompanyReviewStatsDTO readCompanyReviewStats(String comId);
	
	public Map<String, Object> readCompanyWithReviewInfo(String comId);
	
	public List<Map<String, Object>> readCompanyInfoWithReviewInfoList();
	
	public Map<String, Object> readCompanyInfoWithReviewInfoPage(Map<String, Object> params);
	
	
}
