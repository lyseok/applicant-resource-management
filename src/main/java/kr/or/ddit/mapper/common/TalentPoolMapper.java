package kr.or.ddit.mapper.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.CityCodeVO;
import kr.or.ddit.vo.common.JobVO;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.common.TopJobVO;
import kr.or.ddit.vo.resume.CareerVO;
import kr.or.ddit.vo.resume.EducationVO;
import kr.or.ddit.vo.resume.LanguageSkillVO;
import kr.or.ddit.vo.resume.MyLicenseVO;
import kr.or.ddit.vo.resume.MySkillVO;
import kr.or.ddit.vo.resume.ResumeVO;
import kr.or.ddit.vo.resume.SpecialtyVO;
@Mapper
public interface TalentPoolMapper {
	public List<ResumeVO> selectResumeByFilter(Map<String, Object> params);
	public int selectResumeCountByFilter(Map<String, Object> params);
	
	public CareerVO selectCareerListByResumeNo(String resumeNo);
	public List<MyLicenseVO> selectLicenseListByResumeNo(String resumeNo);
	public List<MySkillVO> selectSkillListByResumeNo(String resumeNo);
	public List<LanguageSkillVO> selectLanguageListByResumeNo(String resumeNo);
	public EducationVO selectHighestEducationByResumeNo(String resumeNo);
	public SpecialtyVO selectSpecialtyListByResumeNo(String resumeNo);
	public int insertMember(MemberVO member);
	
	/**
	 * 한사람의 회원 정보 조회
	 * @param username 조회할 대한 pk
	 * @return 조회한 사람의 정보를 가진 VO
	 * 			해당 회원이 존재하지 않는 경우, null 반환
	 */
	public MemberVO selectMember(String username);
	
	public MemberVO selectMemberByMail(String mail);
	
	public int updateMemDelete(String username);
	
	public int updateMember(MemberVO member);
	
	public List<ResumeVO> selectTalentPoolList();
	
	public List<MySkillVO> selectSkillList();
	 
	public ResumeVO selectResumeDetail(String userId);
	
	public List<TopJobVO> selectTopJob();
	
	public List<JobVO> selectJob();
	
	public List<CityCodeVO> selectlocation();
	
	public List<EducationVO> selecteducation();

	public List<ResumeVO> selectTalentPoolListByFilter(Map<String, Object> filter);
	
	public List<ResumeVO> selectSearchSkillAndLicense(Map<String, String> paramMap);
	
	public List<CareerVO> selectCareer();
	
	

}

