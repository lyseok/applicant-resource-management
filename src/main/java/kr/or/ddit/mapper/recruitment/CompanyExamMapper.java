package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.CompanyExamVO;

@Mapper
public interface CompanyExamMapper {
	public List<CompanyExamVO> selectCompanyExamList();
	public CompanyExamVO selectCompanyExam(String companyExamNo);
	public int insertCompanyExam(CompanyExamVO vo);
	public int updateCompanyExam(CompanyExamVO vo);
	public int deleteCompanyExam(String companyExamNo);
	public int deleteComExamQuestByExamNo(String comExamNo);
	public int deleteComExamOptionByExamNo(String ComExamNo);
	
	//추가
	public List<CompanyExamVO> selectCompanyExamListById(String userId);
	
}
