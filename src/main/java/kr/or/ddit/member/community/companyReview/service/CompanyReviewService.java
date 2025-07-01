 package kr.or.ddit.member.community.companyReview.service;

import java.util.List;

import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.community.CompanyReviewQuestionVO;

public interface CompanyReviewService {
	public List<CompanyVO> readCompanyList();
	
	public List<CompanyReviewQuestionVO> readCompanyReviewQuestionList(String id);
}
