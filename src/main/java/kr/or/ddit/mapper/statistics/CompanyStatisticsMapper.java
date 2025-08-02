package kr.or.ddit.mapper.statistics;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyStatisticsMapper {
	public Map<String, Object> selectRecruitmentStatusById(String userId);//지원현황
	public Map<String, Object> selectTalentPoolStatisticsById(String userId);//인재풀 정보
	public List<Map<String ,Object>> selectApplicantAgeStatisticsById(String userId);//연령대별 분포
	public List<Map<String, Object>> selectApplicantEduStatisticsById(String userId);//학력별 분포
	public List<Map<String, Object>>selectApplicantCareerStatisticsById(String userId);//경력구반별 분포
	public List<Map<String, Object>> selectApplicantGenderStaticsById(String userId);//성별 구간별 분포
	
	public List<Map<String, Object>> selectApplicantSkillsTopTenById(String userId); //보유 기술 스택 10
	public List<Map<String, Object>> selectRecruitmentStageStatusById(String userId);//채용단계별현황
	public List<Map<String, Object>> selectTopTenRecruitmentNoticeById(String userId);//공고 10
	
	//업종별 평균 
	
	public List<Map<String, Object>> selectMonthlyApplicantAndPassRate(String userId);//월별 지원자 추이 및 합격률
	public List<Map<String, Object>> selectNewMembers(String userId);
	
}
