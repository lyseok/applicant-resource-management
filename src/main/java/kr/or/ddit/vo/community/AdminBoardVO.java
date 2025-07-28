package kr.or.ddit.vo.community;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.UsersVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

//관리자 게시판
@Data
@EqualsAndHashCode(of="boardNo")
public class AdminBoardVO implements Serializable {
	private String boardNo;
	private String userId;
	@NotBlank(message = "게시판 유형을 선택해주세요.")
	private String boardTypeCode;
	@NotBlank(message = "제목을 입력해주세요.")
	private String boardTitle;
	private String boardWriteDate;
	@NotBlank(message = "내용을 입력해주세요.")
	private String boardContent;
	private String boardDeleteDate;
	private Integer boardPostHit;
	private String boardStatus;
	
	private String codeName;  //공통코드그룹명에 따른 공통코드 리스트 가져옴

	private transient UsersVO users;
	private transient List<AdminCommentVO> adminCommentList;  // 게시판 댓글 리스트
	
	/*
	1. 게시판 유형 코드(AdminBoardVO.boardTypeCode = CmnCodeVO.codeDetailNo = CmnCodeVO.upperCodeNo)

	1-1. 일반회원(CmnCodeGroupVO.codeGroupNo)
		1-1-1. 이력서등록/관리(CmnCodeVO.codeDetailNo, memType)
		1-1-2. 회원정보/아이디/비밀번호
		...
	
	1-2. 기업회원(CmnCodeGroupVO.codeGroupNo)
		1-2-1. 채용정보 등록/관리
		1-2-2. 유료서비스/결제
		...
	 */
	//AdminBoardVO(하나의 게시판 유형) : CmnCodeGroupVO(일반/기업회원) = 1 : N
	private List<CmnCodeGroupVO> cmnCodeGroupVOList;
}
