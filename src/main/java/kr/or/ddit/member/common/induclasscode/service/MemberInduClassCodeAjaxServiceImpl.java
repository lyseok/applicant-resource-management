package kr.or.ddit.member.common.induclasscode.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.InduClassCodeMapper;
import kr.or.ddit.vo.common.InduClassCodeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberInduClassCodeAjaxServiceImpl implements MemberInduClassCodeAjaxService {
	
	private final InduClassCodeMapper mapper;

	@Override
	public List<InduClassCodeVO> readInduClassCodeList() {
		return mapper.selectInduClassCodeList();
	}

	@Override
	public InduClassCodeVO readInduClassCodeBuPk(String no) {
		return mapper.selectInduClassCodeBuPk(no);
	}

}
