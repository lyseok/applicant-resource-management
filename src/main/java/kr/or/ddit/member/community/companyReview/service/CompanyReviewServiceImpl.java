package kr.or.ddit.member.community.companyReview.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.CmnCodeGroupMapper;
import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.community.CompanyReviewMapper;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.CmnCodeVO;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.community.CompanyReviewQuestionVO;
import kr.or.ddit.vo.community.CompanyReviewVO;
import lombok.RequiredArgsConstructor;

@Service
//(value="companyReviewService")
@RequiredArgsConstructor
public class CompanyReviewServiceImpl implements CompanyReviewService {
	private final CompanyMapper companyMapper;
	private final CompanyReviewMapper companyReviewMapper;
	private final CmnCodeGroupMapper cmnCodeGroupMapper;
	

	@Override
	public List<CompanyVO> readCompanyList() {
		return companyReviewMapper.selectCompanyList();
	}

	@Override
	public List<CompanyReviewVO> readCompanyReviewList() {
		return companyReviewMapper.selectCompanyReviewList();
		
	}

	@Override
	public void createCompanyReview(CompanyReviewVO companyReview) {
		companyReviewMapper.insertCompanyReview(companyReview);
	}

	@Override
	public void updateRemoveStatusMyCompanyReview(CompanyReviewVO companyReview) {
		companyReviewMapper.updateDeleteStatusMyCompanyReview(companyReview);
		
	}

	@Override
	public List<CompanyReviewVO> readMyCompanyReviewList(String userId) {
		return companyReviewMapper.selectCompanyReviewListById(userId);
	}

	@Override
	public List<CmnCodeVO> readCmnCodeGroupQuestionList(String codeGroupNo) {
        CmnCodeGroupVO groupVo = cmnCodeGroupMapper.selectCmnCodeGroupByPk(codeGroupNo);
        return (groupVo != null && groupVo.getCmnCodeList() != null)
               ? groupVo.getCmnCodeList()
               : Collections.emptyList();
	}

	@Override
	public List<CompanyReviewVO> readReivewQAList(String comId) {
		return companyReviewMapper.selectCompanyReviewWithQAList(comId);
	}

	
	
	


	
}
