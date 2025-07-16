package kr.or.ddit.admin.common.cityCode.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.admin.common.cityCode.service.CityCodeService;
import kr.or.ddit.vo.common.CityCodeVO;
import kr.or.ddit.vo.common.DistrictCodeVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/admin/cityCode")
public class CityCodeAjaxController {

	private final CityCodeService service;
	
	@GetMapping
	public List<CityCodeVO> getCityCodeList(){
		return service.readCityCode();
	}
	
	@GetMapping("/{no}")
	public List<DistrictCodeVO> getDistrictCodeList(@PathVariable String no){
		return service.readDistrictCodeByCity(no);
	}
}
