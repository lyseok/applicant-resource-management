package kr.or.ddit.member.project.announcement.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.exception.DataInsertException;
import kr.or.ddit.common.exception.DataUpdateException;
import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.mapper.project.PrjAnncBbsMapper;
import kr.or.ddit.member.project.prjpcrtpsncnt.service.PrjRcrtPsncntService;
import kr.or.ddit.member.project.tag.service.PrjAnncBoardTagService;
import kr.or.ddit.vo.project.PrjAnncBbsVO;
import kr.or.ddit.vo.project.PrjAnncBoardTagVO;
import kr.or.ddit.vo.project.PrjRcrtPsncntVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberProjectAnnouncememtServiceImpl implements MemberProjectAnnouncememtService {
	private final PrjAnncBbsMapper prjAnncBbsMapper;
	private final PrjRcrtPsncntService prjRcrtPsncntService;
	private final PrjAnncBoardTagService anncBoardTagService;
	private final CodeMapProvider codeMapProvider;
	
	@Override
	public List<PrjAnncBbsVO> prjAnncBbsList() {
		return prjAnncBbsMapper.selectPrjAnncBbsList();
	}
	
	@Override
	public PrjAnncBbsVO readPrjAnncBbs(String prjAnncNo) {
		PrjAnncBbsVO prjAnncBbs = prjAnncBbsMapper.selectPrjAnncBbsByPk(prjAnncNo);
		for(PrjRcrtPsncntVO prscnt: prjAnncBbs.getPrjRcrtPsncntList()) {
			prscnt.setJobCodeName(codeMapProvider.getJobName(prscnt.getJobCode()));
		}
		return prjAnncBbs;
	}
	
	
	@Transactional
	@Override
	public void createPrjAnncBbs(PrjAnncBbsVO vo) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		vo.setUserId(username);
		
		// 1. prjAnncNo가 있으면 → update, 없으면 → insert
	    if (vo.getPrjAnncNo() != null && !vo.getPrjAnncNo().isBlank()) {
	        // update
	        int result = prjAnncBbsMapper.updatePrjAnncBbs(vo);
	        if (result == 0) {
	            throw new DataUpdateException("프로젝트 공고 수정에 실패했습니다.");
	        }
	    } else {
	        // insert
	        int result = prjAnncBbsMapper.insertPrjAnncBbs(vo);
	        if (result == 0) {
	            throw new DataInsertException("프로젝트 공고 등록에 실패했습니다.");
	        }
	    }
	   for(PrjRcrtPsncntVO prjRcrtPsncntVO : vo.getPrjRcrtPsncntList()) {
		   prjRcrtPsncntVO.setPrjAnncNo(vo.getPrjAnncNo());
		   prjRcrtPsncntService.insertPrjRcrtPsncnt(prjRcrtPsncntVO);
	   }
	   for(PrjAnncBoardTagVO anncBoardTagVO: vo.getPrjAnncBoardTagList()) {
		   anncBoardTagVO.setPrjAnncNo(vo.getPrjAnncNo());
		   anncBoardTagService.insertPrjAnncBoardTag(anncBoardTagVO);
	   }
		
	}

}
