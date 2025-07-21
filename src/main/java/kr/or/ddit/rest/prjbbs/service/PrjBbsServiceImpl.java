package kr.or.ddit.rest.prjbbs.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.mapper.project.PrjBbsMapper;
import kr.or.ddit.vo.project.PrjBbsVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PrjBbsServiceImpl implements PrjBbsService {
	private final PrjBbsMapper prjBbsMapper;

	@Override
    public List<PrjBbsVO> getPrjBbsList(String prjNo, int page, int size) {
        return prjBbsMapper.selectProjectPosts(prjNo, page, size);
    }
	
	@Override
    public PrjBbsVO getPrjBbsWithComments(String prjPostNo) {
        return prjBbsMapper.selectPrjBbsWithComments(prjPostNo);
    }
	
	@Override
    public PrjBbsVO createPrjBbs(PrjBbsVO prjBbs) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		prjBbs.setUserId(username);
		
        prjBbsMapper.insertPrjBbs(prjBbs);

        // 생성된 게시글(댓글 포함) 다시 조회
        return prjBbsMapper.selectPrjBbsWithComments(prjBbs.getPrjPostNo());
    }
	
	@Override
	public PrjBbsVO updatePrjBbs(PrjBbsVO prjBbs) {
		PrjBbsVO dbVo = prjBbsMapper.selectPrjBbsWithComments(prjBbs.getPrjPostNo());
		
		log.info("===========>> {}", dbVo);
		
        // 2. 변경된 값만 체크해서 dbVo에 세팅
        if(prjBbs.getTitle() != null && !prjBbs.getTitle().equals(dbVo.getTitle())) {
            dbVo.setTitle(prjBbs.getTitle());
        }
        if(prjBbs.getContent() != null && !prjBbs.getContent().equals(dbVo.getContent())) {
            dbVo.setContent(prjBbs.getContent());
        }
        prjBbsMapper.updatePrjBbs(dbVo);
        // 갱신된 데이터 다시 조회해서 반환
        return prjBbsMapper.selectPrjBbsWithComments(dbVo.getPrjPostNo());
    }
	
	@Override
	public int deletePrjBbs(String prjPostNo) {
	    return prjBbsMapper.deletePrjBbs(prjPostNo);
	}

}
