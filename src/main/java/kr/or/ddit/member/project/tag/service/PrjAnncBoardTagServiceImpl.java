package kr.or.ddit.member.project.tag.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.exception.DataInsertException;
import kr.or.ddit.common.exception.DataUpdateException;
import kr.or.ddit.mapper.common.TagMapper;
import kr.or.ddit.mapper.project.PrjAnncBoardTagMapper;
import kr.or.ddit.vo.project.PrjAnncBoardTagVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrjAnncBoardTagServiceImpl implements PrjAnncBoardTagService {
	public final PrjAnncBoardTagMapper prjAnncBoardTagMapper;
	public final TagMapper tagMapper;

	@Override
	public void insertPrjAnncBoardTag(PrjAnncBoardTagVO vo) {
		String tagNo = tagMapper.selectTagNo(vo.getTag().getTagName());
		
		if(tagNo == null) {
			int result = tagMapper.insertTag(vo.getTag());
			if (result == 0) {
	            throw new DataInsertException("태그 생성에 실패했습니다.");
	        }
		} else {
			vo.getTag().setTagNo(tagNo);
		}
		
		vo.setTagNo(vo.getTag().getTagNo());
		
		prjAnncBoardTagMapper.deletePrjAnncBoardTag(vo);
		int result = prjAnncBoardTagMapper.insertPrjAnncBoardTag(vo);
		if (result == 0) {
			throw new DataUpdateException("프로젝트 태그 수정에 실패했습니다.");
		}
		
	}
	
	
}
