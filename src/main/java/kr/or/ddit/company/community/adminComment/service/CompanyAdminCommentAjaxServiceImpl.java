package kr.or.ddit.company.community.adminComment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.community.AdminCommentMapper;
import kr.or.ddit.vo.community.AdminCommentVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyAdminCommentAjaxServiceImpl implements CompanyAdminCommentAjaxService {
	
	private final AdminCommentMapper mapper;

	@Override
	public Optional<AdminCommentVO> readAdminCommentbyPk(String commentNo) {
		return Optional.ofNullable(mapper.selectAdminCommentbyPk(commentNo));
	}

	@Override
	public List<AdminCommentVO> searchAdminCommentCommentList(String boardNo) {
		return mapper.searchAdminCommentCommentList(boardNo);
	}

	@Override
	public List<AdminCommentVO> searchAdminCommentList() {
		return mapper.searchAdminCommentList();
	}

	@Override
	public void createAdminComment(AdminCommentVO comment) {
		mapper.insertAdminComment(comment);	
	}

	@Override
	public void modifyAdminComment(AdminCommentVO comment) {
		mapper.updateAdminComment(comment);
	}

	@Override
	public void removeAdminComment(String commentNo) {
		mapper.deleteAdminComment(commentNo);
	}


}
