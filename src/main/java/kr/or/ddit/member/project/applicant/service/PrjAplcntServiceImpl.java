package kr.or.ddit.member.project.applicant.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.exception.DataUpdateException;
import kr.or.ddit.mapper.project.PrjAplcntMapper;
import kr.or.ddit.vo.project.PrjAplcntVO;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PrjAplcntServiceImpl implements PrjAplcntService {
	private final PrjAplcntMapper prjAplcntMapper;
	
	@Override
	@Transactional
	public void modifyApplicantStatusCode(Map<String, Object> reqData) {
		List<String> prjAplcntNoList = (List<String>) reqData.get("prjAplcntNoList");
		
		for(String prjAplcntNo : prjAplcntNoList) {
			PrjAplcntVO vo = new PrjAplcntVO();
			vo.setPrjAplcntNo(prjAplcntNo);
			vo.setAplcntStatusCode(String.valueOf(reqData.get("status")));
			int res = prjAplcntMapper.updateStatusCode(vo);
			if(res == 0) {
				throw new DataUpdateException("상태 코드 변경에 실패했습니다");
			}
			
		}
	}

	@Override
	public void modifyAgreeApplicantStatusCode(Map<String, String> reqData) {
		String prjAplcntNo = reqData.get("prjAplcntNo");
		PrjAplcntVO vo = new PrjAplcntVO();
		vo.setPrjAplcntNo(prjAplcntNo);
		vo.setAplcntStatusCode(String.valueOf(reqData.get("status")));
		int res = prjAplcntMapper.updateStatusCode(vo);
		if(res == 0) {
			throw new DataUpdateException("상태 코드 변경에 실패했습니다");
		}
		
	}

}
