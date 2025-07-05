package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.PaymentListVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class PaymentListMapperTest {

	@Autowired
	PaymentListMapper mapper;
	
	@Test
	void testselectPaymentList() {
		mapper.selectPaymentList().forEach(code->{
			log.info("{}",code);
		});
	}

	@Test
	void testSelectPaymentByPk() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertPayment() {
		PaymentListVO plv = new PaymentListVO();
		
		plv.setPaymentAmount("TEST");
		plv.setPaymentApprovalnum("TEST");
		plv.setPaymentNo("TEST");
		plv.setPaymentReceipt("TEST");
		plv.setPaymentStartDay("TEST");
		plv.setPaymentStatus("Y");
		
		mapper.insertPayment(plv);
		
		log.info("{}", plv);
		
	}

	@Test
	void testUpdatePayment() {
		fail("Not yet implemented");
	}

	@Test
	void testDeletePayment() {
		fail("Not yet implemented");
	}

}
