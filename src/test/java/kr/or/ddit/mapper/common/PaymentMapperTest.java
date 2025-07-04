package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.PaymentVO;
import lombok.extern.slf4j.Slf4j;
@SpringBootTest
@Slf4j
class PaymentMapperTest {

	
	@Autowired
	PaymentMapper mapper;
	
	@Test
	void testSelectPaymentList() {
		mapper.selectPaymentList().forEach(code->{
			log.info("{}",code);
		});
	}

	@Test
	void testSelectPaymentByPk() {
		PaymentVO pv = new PaymentVO();

		pv.setPaymentNo("PAMT000001");
		mapper.selectPaymentByPk(pv);
		
		
	}

	@Test
	void testInsertPayment() {
		PaymentVO pv = new PaymentVO();

		pv.setUserId("corp03");
		pv.setPaymentMethod("TEST");	
		pv.setPaymentPay("TEST");
		pv.setPaymentContract("TEST");
		pv.setProductNo("PAPD000002");
		
		assertEquals(1, mapper.insertPayment(pv));
		
	}

	@Test
	void testUpdatePayment() {
		PaymentVO pv = new PaymentVO();

		pv.setUserId("TEST01");
		pv.setPaymentMethod("TEST01");	
		pv.setPaymentPay("TEST01");
		pv.setPaymentContract("TEST01");
		pv.setProductNo("PAPD000002");
		
		mapper.updatePayment(pv);
	}

	@Test
	void testDeletePayment() {
		PaymentVO pv = new PaymentVO();

		
		pv.setPaymentNo("PAMT000002"); 
		
		mapper.deletePayment(pv);
	}

}
