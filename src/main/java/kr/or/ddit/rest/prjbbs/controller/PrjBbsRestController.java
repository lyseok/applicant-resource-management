package kr.or.ddit.rest.prjbbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.rest.prjbbs.service.PrjBbsService;
import kr.or.ddit.vo.project.PrjBbsVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class PrjBbsRestController {
	private final PrjBbsService prjBbsService;
	
	@GetMapping("/projects/{prjNo}/posts")
	public ResponseEntity<List<PrjBbsVO>> getPrjBbsListApi(
	    @PathVariable String prjNo,
	    @RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "10") int size
	) {
	    List<PrjBbsVO> posts = prjBbsService.getPrjBbsList(prjNo, page, size); 
	    return ResponseEntity.ok(posts);
	}
	
	@GetMapping("/posts/{prjPostNo}")
    public ResponseEntity<PrjBbsVO> getPrjBbsApi(@PathVariable String prjPostNo) {
        PrjBbsVO prjBbs = prjBbsService.getPrjBbsWithComments(prjPostNo);
        if (prjBbs != null) {
            return ResponseEntity.ok(prjBbs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@PostMapping("/posts")
    public ResponseEntity<PrjBbsVO> postPrjBbsApi(@RequestBody PrjBbsVO prjBbs) {
        PrjBbsVO created = prjBbsService.createPrjBbs(prjBbs);
        return ResponseEntity.status(201).body(created);
    }
	
	@PutMapping("/posts/{prjPostNo}")
	public ResponseEntity<PrjBbsVO> putPrjBbsApi(
		@PathVariable String prjPostNo,
        @RequestBody PrjBbsVO prjBbs
    ) {
		prjBbs.setPrjPostNo(prjPostNo);
		PrjBbsVO updated = prjBbsService.updatePrjBbs(prjBbs);
	    return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/posts/{prjPostNo}")
	public ResponseEntity<?> deletePrjBbsApi(@PathVariable String prjPostNo) {
	    int result = prjBbsService.deletePrjBbs(prjPostNo);
	    if (result > 0) {
	        // 성공 시 200 OK + 메시지 반환
	        return ResponseEntity.ok(Map.of(
	            "message", "게시글이 성공적으로 삭제되었습니다.",
	            "prjPostNo", prjPostNo
	        ));
	    } else {
	        // 실패시 404 Not Found 등 반환
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of(
	                    "error", "NOT_FOUND",
	                    "message", "삭제할 게시글을 찾을 수 없습니다.",
	                    "prjPostNo", prjPostNo
	                ));
	    }
	}
}
