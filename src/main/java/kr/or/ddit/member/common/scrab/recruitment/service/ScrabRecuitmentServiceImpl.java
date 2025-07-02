package kr.or.ddit.member.common.scrab.recruitment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.scrab.ScrabRecuitmentMapper;
import kr.or.ddit.vo.common.ScrabRecuitmentVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScrabRecuitmentServiceImpl implements ScrabRecuitmentService {

	private final ScrabRecuitmentMapper mapper;
	@Override
	public List<ScrabRecuitmentVO> readScrabRecuitmentList() {
		
		return mapper.ScrabRecuitmentList(null );
	}

	@Override
	public void insertScrabRecuitment(ScrabRecuitmentVO ScrabRecuitment) {
		

	}

}
