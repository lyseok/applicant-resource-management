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
    private Map<String, String> topJobMap;
    private Map<String, String> jobMap;
    private Map<String, String> cityMap;
    private Map<String, String> districtMap;
    private Map<String, String> induMap;
    private Map<String, String> businessMap;

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
        topJobMap = new HashMap<>();
        jobMap = new HashMap<>();
        cityMap = new HashMap<>();
        districtMap = new HashMap<>();
        induMap = new HashMap<>();
        businessMap = new HashMap<>();
        
    	cmnCodeMapper.selectAll()
    		.forEach(m -> codeMap.put(m.getCodeDetailNo(), m.getCodeName()));
    	topJobMapper.selectTopJobList()
    		.forEach(m -> topJobMap.put(m.getTopJobCode(), m.getTopJobName()));
    	jobMapper.selectJobList()
	    	.forEach(m -> jobMap.put(m.getJobCode(), m.getJobName()));
    	cityCodeMapper.selectCityCodeList()
	    	.forEach(m -> cityMap.put(m.getCityCodeNo(), m.getCityName()));
    	districtCodeMapper.selectDistrictCodeList()
	    	.forEach(m -> districtMap.put(m.getDistrictCodeNo(), m.getDistrictName()));
    	induCodeMapper.selectInduCodeList()
	    	.forEach(m -> induMap.put(m.getInduNo(), m.getInduName()));
    	businessTypeCodeMapper.selectBusinessTypeCodeList()
	    	.forEach(m -> businessMap.put(m.getBusinessTypeNo(), m.getBusinessTypeName()));
    	
        log.info("{}", codeMap);
    }

    public String getCodeName(String code) {
        return codeMap.get(code);
    }
    
    public String getTopJobName(String code) {
        return topJobMap.get(code);
    }

    public String getJobName(String code) {
        return jobMap.get(code);
    }

    public String getCityName(String code) {
        return cityMap.get(code);
    }

    public String getDistrictName(String code) {
        return districtMap.get(code);
    }

    public String getInduName(String code) {
        return induMap.get(code);
    }

    public String getBusinessTypeName(String code) {
        return businessMap.get(code);
    }
}