package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.CompanyReviewQuestionVO;

@Mapper
public interface CompanyReivewQuestionMapper {
	
	public CompanyReviewQuestionVO selectCompanyReviewQuestionByPk(String questionNo);
	public List<CompanyReviewQuestionVO> selectCompanyReviewQuestionList();
	public int insertCompanyReviewQuestionWithAnswer(CompanyReviewQuestionVO companyReviewQuestion);
	public int updateCompanyReviewQuetiion(CompanyReviewQuestionVO companyReviewQuestion);
	public int deleteCompanyReviewQuestion(String questionNo);
	//public List<CompanyReviewQuestionVO> selectCompanyReviewQuestionListByNo(String reviewNo);

	
}
