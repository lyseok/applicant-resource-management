package kr.or.ddit.member.project.project.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.exception.DataInsertException;
import kr.or.ddit.common.exception.DataUpdateException;
import kr.or.ddit.mapper.project.ChatroomMapper;
import kr.or.ddit.mapper.project.ChatroomMemMapper;
import kr.or.ddit.mapper.project.PrjAnncBbsMapper;
import kr.or.ddit.mapper.project.PrjAplcntMapper;
import kr.or.ddit.mapper.project.PrjMemMapper;
import kr.or.ddit.mapper.project.ProjectMapper;
import kr.or.ddit.vo.project.ChatroomMemVO;
import kr.or.ddit.vo.project.ChatroomVO;
import kr.or.ddit.vo.project.PrjAplcntVO;
import kr.or.ddit.vo.project.PrjMemVO;
import kr.or.ddit.vo.project.ProjectVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberProjectAjaxServiceImpl implements MemberProjectAjaxService{
	private final ProjectMapper mapper;
	private final PrjAplcntMapper aplcntMapper;
	private final PrjMemMapper memMapper;
	private final ChatroomMapper chatroomMapper;
	private final ChatroomMemMapper chatroomMemMapper;
	private final PrjAnncBbsMapper anncBbsMapper;
	
	@Override
	public List<ProjectVO> readProjectList() {
		return mapper.selectProjectList();
	}

	@Override
	public List<ProjectVO> readProjectUserIdList(String userId) {
//		return mapper.selectProjectUserIdList(userId);
		return null;
	}

	@Override
	public ProjectVO readProjectByPk(String prjNo) {
//		return mapper.selectProjectByPk(prjNo);
		return null;
	}

	@Override
	public int createProject(ProjectVO project) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		project.setUserId(username);
		
		int res = mapper.createProject(project);
		if(res <= 0) {
			throw new DataInsertException("프로젝트 생성중 오류 발생");
		}
		
		ChatroomVO chatroom = new ChatroomVO();
		chatroom.setPrjNo(project.getPrjNo());
		chatroom.setChatroomName(project.getProjectName() + " 채팅방");
		
		int chatRes =  chatroomMapper.insertChatroom(chatroom);
		if(chatRes <= 0) {
			throw new DataInsertException("프로젝트 채 생성중 오류 발생");
		}
		
		PrjMemVO manager = new PrjMemVO();
		manager.setPrjNo(project.getPrjNo());
		manager.setUserId(username);
		manager.setAuthorityCode("MANAGER");
		manager.setUserPosition("프로젝트 매니저");
		int managerRes = memMapper.insertProjectMember(manager);
		if(managerRes <= 0) {
			throw new DataInsertException("프로젝트 매니저 생성중 오류 발생");
		}
		
		ChatroomMemVO chatManagerVO = new ChatroomMemVO();
		chatManagerVO.setPrjNo(project.getPrjNo());
		chatManagerVO.setChatroomNo(chatroom.getChatroomNo());
		chatManagerVO.setUserId(username);
		int cManagerRes = chatroomMemMapper.insertChatroomMem(chatManagerVO);
		if(cManagerRes <= 0) {
			throw new DataInsertException("채팅 매니저 생성중 오류 발생");
		}
		
		List<PrjAplcntVO> aplcntList = aplcntMapper.selectPrjRcrtPsncntByBbs(project.getProjectBoardNo());
		log.info("====> {}", aplcntList);
		for(PrjAplcntVO aplcntVO : aplcntList) {
			if(aplcntVO.getAplcntStatusCode().equals("PRST-003")) {
				PrjMemVO memVO = new PrjMemVO();
				memVO.setPrjNo(project.getPrjNo());
				memVO.setUserId(aplcntVO.getUserId());
				memVO.setAuthorityCode("MEMBER");
				memVO.setUserPosition(aplcntVO.getUserPosition());
				int memRes = memMapper.insertProjectMember(memVO);
				if(memRes <= 0) {
					throw new DataInsertException("프로젝트 팀원 생성중 오류 발생");
				}
				ChatroomMemVO chatroomMemVO = new ChatroomMemVO();
				chatroomMemVO.setPrjNo(project.getPrjNo());
				chatroomMemVO.setChatroomNo(chatroom.getChatroomNo());
				chatroomMemVO.setUserId(aplcntVO.getUserId());
				
				int cMemRes = chatroomMemMapper.insertChatroomMem(chatroomMemVO);
				if(cMemRes <= 0) {
					throw new DataInsertException("채팅 참여자 생성중 오류 발생");
				}				
			}
		}
		
		int updateRes = anncBbsMapper.updateAnncEndYn(project.getProjectBoardNo());
		if(updateRes <= 0) {
			throw new DataUpdateException("프로젝트 게시판 상태 변경중 오류 발생");
		}
		
		return 1;
	}

	@Override
	public int modifyProject(ProjectVO project) {
		return mapper.updateProject(project);
	}

	@Override
	public int removeProject(String prjNo) {
//		return mapper.deleteProject(prjNo);
		return 0;
	}

}
