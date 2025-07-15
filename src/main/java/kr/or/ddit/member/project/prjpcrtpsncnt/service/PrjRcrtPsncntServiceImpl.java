package kr.or.ddit.member.project.prjpcrtpsncnt.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.exception.DataInsertException;
import kr.or.ddit.common.exception.DataUpdateException;
import kr.or.ddit.mapper.project.PrjRcrtPsncntMapper;
import kr.or.ddit.vo.project.PrjRcrtPsncntVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrjRcrtPsncntServiceImpl implements PrjRcrtPsncntService {
	private final PrjRcrtPsncntMapper prjRcrtPsncntMapper;
	
	@Override
	public void insertPrjRcrtPsncnt(PrjRcrtPsncntVO vo) {
		if (vo.getRcrtPsncntNo() != null && !vo.getRcrtPsncntNo().isBlank()) {
	        // update
	        int result = prjRcrtPsncntMapper.updatePrjRcrtPsncnt(vo);
	        if (result == 0) {
	            throw new DataUpdateException("프로젝트 모집 인원 수정에 실패했습니다.");
	        }
	    } else {
	        // insert
	        int result = prjRcrtPsncntMapper.insertPrjRcrtPsncnt(vo);
	        if (result == 0) {
	            throw new DataInsertException("프로젝트 모집 인원 등록에 실패했습니다.");
	        }
	    }
		
	}

	@Override
	public void updatePrjRcrtPsncnt(PrjRcrtPsncntVO vo) {
		// TODO Auto-generated method stub
		
	}

}
