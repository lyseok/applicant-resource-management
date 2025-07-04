package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.PortfolioVO;

@Mapper
public interface PortfolioMapper {
	// 목록 조회
	public List<PortfolioVO> selectPortfolioList();
	// 단건 조회
	public PortfolioVO selectPortfolioDetail(String no);
	// 등록
	public int insertPortfolio(PortfolioVO vo);
	// 수정
	public int updatePortfolio(PortfolioVO vo);
	// 삭제
	public int deletePortfolio(String no);
}
