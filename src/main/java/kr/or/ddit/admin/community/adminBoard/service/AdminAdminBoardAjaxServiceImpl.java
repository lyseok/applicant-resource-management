package kr.or.ddit.admin.community.adminBoard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.mapper.community.AdminBoardMapper;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.CmnCodeVO;
import kr.or.ddit.vo.community.AdminBoardVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminAdminBoardAjaxServiceImpl implements AdminAdminBoardAjaxService{

	private final AdminBoardMapper mapper;  //UserMapper를 넣어야 하나?
	private final CodeMapProvider codeMapProvider;
	
	@Override
	public Optional<AdminBoardVO> readAdminBoardByPk(String boardNo) {
		Optional<AdminBoardVO> aboard = Optional.ofNullable(mapper.selectAdminBoardByPk(boardNo));
		aboard.ifPresent(this::setCodeName); // Optional의 값이 존재할 때만 setCodeName 호출
		return aboard;
	}

	private void setCodeName(AdminBoardVO aboard) {
		String boardTypeCode = codeMapProvider.getCodeName(aboard.getBoardTypeCode());
		aboard.setCodeName(boardTypeCode);
	}

	@Override
	public List<AdminBoardVO> readAdminBoardListByType(String boardTypeCode) {
		List<AdminBoardVO> aboardList = mapper.selectAdminBoardListByType(boardTypeCode);
		for(AdminBoardVO aboard : aboardList) {
			setCodeName(aboard);
		}
		return aboardList;
	}
	
	public List<AdminBoardVO> readAFaqListByCgn(String groupPrefix) {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("boardTypeCode", groupPrefix + "%"); // 예: "CFAQ%" 또는 "UFAQ%"
	    return mapper.selectAFaqListByCgn(paramMap);
	}	

	@Override
	public List<AdminBoardVO> readAFaqListByUcn(String upperCodeNo) {
		List<AdminBoardVO> aboardList = mapper.selectAFaqListByUcn(upperCodeNo);  //'BRDD-002'
		for(AdminBoardVO aboard : aboardList) {
			setCodeName(aboard);
		}
		return aboardList;
	}
		
	@Override
	public List<AdminBoardVO> readAdminBoardList() {
		List<AdminBoardVO> aboardList = mapper.selectAdminBoardList();
		for(AdminBoardVO aboard : aboardList) {
			setCodeName(aboard);
		}
		return aboardList;
	}

	@Override
	public void createAdminBoard(AdminBoardVO board) {
	    mapper.insertAdminBoard(board);
	}

	@Override
	public void modifyAdminBoard(AdminBoardVO board) {
		mapper.updateAdminBoard(board);

	}

	@Override
	public void removeAdminBoard(String boardNo) {
		mapper.deleteAdminBoard(boardNo);
	}

	@Override
	public void hiddenAdminBoard(AdminBoardVO board) {
		mapper.upDeleteAdminBoard(board);
	}

	@Override
	public List<CmnCodeGroupVO> readCmnGroupList(String upperCodeNo) {
		return mapper.selectCmnGroupList(upperCodeNo);
	}

	@Override
	public List<CmnCodeVO> readCmnList(String codeGroupNo) {
		return mapper.selectCmnList(codeGroupNo);
	}

}
