 package kr.or.ddit.member.community.companyReview.service;

import java.util.List;
import kr.or.ddit.vo.common.CmnCodeVO;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.community.CompanyReviewVO;

public interface MemberCompanyReviewService {
	public List<CompanyVO> readCompanyList();
	
	public List<CompanyReviewVO> readCompanyReviewList();
	
	public void createCompanyReview(CompanyReviewVO companyReview);
	
	public void updateRemoveStatusMyCompanyReview(CompanyReviewVO companyReview);
	
	public List<CompanyReviewVO> readMyCompanyReviewList(String userId);
	
	public List<CmnCodeVO> readCmnCodeGroupQuestionList(String cmnGroupNo);
	
	public List<CompanyReviewVO> readReivewQAList(String comId);
	
}
