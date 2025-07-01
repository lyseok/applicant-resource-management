package kr.or.ddit.ats.companyReview.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.companyReview.CompanyReviewMapper;
import kr.or.ddit.vo.common.CompanyVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyReviewServiceImpl implements CompanyReviewService {
	private final CompanyReviewMapper mapper;

	@Override
	public List<CompanyVO> readCompanyList() {
		return mapper.selectCompanyList();
	}
	
}
