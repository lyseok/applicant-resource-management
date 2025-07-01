package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.community.CompanyReviewQuestionVO;

@Mapper
public interface CompanyReviewMapper {
	public List<CompanyVO> selectCompanyList();
	
	
	public List<CompanyReviewQuestionVO> selectCompanyReviewQuestionList(String id);
}
