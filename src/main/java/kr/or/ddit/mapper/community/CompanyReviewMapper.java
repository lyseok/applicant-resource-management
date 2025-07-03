package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.community.CompanyReviewQuestionVO;
import kr.or.ddit.vo.community.CompanyReviewVO;

@Mapper
public interface CompanyReviewMapper {
	public List<CompanyVO> selectCompanyList();
	
	public CompanyReviewVO selectCompanyReviewList(String reviewNo);
	
	public int insertCompanyReview(CompanyReviewVO companyReview);
	public int deleteCompanyReview(String userId);
	public int updateDeleteStatusMyCompanyReview(String userId);
	
	
}
