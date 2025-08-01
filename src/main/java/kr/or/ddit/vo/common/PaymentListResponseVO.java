package kr.or.ddit.vo.common;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentListResponseVO {
	 private List<PaymentVO> payments;
	    private List<Long> cumulativeAmounts;
	    private Map<String, Integer> productCountMap;
	    private long totalUsedAmount;
	    private int totalPages;
}
