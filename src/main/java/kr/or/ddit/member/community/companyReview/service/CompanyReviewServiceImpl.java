package kr.or.ddit.member.community.companyReview.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.community.CompanyReviewMapper;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.community.CompanyReviewQuestionVO;
import kr.or.ddit.vo.community.CompanyReviewVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyReviewServiceImpl implements CompanyReviewService {
	private final CompanyReviewMapper mapper;

	@Override
	public List<CompanyVO> readCompanyList() {
		return mapper.selectCompanyList();
	}

	@Override
	public List<CompanyReviewVO> readCompanyReviewAnswerList(String id) {
		return mapper.selectCompanyReviewAnswerList(id);
	}

	@Override
	public List<CmnCodeGroupVO> readCompanyReviewQuestionList(String code) {
		return mapper.selectCompanyQuestionList(code);
	}

	
	
	
	
}
