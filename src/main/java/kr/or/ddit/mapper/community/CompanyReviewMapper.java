package kr.or.ddit.mapper.community;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.dto.CompanyReviewStatsDTO;
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
	public boolean updateDeleteStatusMyCompanyReview(CompanyReviewVO companyReview);
	public List<CompanyVO> selectCompanyList();
	
	public int selectTotalReviewCount(String comId);
	public Double selectOverallAvg(String comId);
	List<CompanyReviewStatsDTO.QuestionAvgDTO> selectQuestionAvg(String comId);
	List<CompanyReviewStatsDTO.TopJobStatsDTO> selectTopJobOverallList(String comId);
	List<CompanyReviewStatsDTO.QuestionAvgDTO> selectTopJobQuestionAvgList(String comId);
	public int selectReviewUserCount(String comId);
	
	public Map<String, Object> selectCompanyWithReviewInfo(String userId);
	public List<Map<String, Object>> selectCompanyInfoWithReviewInfoList();

	public  int selectCompanyReviewListCount(Map<String, Object> paramMap);
	 List<Map<String, Object>> selectCompanyReviewList(Map<String, Object> paramMap);
	
}
