package kr.or.ddit.company.recruitment.mailtemplate.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.recruitment.ComMailTemMapper;
import kr.or.ddit.vo.recruitment.ComMailTemVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ComMailTemServiceImpl implements ComMailTemService {
	private final ComMailTemMapper comMailTemMapper;
	
	@Override
	public List<ComMailTemVO> readComMailTemList() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return comMailTemMapper.selectComMailTemList(username);
	}

	@Override
	public ComMailTemVO readComMailTem(ComMailTemVO comMailTem) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		comMailTem.setUserId(username);
		return comMailTemMapper.selectComMailTem(comMailTem);
	}

	@Override
	public int createComMailTem(ComMailTemVO comMailTem) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		comMailTem.setUserId(username);
		return comMailTemMapper.insertComMailTem(comMailTem);
	}

	@Override
	public int modifyComMailTem(ComMailTemVO comMailTem) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		comMailTem.setUserId(username);
		return comMailTemMapper.updateComMailTem(comMailTem);
	}

	@Override
	public int removeComMailTem(String no) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		ComMailTemVO comMailTem = new ComMailTemVO();
		comMailTem.setUserId(username);
		comMailTem.setTemNo(no);
		return comMailTemMapper.deleteComMailTem(comMailTem);
	}

}
