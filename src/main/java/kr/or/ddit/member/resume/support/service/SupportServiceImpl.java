package kr.or.ddit.member.resume.support.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.SupportMapper;
import kr.or.ddit.vo.resume.SupportVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupportServiceImpl implements SupportService {
	private final SupportMapper mapper;
	
	@Override
	public List<SupportVO> readSupportList(String no) {
		return mapper.selectSupportList(no);
	}

	@Override
	public SupportVO readSupportDetail(SupportVO vo) {
		return mapper.selectSupportDetail(vo);
	}

	@Override
	public void createSupport(SupportVO vo) {
		mapper.insertSupport(vo);
	}

	@Override
	public void editSupport(SupportVO vo) {
		mapper.updateSupport(vo);
	}

	@Override
	public void removeSupport(String no) {
		mapper.deleteSupport(no);
	}

}
