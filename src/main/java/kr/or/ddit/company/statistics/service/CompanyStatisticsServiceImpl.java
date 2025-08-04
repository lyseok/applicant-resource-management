package kr.or.ddit.company.statistics.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.statistics.CompanyStatisticsMapper;
import kr.or.ddit.vo.common.CompanyVO;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CompanyStatisticsServiceImpl implements CompanyStatisticsService{
	private final CompanyMapper companyMapper;
	private final CompanyStatisticsMapper companyStatisticsMapper;

	

    @Override
    public Map<String, Object> getDashboardSummry() {
        String userId = getUserId();
        Map<String, Object> result = new HashMap<>();
        result.put("company", companyMapper.selectCompanyInfoById(getUserId()));   //회사정보
        result.put("recruitmentStatus", companyStatisticsMapper.selectRecruitmentStatusById(userId));         // 지원 현황
        result.put("talentPool", companyStatisticsMapper.selectTalentPoolStatisticsById(userId));             // 인재풀
        result.put("newMembers", companyStatisticsMapper.selectNewMembers(userId));
        result.put("age", companyStatisticsMapper.selectApplicantAgeStatisticsById(userId));                  // 연령
        result.put("edu", companyStatisticsMapper.selectApplicantEduStatisticsById(userId));                  // 학력
        result.put("career", companyStatisticsMapper.selectApplicantCareerStatisticsById(userId));           // 경력
        result.put("gender", companyStatisticsMapper.selectApplicantGenderStaticsById(userId));              // 성별
        result.put("skills", companyStatisticsMapper.selectApplicantSkillsTopTenById());               // 스킬 TOP10
        result.put("topNotice", companyStatisticsMapper.selectTopTenRecruitmentNoticeById());          // 인기 공고
        result.put("passRate", companyStatisticsMapper.selectMonthlyApplicantAndPassRate(userId));           // 월별 지원자+합격률
        return result;
    }

    public String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }




	



}
