package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.DistrictCodeVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class DistrictCodeMapperTest {

	@Autowired
	DistrictCodeMapper mapper;

	@Test
	void testSelectDistrictCodeList() {
		
		mapper.selectDistrictCodeList().forEach(code->{
			log.info("{}", code);
		});
	}

	@Test
	void testSelectDistrictCodeByPk() {
		DistrictCodeVO DCV = new DistrictCodeVO();

		DCV.setCityCodeNo("9090");
		DCV.setDistrictCodeNo("101000");
		
		mapper.selectDistrictCodeByPk(DCV);
	}

	@Test
	void testInsertDistrictCode() {
		DistrictCodeVO DCV = new DistrictCodeVO();

		DCV.setCityCodeNo("101000");
		DCV.setDistrictCodeNo("999999");
		DCV.setDistrictName("낙성동");
		
		mapper.insertDistrictCode(DCV);
	}

	@Test
	void testUpdateDistrictCode() {
		DistrictCodeVO DCV = new DistrictCodeVO();

		DCV.setCityCodeNo("101000");
		DCV.setDistrictCodeNo("999999");
		DCV.setDistrictName("TEST00");
			
		mapper.updateDistrictCode(DCV);
	}

	@Test
	void testDeleteDistrictCode() {
		DistrictCodeVO DCV = new DistrictCodeVO();

		DCV.setCityCodeNo("101000");
		DCV.setDistrictCodeNo("999999");
		
		mapper.deleteDistrictCode(DCV);
	}

}
