package kr.or.ddit.member.community.companyReview.service;

import java.lang.reflect.Member;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.mapper.common.CmnCodeGroupMapper;
import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.common.MemberMapper;
import kr.or.ddit.mapper.community.CompanyReivewQuestionMapper;
import kr.or.ddit.mapper.community.CompanyReviewMapper;
import kr.or.ddit.mapper.resume.CareerMapper;
import kr.or.ddit.mapper.resume.ResumeMapper;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.CmnCodeVO;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.community.CompanyReviewQuestionVO;
import kr.or.ddit.vo.community.CompanyReviewVO;
import kr.or.ddit.vo.resume.CareerVO;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;

@Service
//(value="companyReviewService")
@RequiredArgsConstructor
public class MemberCompanyReviewServiceImpl implements MemberCompanyReviewService {
	private final CompanyMapper companyMapper;
	private final CompanyReviewMapper companyReviewMapper;
	private final CmnCodeGroupMapper cmnCodeGroupMapper;
	private final MemberMapper memberMapper;
	private final ResumeMapper resumeMapper;
	private final CareerMapper careerMapper;
	private final  CompanyReivewQuestionMapper companyReivewQuestionMapper; 

	@Override
	public List<CompanyVO> readCompanyList() {
		return companyReviewMapper.selectCompanyList();
	}

	@Override
	public List<CompanyReviewVO> readCompanyReviewList() {
		return companyReviewMapper.selectCompanyReviewList();
		
	}

	
	@Transactional
	@Override
	public void createCompanyReview(CompanyReviewVO companyReview) {
		 companyReviewMapper.insertCompanyReview(companyReview);
		 for(CompanyReviewQuestionVO q : companyReview.getCompanyReviewQuestion()) {
			 q.setCompanyReviewNo(companyReview.getCompanyReviewNo());
			 companyReivewQuestionMapper.insertCompanyReviewQuestionWithAnswer(q);
		 }
	}

	@Override
	public boolean updateRemoveStatusMyCompanyReview(CompanyReviewVO companyReview) {
		return companyReviewMapper.updateDeleteStatusMyCompanyReview(companyReview);
		
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

	@Override
	public CompanyVO readCompany(String id) {
		return companyMapper.selectCompanyById(id);
	}

	@Override
	public MemberVO readMemberById(String id) {
		return memberMapper.selectMemberById(id);
	}

	@Override
	public List<ResumeVO> readResumeWithCareers(String userId) {
		return resumeMapper.selectResumeWithCareers(userId);
	}

	@Override
	public CareerVO readCareerDetail(String careerNo) {
		return careerMapper.selectCareerDetail(careerNo);
	}

	
	
	


	
}
