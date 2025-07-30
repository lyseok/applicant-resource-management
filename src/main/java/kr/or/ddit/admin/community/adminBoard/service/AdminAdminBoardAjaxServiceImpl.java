package kr.or.ddit.admin.community.adminBoard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.common.MemberMapper;
import kr.or.ddit.mapper.common.UserMapper;
import kr.or.ddit.mapper.community.AdminBoardMapper;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.CmnCodeVO;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.common.UsersVO;
import kr.or.ddit.vo.community.AdminBoardVO;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminAdminBoardAjaxServiceImpl implements AdminAdminBoardAjaxService{

	private final AdminBoardMapper mapper;
	private final CodeMapProvider codeMapProvider;
	private final UserMapper userMapper;
	private final MemberMapper memberMapper;
	private final CompanyMapper companyMapper;
	
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
	
	//문의사항에서 유저권한 구분용
	@Override
	public List<AdminBoardVO> readAdminBoardListByType(String boardTypeCode, String userRole) {
		List<AdminBoardVO> aboardList = mapper.selectAdminBoardListByTypeAndUserRole(boardTypeCode, userRole);
		for (AdminBoardVO aboard : aboardList) {
			setCodeName(aboard);
		}
		return aboardList;
	}

	
	@Override
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
	public void addABoardPostHit(AdminBoardVO board) {
		mapper.updateABoardPostHit(board);		
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

	@Override
	public List<AdminBoardVO> readDelAboardList() {
		return mapper.selectDelAboardList();
	}

	@Override
	public MemberVO readMemName(String userId) {
		return memberMapper.selectMemberById(userId);
	}

	@Override
	public CompanyVO readComName(String userId) {
		return companyMapper.selectCompanyById(userId);
	}

	@Override
	public String readBoardTypeName(String boardTypeCode) {
		return mapper.selectBoardTypeName(boardTypeCode);
	}

	@Override
	public Map<String, Object> readAboardWithComments(Map<String, Object> params) {
		
		List<AdminBoardVO> aboardList = mapper.selectAdminBoardWithComments(params);
		int totalCnt = mapper.selectCountAdminBoard(params); 
		Map<String, Object> resp = new HashMap<String, Object>();
		resp.put("data", aboardList);
		resp.put("totalCnt", totalCnt);
		
		return resp;
	}
	
	@Override
	public Map<String, Object> readAboardPage(Map<String, Object> params) {
    	
		List<AdminBoardVO> aboardList = mapper.selectAdminBoard(params);
		int totalCnt = mapper.selectCountAdminBoard(params); 
		Map<String, Object> resp = new HashMap<String, Object>();
		resp.put("data", aboardList);
		resp.put("totalCnt", totalCnt);
		
		return resp;
	}
	
	@Override
	public Map<String, Object> readAboardByFilter(Map<String, Object> params) {
		List<AdminBoardVO> aboardList = mapper.selectAboardByFilter(params);
		int totalCnt = mapper.selectAboardCountByFilter(params); 
		Map<String, Object> resp = new HashMap<String, Object>();
		resp.put("data", aboardList);
		resp.put("totalCnt", totalCnt);
		log.info("{}", params);
		
		return resp;
	}
	
	@Override
	public Map<String, Object> readNotice(Map<String, Object> params) {
    	
		List<AdminBoardVO> noticeList = mapper.selectNotice(params);
		int totalCnt = mapper.selectCountNotice(params); 
		Map<String, Object> resp = new HashMap<String, Object>();
		resp.put("data", noticeList);
		resp.put("totalCnt", totalCnt);
		
		return resp;
	}

	@Override
	public List<AdminBoardVO> readNoticeList(String boardTypeCode) {
		return mapper.selectNoticeList(boardTypeCode);
	}

}
