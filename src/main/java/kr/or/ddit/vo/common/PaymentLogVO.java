package kr.or.ddit.vo.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "log")
public class PaymentLogVO {
	private String logId;
	private String paymentNo;
	private String productNo;
	private Integer usedCount;
	private String usedAt;
	private String emailAddress;
	private String subject;
	private String messageBody;
}
