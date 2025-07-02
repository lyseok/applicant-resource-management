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
	
	public List<CompanyReviewVO> selectCompanyReviewAnswerList(String id);
	
	public List<CmnCodeGroupVO> selectCompanyQuestionList(String code);
	
	
}
