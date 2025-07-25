package kr.or.ddit.rest.prjmem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.mapper.project.PrjMemMapper;
import kr.or.ddit.vo.project.PrjMemVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {
	private final PrjMemMapper projectMemberMapper;
	 
	public List<PrjMemVO> getProjectMembers(String prjNo) {
        return projectMemberMapper.selectProjectMembers(prjNo);
    }
	
    public PrjMemVO addProjectMember(PrjMemVO prjMemVO) {
        projectMemberMapper.insertProjectMember(prjMemVO);
        return projectMemberMapper.selectProjectMember(prjMemVO);
    }
	
	public PrjMemVO updateProjectMemberAuthority(PrjMemVO prjMemVO) {
	    int result = projectMemberMapper.updateProjectMemberAuthority(prjMemVO);
	    if (result > 0) {
	        return projectMemberMapper.selectProjectMember(prjMemVO);
	    }
	    return null;
	}
	
	public boolean deleteProjectMember(PrjMemVO prjMemVO) {
		int result = projectMemberMapper.deleteProjectMember(prjMemVO);
	    return result > 0;
	}

}
