package kr.or.ddit.rest.comment.service;

import kr.or.ddit.vo.project.PrjBbsCommentVO;

public interface PrjBbsCommentService {
	PrjBbsCommentVO insertPrjBbsComment(PrjBbsCommentVO vo);
    PrjBbsCommentVO updatePrjBbsComment(PrjBbsCommentVO vo);
    boolean deletePrjBbsComment(String commentNo);
}
