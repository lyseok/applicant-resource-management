package kr.or.ddit.conf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import kr.or.ddit.mapper.common.BusinessTypeCodeMapper;
import kr.or.ddit.mapper.common.CityCodeMapper;
import kr.or.ddit.mapper.common.CmnCodeMapper;
import kr.or.ddit.mapper.common.DistrictCodeMapper;
import kr.or.ddit.mapper.common.InduCodeMapper;
import kr.or.ddit.mapper.common.JobMapper;
import kr.or.ddit.mapper.common.TopJobMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CodeMapProvider {
    private Map<String, String> codeMap;

    private final CmnCodeMapper cmnCodeMapper;
    private final TopJobMapper topJobMapper;
    private final JobMapper jobMapper;
    private final CityCodeMapper cityCodeMapper;
    private final DistrictCodeMapper districtCodeMapper;
    private final InduCodeMapper induCodeMapper;
    private final BusinessTypeCodeMapper businessTypeCodeMapper;

    @PostConstruct
    public void init() {
    	codeMap = new HashMap<>();
    	cmnCodeMapper.selectAll()
    		.forEach(m -> codeMap.put(m.getCodeDetailNo(), m.getCodeName()));
    	topJobMapper.selectTopJobList()
    		.forEach(m -> codeMap.put(m.getTopJobCode(), m.getTopJobName()));
    	jobMapper.selectJobList()
	    	.forEach(m -> codeMap.put(m.getJobCode(), m.getJobName()));
    	cityCodeMapper.selectCityCodeList()
	    	.forEach(m -> codeMap.put(m.getCityCodeNo(), m.getCityName()));
    	districtCodeMapper.selectDistrictCodeList()
	    	.forEach(m -> codeMap.put(m.getDistrictCodeNo(), m.getDistrictName()));
    	induCodeMapper.selectInduCodeList()
	    	.forEach(m -> codeMap.put(m.getInduNo(), m.getInduName()));
    	businessTypeCodeMapper.selectBusinessTypeCodeList()
	    	.forEach(m -> codeMap.put(m.getBusinessTypeNo(), m.getBusinessTypeName()));
    	
        log.info("{}", codeMap);
    }

    public String getCodeName(String code) {
        return codeMap.get(code);
    }

    public Map<String, String> getCodeMap() {
        return Collections.unmodifiableMap(codeMap); // 수정불가 map으로 반환 (권장)
    }
}