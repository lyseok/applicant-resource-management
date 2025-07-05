package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.CityCodeVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class CityCodeMapperTest {

	@Autowired
	CityCodeMapper mapper;

	@Test
	void testSelectCityCodeList() {
		mapper.selectCityCodeList().forEach(code -> {
			log.info("{}", code);
		});
	}

	@Test
	void testSelectCityCodeByPk() {
		CityCodeVO CCV = new CityCodeVO();

		CCV.setCityCodeNo("107000");
		mapper.selectCityCodeByPk(CCV);
	}

	@Test
	void testInsertCityCode() {
		CityCodeVO CCV = new CityCodeVO();

		CCV.setCityCodeNo("99999");
		CCV.setCityName("TEST");
		
		mapper.insertCityCode(CCV);
	}

	@Test
	void testUpdateCityCode() {
		CityCodeVO CCV = new CityCodeVO();

		CCV.setCityCodeNo("99999");
		CCV.setCityName("TEST03");	
		mapper.updateCityCode(CCV);
	}

	@Test
	void testDeleteCityCode() {
		CityCodeVO CCV = new CityCodeVO();

		CCV.setCityCodeNo("99999");
		CCV.setCityName("TEST03");
		
		int a = mapper.deleteCityCode(CCV);
		
		log.info("{}", a);
	}

}
