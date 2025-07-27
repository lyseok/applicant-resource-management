package kr.or.ddit.mapper.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

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
	public List<ResumeVO> selectScrabResume(Map<String, Object> params);
	public int selectCountScrabResume(Map<String, Object> params);
	
	public CareerVO selectCareerListByResumeNo(String resumeNo);
	public List<MyLicenseVO> selectLicenseListByResumeNo(String resumeNo);
	public List<MySkillVO> selectSkillListByResumeNo(String resumeNo);
	public List<LanguageSkillVO> selectLanguageListByResumeNo(String resumeNo);
	public EducationVO selectHighestEducationByResumeNo(String resumeNo);
	public SpecialtyVO selectSpecialtyListByResumeNo(String resumeNo);
	
	public List<String> selectLicenseList();
	public List<String> selectSkillList();
	public List<String> selectEducationList();
	
}

