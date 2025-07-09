package kr.or.ddit.ajax.code.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.ajax.code.service.CodeService;
import kr.or.ddit.vo.common.BusinessTypeCodeVO;
import kr.or.ddit.vo.common.CityCodeVO;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.DistrictCodeVO;
import kr.or.ddit.vo.common.InduClassCodeVO;
import kr.or.ddit.vo.common.InduCodeVO;
import kr.or.ddit.vo.common.JobVO;
import kr.or.ddit.vo.common.TopJobVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ajax/code")
public class CodeAjaxController {
    private final CodeService service;

    @GetMapping("/topjob")
    public List<TopJobVO> topJobCode() {
        return service.readTopJobList();
    }

    @GetMapping("/job")
    public List<JobVO> jobCode() {
        return service.readJobList();
    }

    @GetMapping("/city")
    public List<CityCodeVO> cityCode() {
        return service.readCityCodeList();
    }

    @GetMapping("/district")
    public List<DistrictCodeVO> districtCode() {
        return service.readDistrictCodeList();
    }

    @GetMapping("/induclass")
    public List<InduClassCodeVO> induClassCode() {
        return service.readInduClassCodeList();
    }

    @GetMapping("/indu")
    public List<InduCodeVO> induCode() {
        return service.readInduCodeList();
    }

    @GetMapping("/businesstype")
    public List<BusinessTypeCodeVO> businessTypeCode() {
        return service.readBusinessTypeCodeList();
    }

    // codeGroupNo 파라미터를 받아 단건 조회
    @GetMapping("/cmncodegroup/{no}")
    public CmnCodeGroupVO cmnCodeGroup(@PathVariable("no") String no) {
        return service.readCmnCodeGroupByPk(no);
    }
}
