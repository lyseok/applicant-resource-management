package kr.or.ddit.rest.comment.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.rest.comment.service.PrjBbsCommentService;
import kr.or.ddit.vo.project.PrjBbsCommentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/comments")
@Slf4j
@RequiredArgsConstructor
public class PrjBbsCommentRestController {
    private final PrjBbsCommentService prjBbsCommentService;

    // 댓글 등록
    @PostMapping
    public ResponseEntity<PrjBbsCommentVO> postPrjBbsCommentApi(@RequestBody PrjBbsCommentVO prjBbsComment) {
        PrjBbsCommentVO saved = prjBbsCommentService.insertPrjBbsComment(prjBbsComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // 댓글 수정
    @PutMapping("/{commentNo}")
    public ResponseEntity<PrjBbsCommentVO> putPrjBbsCommentApi(
            @PathVariable String commentNo,
            @RequestBody PrjBbsCommentVO prjBbsComment) {
        prjBbsComment.setCommentNo(commentNo);
        PrjBbsCommentVO updated = prjBbsCommentService.updatePrjBbsComment(prjBbsComment);
        return ResponseEntity.ok(updated);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentNo}")
    public ResponseEntity<?> deletePrjBbsCommentApi(@PathVariable String commentNo){
        boolean deleted = prjBbsCommentService.deletePrjBbsComment(commentNo);
        if (deleted) {
            return ResponseEntity.ok(Map.of(
                "message", "댓글이 성공적으로 삭제되었습니다.",
                "commentNo", commentNo
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "NOT_FOUND", "commentNo", commentNo));
        }
    }
}
