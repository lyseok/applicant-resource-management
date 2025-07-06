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
	public List<PortfolioVO> readPortfolioList() {
		return mapper.selectPortfolioList();
	}

	@Override
	public PortfolioVO readPortfolioDetail(String no) {
		return mapper.selectPortfolioDetail(no);
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
