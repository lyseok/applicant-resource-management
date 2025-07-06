package kr.or.ddit.member.resume.portfolio.service;

import java.util.List;

import kr.or.ddit.vo.resume.PortfolioVO;

public interface PortfolioService {
	// 목록 조회
	public List<PortfolioVO> readPortfolioList();
	// 단건 조회
	public PortfolioVO readPortfolioDetail(String no);
	// 등록
	public void createPortfolio(PortfolioVO vo);
	// 수정
	public void editPortfolio(PortfolioVO vo);
	// 삭제
	public void removePortfolio(String no);
}
