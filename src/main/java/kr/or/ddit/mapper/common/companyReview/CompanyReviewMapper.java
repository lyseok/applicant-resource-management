package kr.or.ddit.mapper.common.companyReview;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.CompanyVO;

@Mapper
public interface CompanyReviewMapper {
	public List<CompanyVO> selectCompanyList();
}
