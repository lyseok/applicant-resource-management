package kr.or.ddit.company.recruitment.talentpool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.TalentPoolMapper;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.resume.CareerVO;
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
	public List<CareerVO> selectTalentPoolList() {
		// TODO Auto-generated method stub
		return TPMapper.selectTalentPoolList();
	}

	@Override
	public ResumeVO selectResumeDetail(String userName) {
		// TODO Auto-generated method stub
		return TPMapper.selectResumeDetail(userName);
	}

//	@Override
//	public List<MySkillVO> selectMySkill() {
//		// TODO Auto-generated method stub
//		return TPMapper.selectSkillList();
//	}
	
	

}
