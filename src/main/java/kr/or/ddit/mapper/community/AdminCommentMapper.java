package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.AdminCommentVO;

@Mapper
public interface AdminCommentMapper {

	public AdminCommentVO selectAdminComment(String commentNo);
	public List<AdminCommentVO> selectAdminCommentList(String boardNo);  //해당 문의게시글에 대한 관리자답글 리스트. 그럼 문의글:답글은 1:N 관계?
	public int insertAdminComment(AdminCommentVO comment);
	public int updateAdminComment(AdminCommentVO comment);
	public int deleteAdminComment(String commentNo);
}
