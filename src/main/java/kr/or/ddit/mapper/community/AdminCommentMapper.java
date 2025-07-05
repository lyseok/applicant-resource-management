package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.AdminCommentVO;

@Mapper
public interface AdminCommentMapper {

	public AdminCommentVO selectAdminCommentbyPk(String commentNo);
	public List<AdminCommentVO> searchAdminCommentCommentList(String boardNo);  //해당 문의게시글에 대한 관리자답글 모두
	public List<AdminCommentVO> searchAdminCommentList();  //문의게시판 관리자답글 모두
	public int insertAdminComment(AdminCommentVO comment);
	public int updateAdminComment(AdminCommentVO comment);
	public int deleteAdminComment(String commentNo);
}
