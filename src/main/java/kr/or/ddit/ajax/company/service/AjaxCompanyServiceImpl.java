package kr.or.ddit.ajax.company.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.CompanyMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AjaxCompanyServiceImpl implements AjaxCompanyService {
	private final CompanyMapper mapper;
	
	@Override
	public List<Map<String, Object>> readCompanyNameList() {
		return mapper.selectCompanyNameList();
	}

}
