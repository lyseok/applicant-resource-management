package kr.or.ddit.vo.common;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "Link")
public class PaymentProductLinkVO {
	 private String paymentProductLinkId;
	 private String PaymentNo;
	 private String productNo;
	 private int usageAllowed;
	 private int usageRemaining;
	 private String periodStart;
	 private String periodEnd;
	 
	 private List<PaymentVO> paymentVO;
	 private List<PaymentProductVO> paymentProductVO;
	
}
