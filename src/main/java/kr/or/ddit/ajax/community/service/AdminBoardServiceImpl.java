package kr.or.ddit.ajax.community.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.community.AdminBoardMapper;
import kr.or.ddit.vo.community.AdminBoardVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminBoardServiceImpl implements AdminBoardService {
	
	private final AdminBoardMapper mapper;
//	private final AdminService service;

	@Override
	public Optional<AdminBoardVO> readAdminBoard(String boardNo) {
		return Optional.ofNullable(mapper.selectAdminBoard(boardNo));
	}

	@Override
	public List<AdminBoardVO> readAdminBoardList(String boardTypeCode) {
		return mapper.selectAdminBoardList(boardTypeCode);
	}

	@Override
	public void createAdminBoard(AdminBoardVO board) {
		/*
		board.setBoardStatus(
			    Optional.ofNullable(board.getBoardStatus())
			            .filter(s -> !s.isBlank())
			            .orElse("R")
			);
		*/
		mapper.insertAdminBoard(board);		
	}

	@Override
	public void modifyAdminBoard(AdminBoardVO board) {
		/*
		// 1. 관리자 여부 조회
	    AdminVO adminInfo = AdminBoardService.selectAdminById(board.getUserId());
	    boolean isAdmin = adminInfo != null && "Y".equalsIgnoreCase(adminInfo.getIsAdmin());

	    // 2. Mapper에 넘길 파라미터 구성 (VO + isAdmin)
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("board", board);
	    paramMap.put("isAdmin", isAdmin);

	    // 3. Mapper 호출
	    mapper.updateBoard(paramMap);
	    
		/*
		AdminVO adminInfo = adminMapper.selectAdminById(board.getUserId());
	    boolean isAdmin = adminInfo != null && "Y".equalsIgnoreCase(adminInfo.getIsAdmin());

	    board.setIsAdmin(isAdmin); // VO에 boolean isAdmin 필드 추가 */
	    
		mapper.updateAdminBoard(board);

	}


	@Override
	public void removeAdminBoard(String boardNo) {
		mapper.deleteAdminBoard(boardNo);
	}
	
}
