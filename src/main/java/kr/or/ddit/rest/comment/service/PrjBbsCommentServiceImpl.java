package kr.or.ddit.rest.comment.service;

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
        // COMMENT_NO 생성 로직 필요 시 추가 (예: UUID, 시퀀스 등)
        commentMapper.insertPrjBbsComment(vo);
        return commentMapper.selectPrjBbsComment(vo.getCommentNo());
    }

    @Override
    public PrjBbsCommentVO updatePrjBbsComment(PrjBbsCommentVO vo) {
        commentMapper.updatePrjBbsComment(vo);
        return commentMapper.selectPrjBbsComment(vo.getCommentNo());
    }

    @Override
    public boolean deletePrjBbsComment(String commentNo) {
        return commentMapper.deletePrjBbsComment(commentNo) > 0;
    }
}