package kr.or.ddit.member.community.companyReview.service;


import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.dto.CompanyReviewDTO;
import kr.or.ddit.dto.CompanyReviewStatsDTO;

import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.common.MemberMapper;
import kr.or.ddit.mapper.community.CompanyReivewQuestionMapper;
import kr.or.ddit.mapper.community.CompanyReviewMapper;
import kr.or.ddit.mapper.resume.CareerMapper;
import kr.or.ddit.mapper.resume.ResumeMapper;

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
	private final MemberMapper memberMapper;
	private final ResumeMapper resumeMapper;
	private final CareerMapper careerMapper;
	private final CompanyReivewQuestionMapper companyReivewQuestionMapper; 
	private final CodeMapProvider codeMapProvider;

	


	@Override
	public List<CompanyVO> readCompanyInfoList() {
		List<CompanyVO> companies = companyMapper.selectCompanyInfoList();
		for (CompanyVO company : companies) {
			String induName = codeMapProvider.getInduName(company.getIndustryType());
			company.setInduName(induName);
		}
		return companies;
	}


	@Override
	public List<CompanyReviewVO> readCompanyReviewList() {
		return companyReviewMapper.selectCompanyReviewList();
		
	}

	
	@Transactional
	@Override
	public void createCompanyReview(CompanyReviewDTO companyReview) {
		CareerVO career = readCareerDetail(companyReview.getCareerNo());
		CompanyReviewVO review = new CompanyReviewVO();
		review.setUserId(getUserId());
		review.setJobCode(career.getJobCode());
		review.setComId(career.getComId());
		review.setCompanyReviewOneLine(companyReview.getCompanyReviewOneLine());
		review.setCompanyReviewQuestion(companyReview.getCompanyReviewQuestion());
		if(career.getRetireDate() != null && career.getTenure() == "Y") {
			review.setWorkingYn("Y");
		}else {
			review.setWorkingYn("N");
		}
		
		companyReviewMapper.insertCompanyReview(review);
		for (CompanyReviewQuestionVO q : review.getCompanyReviewQuestion()) {
			q.setCompanyReviewNo(review.getCompanyReviewNo());
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
	public List<ResumeVO> readResumeWithCareers() {
		List<ResumeVO> resumes = resumeMapper.selectResumeWithCareers(getUserId());
		for (ResumeVO resume : resumes) {
			for (CareerVO career : resume.getCareerList()) {
				String jobName = codeMapProvider.getJobName(career.getJobCode());
				String jobGradeName = codeMapProvider.getCodeName(career.getJobGradeCode());
				String positionName = codeMapProvider.getCodeName(career.getPositionCode());
				String yearName = codeMapProvider.getCodeName(career.getCareerYear());
				
				career.setJobCodeName(jobName);
				career.setJobGradeCodeName(jobGradeName);
				career.setPositionCodeName(positionName);
				career.setCareerYearName(yearName);
			}
		}
		return resumes;
	}

	@Override
	public CareerVO readCareerDetail(String careerNo) {
		CareerVO career = careerMapper.selectCareerDetailWithCom(careerNo);
		String jobName = codeMapProvider.getJobName(career.getJobCode());
		String jobGradeName = codeMapProvider.getCodeName(career.getJobGradeCode());
		String positionName = codeMapProvider.getCodeName(career.getPositionCode());
		String yearName = codeMapProvider.getCodeName(career.getCareerYear());
		career.setJobCodeName(jobName);
		career.setJobGradeCodeName(jobGradeName);
		career.setPositionCodeName(positionName);
		career.setCareerYearName(yearName);
		return career;
		
	}
	
	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}


	@Override
	public CompanyReviewStatsDTO readCompanyReviewStats(String comId) {
		CompanyReviewStatsDTO dto = new CompanyReviewStatsDTO();
		
		dto.setTotalReviewCount(companyReviewMapper.selectTotalReviewCount(comId));
	    dto.setReviewUserCount(companyReviewMapper.selectReviewUserCount(comId));
		
		dto.setOverallAvg(companyReviewMapper.selectOverallAvg(comId));
		dto.setQuestionAvgList(companyReviewMapper.selectQuestionAvg(comId));

		List<CompanyReviewStatsDTO.TopJobStatsDTO> topJobOverallList = companyReviewMapper.selectTopJobOverallList(comId);
		List<CompanyReviewStatsDTO.QuestionAvgDTO> topJobQuestionList = companyReviewMapper.selectTopJobQuestionAvgList(comId);
		
		  for (CompanyReviewStatsDTO.TopJobStatsDTO topJob : topJobOverallList) {
	            List<CompanyReviewStatsDTO.QuestionAvgDTO> questionListForTopJob = topJobQuestionList.stream()
	                    .filter(q -> q.getTopJobCode().equals(topJob.getTopJobCode()))
	                    .toList();
	            topJob.setQuestionAvgList(questionListForTopJob);
	        }
	        dto.setTopJobStatsList(topJobOverallList);
													
		
		return dto;
	}


	@Override
	public Map<String, Object> readCompanyWithReviewInfo(String comId) {
		Map<String, Object> companyReviewInfo = companyReviewMapper.selectCompanyWithReviewInfo(comId);
		return companyReviewInfo;
	}

	
	
	


	
}
