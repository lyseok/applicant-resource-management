package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.RefundVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class RefundMapperTest {

	@Autowired
	RefundMapper mapper;

	@Test
	void testSelectRefundList() {
		mapper.selectRefundList().forEach(code -> {
			log.info("{}", code);
		});
	}

	@Test
	void testSelectRefundByPk() {
		RefundVO rv = new RefundVO();

		rv.setRefundNo("REFU000001");

		mapper.selectRefundByPk(rv);
	}

	@Test
	void testInsertRefund() {
		RefundVO rv = new RefundVO();

		rv.setPaymentNo("PAMT000001");
		rv.setPaymentPay("test");
		rv.setProductName("test");
		rv.setRefundAccount("test");
		rv.setRefundRatio("100");
		rv.setRefundReason("test");

		assertEquals(1, mapper.insertRefund(rv));
	}

	@Test
	void testUpdateRefund() {
		
		RefundVO rv = new RefundVO();

		rv.setPaymentNo("PAMT000001");
		rv.setPaymentPay("test01");
		rv.setProductName("test01");
		rv.setRefundAccount("test01");
		rv.setRefundRatio("100");
		rv.setRefundReason("test01");
		
		rv.setRefundNo("REFU000001");
		
		assertEquals(1, mapper.updateRefund(rv));
	}

	@Test
	void testDeleteRefund() {
		RefundVO rv = new RefundVO();

		rv.setRefundNo("REFU000001");
		
		assertEquals(1, mapper.deleteRefund(rv));
	}

}
