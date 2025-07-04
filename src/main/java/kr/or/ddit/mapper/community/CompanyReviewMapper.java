package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.community.CompanyReviewQuestionVO;
import kr.or.ddit.vo.community.CompanyReviewVO;

@Mapper
public interface CompanyReviewMapper {
	public CompanyReviewVO selectCompanyReviewByPk(String reviewNo);
	public List<CompanyReviewVO> selectCompanyReviewList();
	public List<CompanyReviewVO> selectCompanyReviewWithQAList(String comId);
	public List<CompanyReviewVO> selectCompanyReviewListById(String userId);
	public int insertCompanyReview(CompanyReviewVO companyReview);
	public int deleteCompanyReview(String reviewNo);
	public int updateDeleteStatusMyCompanyReview(CompanyReviewVO companyReview);
	public List<CompanyVO> selectCompanyList();
}
