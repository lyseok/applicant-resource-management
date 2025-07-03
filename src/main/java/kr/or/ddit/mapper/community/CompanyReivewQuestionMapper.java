package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.CompanyReviewQuestionVO;

@Mapper
public interface CompanyReivewQuestionMapper {
	
	public CompanyReviewQuestionVO selectCompanyReviewQuestionByNo(String questionNo);
	public List<CompanyReviewQuestionVO> selectCompanyReviewQuestionList();
	public List<CompanyReviewQuestionVO> selectCompanyReviewQuestionListByNo(String reviewNo);
	
	
	public int insertCompanyReviewQuestionWithAnswer(CompanyReviewQuestionVO companyReviewQuestion);
	public int deleteCompanyReviewQuestion(String questionNo);
	public int updateCompanyReviewQuetiion(CompanyReviewQuestionVO companyReviewQuestion);
	

	
}
