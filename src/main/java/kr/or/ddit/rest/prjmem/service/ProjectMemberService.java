package kr.or.ddit.rest.prjmem.service;

import java.util.List;

import kr.or.ddit.vo.project.PrjMemVO;

public interface ProjectMemberService {
	public List<PrjMemVO> getProjectMembers(String prjNo);
	public PrjMemVO addProjectMember(PrjMemVO prjMemVO);
	public PrjMemVO updateProjectMemberAuthority(PrjMemVO prjMemVO);
	public boolean deleteProjectMember(PrjMemVO vo);
}
