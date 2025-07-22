package kr.or.ddit.rest.comment.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.project.PrjBbsCommentMapper;
import kr.or.ddit.vo.project.PrjBbsCommentVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrjBbsCommentServiceImpl implements PrjBbsCommentService {
    private final PrjBbsCommentMapper commentMapper;

    @Override
    public PrjBbsCommentVO insertPrjBbsComment(PrjBbsCommentVO vo) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	vo.setUserId(username);
        commentMapper.insertPrjBbsComment(vo);
        return commentMapper.selectPrjBbsComment(vo.getCommentNo());
    }

    @Override
    public PrjBbsCommentVO updatePrjBbsComment(PrjBbsCommentVO vo) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	vo.setUserId(username);
        commentMapper.updatePrjBbsComment(vo);
        return commentMapper.selectPrjBbsComment(vo.getCommentNo());
    }

    @Override
    public boolean deletePrjBbsComment(String commentNo) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	PrjBbsCommentVO vo = new PrjBbsCommentVO();
    	vo.setUserId(username);
    	vo.setCommentNo(commentNo);
        return commentMapper.deletePrjBbsComment(vo) > 0;
    }
}