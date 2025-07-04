package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.PaymentProductVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class PaymentProductMapperTest {

	@Autowired
	PaymentProductMapper mapper;

	@Test
	void testSelectPaymentProductList() {
		mapper.selectPaymentProductList().forEach(code -> {
			log.info("{}", code);
		});
	}

	@Test
	void testSelectPaymentProductByPk() {
		PaymentProductVO pv = new PaymentProductVO();

		pv.setProductDetail("test1");
		pv.setProductImg("test1");
		pv.setProductName("test1");
		pv.setProductPeriod("test1");
		pv.setProductPrice("test1");

		
		mapper.selectPaymentProductByPk(pv);
	
	}

	@Test
	void testInsertPaymentProduct() {
		PaymentProductVO pv = new PaymentProductVO();

		pv.setProductDetail("test1");
		pv.setProductImg("test1");
		pv.setProductName("test1");
		pv.setProductPeriod("test1");
		pv.setProductPrice("test1");
		
		assertEquals(1, mapper.insertPaymentProduct(pv));
		
	}

	@Test
	void testUpdatePaymentProduct() {
		PaymentProductVO pv = new PaymentProductVO();

		pv.setProductDetail("test01");
		pv.setProductImg("test01");
		pv.setProductName("test01");
		pv.setProductPeriod("test01");
		pv.setProductPrice("test01");
		pv.setProductNo("PAPD000003");
		assertEquals(1, mapper.updatePaymentProduct(pv));
		
	}

	@Test
	void testDeletePaymentProduct() {
		PaymentProductVO pv = new PaymentProductVO();

		pv.setProductNo("PAPD000004");
		
		assertEquals(1, mapper.deletePaymentProduct(pv));
	}

}
