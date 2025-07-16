package kr.or.ddit.member.community.commuTag.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.community.commuTag.service.MemberCommuTagService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.community.CommuTagVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/member/board/commu_tag")
@RequiredArgsConstructor
public class MemberCommuTagAjaxController {

	private final MemberCommuTagService service;
	private final ErrorsUtils errorsUtils;  //검증 추가해야 함
	
	@GetMapping("/{tagNo}/{boardNo}")
	@ResponseBody
	public ResponseEntity<CommuTagVO> getOnetag(
		@PathVariable String tagNo
		, @PathVariable String boardNo
	) {
	    return service.readCommuTagByPk(tagNo, boardNo)
	    		.map(ResponseEntity::ok)
	            .orElse(ResponseEntity.status(404).body(null));  //없으면 404 반환
	}
	
	@GetMapping("/{boardNo}")
	public List<CommuTagVO> getTags(@PathVariable String boardNo){
		return service.searchCommuTagTagList(boardNo);
	}
	
	@GetMapping("/{tagNo}")
	public List<CommuTagVO> getBoards(@PathVariable String tagNo){
		return service.searchCommuTagBoardList(tagNo);
	}
	
	@GetMapping
	public List<CommuTagVO> getAll(){
		return service.readCommuTagList();
	}
	
	@PostMapping("/{tagNo}")
	public Map<String, Object> intag(@RequestBody CommuTagVO tag) {
		service.createCommuTag(tag);
	    return Map.of("ok", true);
	}

	@DeleteMapping("/{tagNo}/{boardNo}")
	public Map<String, Object> deletetag(
		@PathVariable String tagNo
		, @PathVariable String boardNo	
	) {
		service.removeCommuTag(tagNo, boardNo);
		return Map.of("ok", true);
	}
}
