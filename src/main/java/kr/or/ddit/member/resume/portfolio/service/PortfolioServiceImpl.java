package kr.or.ddit.member.resume.portfolio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.PortfolioMapper;
import kr.or.ddit.vo.resume.PortfolioVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
	private final PortfolioMapper mapper;
	
	@Override
	public List<PortfolioVO> readPortfolioList(String no) {
		return mapper.selectPortfolioList(no);
	}

	@Override
	public PortfolioVO readPortfolioDetail(PortfolioVO vo) {
		return mapper.selectPortfolioDetail(vo);
	}

	@Override
	public void createPortfolio(PortfolioVO vo) {
		mapper.insertPortfolio(vo);
	}

	@Override
	public void editPortfolio(PortfolioVO vo) {
		mapper.updatePortfolio(vo);
	}

	@Override
	public void removePortfolio(String no) {
		mapper.deletePortfolio(no);
	}

}
