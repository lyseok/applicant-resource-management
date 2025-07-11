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
	
	
	//추가
	public List<CompanyExamVO> selectCompanyExamListById(String userId);
	public CompanyExamVO selectCompanyExamWithQuestionAndOption(String examNo);
	public boolean updateExamDeleteDate(String companyExamNo);
	public List<CompanyExamVO> selctCompanyExamListByExamNo(String comapnyExamNo);
}
