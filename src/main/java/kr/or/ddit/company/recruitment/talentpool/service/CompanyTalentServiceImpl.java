package kr.or.ddit.company.recruitment.talentpool.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.TalentPoolMapper;
import kr.or.ddit.vo.common.CityCodeVO;
import kr.or.ddit.vo.common.JobVO;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.common.TopJobVO;
import kr.or.ddit.vo.resume.CareerVO;
import kr.or.ddit.vo.resume.EducationVO;
import kr.or.ddit.vo.resume.MySkillVO;
import kr.or.ddit.vo.resume.ResumeVO;

@Service
public class CompanyTalentServiceImpl implements CompanyTalentService {

	@Autowired
	TalentPoolMapper TPMapper;
	
	@Override
	public int insertMember(MemberVO member) {
		
		return TPMapper.insertMember(member);
	}

	@Override
	public MemberVO selectMember(String username) {
		// TODO Auto-generated method stub
		return TPMapper.selectMember(username);
	}

	@Override
	public MemberVO selectMemberByMail(String mail) {
		// TODO Auto-generated method stub
		return TPMapper.selectMemberByMail(mail);
	}

	@Override
	public int updateMemDelete(String username) {
		// TODO Auto-generated method stub
		return TPMapper.updateMemDelete(username);
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return TPMapper.updateMember(member);
	}

	@Override
	public List<ResumeVO> selectTalentPoolList() {
		// TODO Auto-generated method stub
		return TPMapper.selectTalentPoolList();
	}

	@Override
	public ResumeVO selectResumeDetail(String userId) {
		// TODO Auto-generated method stub
		return TPMapper.selectResumeDetail(userId);
	}

	@Override
	public List<TopJobVO> selectTopJob() {
		// TODO Auto-generated method stub
		return TPMapper.selectTopJob();
	}

	@Override
	public List<JobVO> selectJob() {
		// TODO Auto-generated method stub
		return TPMapper.selectJob();
	}

	@Override
	public List<CityCodeVO> selectlocation() {
		// TODO Auto-generated method stub
		return TPMapper.selectlocation();
	}

	@Override
	public List<EducationVO> selecteducation() {
		// TODO Auto-generated method stub
		return TPMapper.selecteducation();
	}
	
	@Override
	public List<ResumeVO> selectTalentPoolListByFilter(Map<String, Object> filter) {
	    return TPMapper.selectTalentPoolListByFilter(filter);
	}

	@Override
	public List<MySkillVO> selectSearchSkill(String skillName) {
		// TODO Auto-generated method stub
		return TPMapper.selectSearchSkill(skillName);
	}

	/*
	 * @Override public CityCodeVO selectedlocation() { // TODO Auto-generated
	 * method stub return TPMapper.selectedlocation(); }
	 * 
	 * @Override public EducationVO selectededucation() { // TODO Auto-generated
	 * method stub return TPMapper.selectededucation(); }
	 * 
	 * @Override public TopJobVO selectedTopJob() { // TODO Auto-generated method
	 * stub return TPMapper.selectedTopJob(); }
	 */
//	@Override
//	public List<MySkillVO> selectMySkill() {
//		// TODO Auto-generated method stub
//		return TPMapper.selectSkillList();
//	}
	
	

}
