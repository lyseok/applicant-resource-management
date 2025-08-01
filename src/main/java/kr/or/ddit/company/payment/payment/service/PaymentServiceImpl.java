package kr.or.ddit.company.payment.payment.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.company.payment.product.service.PaymentProductServiceImpl;
import kr.or.ddit.mapper.common.PaymentMapper;
import kr.or.ddit.vo.common.AdminPaymentVO;
import kr.or.ddit.vo.common.ChangeProductResponseVO;
import kr.or.ddit.vo.common.PaymentListResponseVO;
import kr.or.ddit.vo.common.PaymentProductVO;
import kr.or.ddit.vo.common.PaymentVO;
import kr.or.ddit.vo.common.ProductListResponseVO;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentMapper mapper;
	
	@Autowired
	private PaymentProductServiceImpl pservice;
	
	@Autowired
	private TossPaymentService tossPaymentService;
	
	//결제 메인화면
	@Transactional(readOnly = true)
	public PaymentVO getUserCurrentPaymentStatus() {
	    String userId = getUserId();
	    PaymentVO ppvo = selectStauts(userId);

	    if (ppvo != null) {
	        List<PaymentProductVO> productList = pservice.selectPaymentProductListByPk(ppvo.getProductNo());
	        ppvo.setPaymentProductList(productList);

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	        LocalDate today = LocalDate.now();
	        LocalDate end = LocalDate.parse(ppvo.getEndDate(), formatter);
	        long daysRemaining = ChronoUnit.DAYS.between(today, end);

	        ppvo.setDaysRemaining((int) daysRemaining);
	    }

	    return ppvo;
	}

	
	// 요금제 변경하기
	@Transactional
    public void changeProduct(Map<String, String> payload) {
        String orderId = payload.get("orderId");
        String oldPaymentNo = payload.get("oldPaymentNo");
        String newProductNo = payload.get("newProductNo");
        String billingKey = payload.get("billingKey");
        String userId = getUserId(); 

        if (selectScheduledByUserId(userId) != null) {
            throw new IllegalStateException("이미 구매한 상품이 있습니다. 관리자에게 문의하세요");
        }

        PaymentVO oldPayment = selectPaymentByPk(oldPaymentNo);
        LocalDate nextStart = LocalDate.parse(oldPayment.getEndDate(), DateTimeFormatter.ofPattern("yyyyMMdd")).plusDays(1);
        LocalDate nextEnd = nextStart.plusMonths(1);

        cancelPayment(oldPaymentNo);

        PaymentProductVO newProduct = pservice.selectPaymentProductByPk(newProductNo);
        PaymentVO newPayment = createNewPayment(userId, newProduct, billingKey, orderId, nextStart, nextEnd);
        comePayment(newPayment);
    }
	
	
	// 요금제 변경화면으로 이동
	@Transactional(readOnly = true)
	public ChangeProductResponseVO getChangeableProductInfo(String paymentNo, String productNo) {
	    // 현재 결제 내역
	    PaymentVO payment = selectPaymentByPk(paymentNo);
	    if (payment == null) {
	        throw new IllegalArgumentException("존재하지 않는 결제 내역입니다. paymentNo=" + paymentNo);
	    }

	    // 현재 상품 정보
	    List<PaymentProductVO> productList = pservice.selectPaymentProductListByPk(productNo);
	    payment.setPaymentProductList(productList);

	    // 변경 가능한 상품 목록
	    List<PaymentProductVO> allProducts = pservice.selectPaymentProductList();
	    List<PaymentProductVO> changeableProducts = allProducts.stream()
	            .filter(p -> !p.getProductNo().equals(productNo))
	            .collect(Collectors.toList());

	    return new ChangeProductResponseVO(payment, changeableProducts);
	}


    private PaymentVO createNewPayment(String userId, PaymentProductVO newProduct, String billingKey, String orderId, LocalDate start, LocalDate end) {
        PaymentVO newPayment = new PaymentVO();
        newPayment.setUserId(userId);
        newPayment.setProductNo(newProduct.getProductNo());
        newPayment.setPaymentPay(newProduct.getProductPrice());
        newPayment.setPaymentMethod("카드");
        newPayment.setPaymentDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        newPayment.setPaymentBillingKey(billingKey);
        newPayment.setUsageAllowed(newProduct.getProductLimit());
        newPayment.setUsageRemaining(newProduct.getProductLimit());
        newPayment.setStartDate(start.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        newPayment.setEndDate(end.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        newPayment.setStatus("S");
        newPayment.setPaymentOrderId(orderId);
        return newPayment;
    }
    
    // 결제 취소
    @Transactional
    public String cancelUserSubscription(String paymentNo) throws Exception {
        // 1. 결제정보 조회
        String userId = getUserId();
        String PN = getPaymentNo(userId);
        PaymentVO vo = selectPaymentByPk(PN);

        if (vo == null) {
            throw new RuntimeException("결제 정보를 찾을 수 없습니다. paymentNo=" + paymentNo);
        }

        // 2. Toss 해지 요청
        String billingKey = vo.getPaymentBillingKey();
        String paymentKey = vo.getPaymentKey();
        String orderId = vo.getPaymentOrderId();
        String customerKey = "h5hXSJ-WPK8sZQpXQUJUA"; // 상수 or Config 관리

        String result = tossPaymentService.deactivateBillingKey(billingKey, orderId, paymentKey, customerKey);

        // 3. DB 상태 업데이트
        cancelPayment(paymentNo);

        return result; // Toss 응답 그대로 반환
    }

    
    // 결제내역조회
    @Transactional(readOnly = true)
    public PaymentListResponseVO getFilteredPayments(String userId, String filter, int page) {
        List<PaymentVO> purchaseList = selectMyPaymentList(userId);
        LocalDate now = LocalDate.now();

        // 1. 결제 내역에 남은 기간/상품정보 채우기
        for (PaymentVO payment : purchaseList) {
            LocalDate paymentDate = LocalDate.parse(payment.getPaymentDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            long daysLeft = 30 - ChronoUnit.DAYS.between(paymentDate, now);

            List<PaymentProductVO> productList = pservice.selectPaymentProductListByPk(payment.getProductNo());
            if (productList == null) productList = new ArrayList<>();
            for (PaymentProductVO product : productList) product.setDaysRemaining((int) daysLeft);

            payment.setPaymentProductList(productList);
            payment.setStartDate(paymentDate.toString());
            payment.setEndDate(paymentDate.plusDays(30).toString());
        }

        // 2. 필터링
        List<PaymentVO> filteredList = filterPayments(purchaseList, filter);

        // 3. 정렬
        filteredList.sort(Comparator.comparing(PaymentVO::getStartDate));

        // 4. 누적금액 & 상품수량
        Map<String, Integer> productCountMap = new HashMap<>();
        List<Long> cumulativeAmounts = new ArrayList<>();
        long totalUsedAmount = 0;
        for (PaymentVO payment : filteredList) {
            for (PaymentProductVO product : payment.getPaymentProductList()) {
                if (product.getProductName() != null) {
                    productCountMap.merge(product.getProductName(), 1, Integer::sum);
                }
            }
            totalUsedAmount += Long.parseLong(payment.getPaymentPay());
            cumulativeAmounts.add(totalUsedAmount);
        }

        // 5. 페이징
        int pageSize = 10;
        int totalItems = filteredList.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        int offset = (page - 1) * pageSize;
        List<PaymentVO> pageList = filteredList.stream().skip(offset).limit(pageSize).collect(Collectors.toList());
        List<Long> pageCumulativeAmounts = cumulativeAmounts.subList(offset, Math.min(offset + pageSize, cumulativeAmounts.size()));

        // 6. DTO로 묶어서 반환
        return new PaymentListResponseVO(pageList, pageCumulativeAmounts, productCountMap, totalUsedAmount, totalPages);
    }

    private List<PaymentVO> filterPayments(List<PaymentVO> purchaseList, String filter) {
        if ("expired".equals(filter)) {
            return purchaseList.stream()
                    .filter(p -> p.getPaymentProductList().stream().allMatch(prod -> prod.getDaysRemaining() <= 0))
                    .collect(Collectors.toList());
        } else if ("active".equals(filter)) {
            return purchaseList.stream()
                    .filter(p -> p.getPaymentProductList().stream().anyMatch(prod -> prod.getDaysRemaining() > 0))
                    .collect(Collectors.toList());
        } else if (List.of("A", "S", "E", "C").contains(filter)) {
            return purchaseList.stream().filter(p -> filter.equals(p.getStatus())).collect(Collectors.toList());
        }
        return purchaseList;
    }
    
    
    // 정기구독 성공
    @Transactional
    public PaymentVO processSuccessfulBilling(String billingKey, String amount, String orderName, String paymentKey, String orderId) {
        // 상품 조회
        PaymentProductVO product = pservice.selectPaymentProductByName(orderName);
        if (product == null) {
            throw new IllegalArgumentException("존재하지 않는 상품명입니다: " + orderName);
        }
        PaymentVO vo = new PaymentVO();
        vo.setPaymentKey(paymentKey);
        vo.setUserId(getUserId());
        vo.setProductNo(product.getProductNo());
        vo.setPaymentMethod("카드");
        vo.setPaymentBillingKey(billingKey);
        vo.setPaymentPay(amount);
        vo.setUsageAllowed(product.getProductLimit());
        vo.setUsageRemaining(product.getProductLimit());
        vo.setPaymentOrderId(orderId);
        vo.setPaymentProductList(List.of(product));

        log.info("vo : {} ", vo);
        insertPayment(vo);
        updateComPaymentStatus(vo.getUserId());

        return selectPaymentByPk(vo.getPaymentNo());
    }

    // 구매상품보기
    @Transactional
    public PaymentVO BuyDetail(String paymentNo, String productNo) {
    	PaymentVO payment = selectPaymentByPk(paymentNo);
    	if(payment == null) {
    		throw new IllegalArgumentException("결제 내역을 찾을 수 없습니다. 결제번호 = ");
    	}
    	List<PaymentProductVO> productList = pservice.selectPaymentProductListByPk(productNo);
    	payment.setPaymentProductList(productList != null ? productList : new ArrayList<>());
    	
    	return payment;
    }

    // 관리자 상품목록 보기
    @Transactional(readOnly = true)
    public ProductListResponseVO getFilteredProductList(String filterType, int page) {
        int pageSize = 10;
        int offset = (page - 1) * pageSize;

        // 전체 상품 중 'A' 상태만 필터링
        List<PaymentProductVO> productList = pservice.selectPaymentProductList().stream()
                .filter(p -> "A".equals(p.getProductStatus()))
                .toList();

        // 타입별 추가 필터링
        if ("S".equalsIgnoreCase(filterType)) {
            productList = productList.stream()
                    .filter(p -> "BUSINESS".equalsIgnoreCase(p.getProductType()))
                    .toList();
        } else if ("L".equalsIgnoreCase(filterType)) {
            productList = productList.stream()
                    .filter(p -> "PREMIUM".equalsIgnoreCase(p.getProductType()))
                    .toList();
        }

        // 페이징
        int totalItems = productList.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        List<PaymentProductVO> pageList = productList.stream()
                .skip(offset)
                .limit(pageSize)
                .toList();

        return new ProductListResponseVO(pageList, totalPages);
    }

    // 관리자 상품 삭제
    @Transactional
    public void deleteProduct(String productNo) {
        PaymentProductVO vo = pservice.selectPaymentProductByPk(productNo);
        if (vo == null) {
            throw new IllegalArgumentException("상품을 찾을 수 없습니다. productNo=" + productNo);
        }
        vo.setProductStatus("D");
        pservice.updatePaymentProduct(vo);
        log.info("상품 삭제 처리 완료: {}", vo);
    }
    
    // 관리자 상품 수정
    @Transactional(readOnly = true)
    public PaymentProductVO getProductByNo(String productNo) {
        PaymentProductVO product = pservice.selectPaymentProductByPk(productNo);
        if (product == null) {
            throw new IllegalArgumentException("상품을 찾을 수 없습니다. productNo=" + productNo);
        }
        return product;
    }
    
    @Transactional
    public void updateProduct(PaymentProductVO productVO) {
        // 필요 시 이미지 처리
//        if (productVO.getProductImgFile() != null && !productVO.getProductImgFile().isEmpty()) {
//            String uploadedFileName = fileUploadService.saveFile(productVO.getProductImgFile());
//            productVO.setProductImg(uploadedFileName);
//        }
    	
        productVO.setProductStatus("A");
        pservice.updatePaymentProduct(productVO);
        log.info("상품 수정 완료: {}", productVO);
    }

    @Override
    public List<Integer> selectMonthlySales(int year) {
    	return mapper.selectMonthlySales(year);
    }

   
    
    
	
	@Override
	public List<PaymentVO> selectPaymentList() {
		// TODO Auto-generated method stub
		return mapper.selectPaymentList();
	}

	@Override
	public PaymentVO selectPaymentByPk(String paymentNo) {
		// TODO Auto-generated method stub
		return mapper.selectPaymentByPk(paymentNo);
	}

	@Override
	public int insertPayment(PaymentVO vo) {
		vo.setUserId(getUserId());
		
		
		return mapper.insertPayment(vo);
	}

	@Override
	public int updatePayment(PaymentVO vo) {
		// TODO Auto-generated method stub
		return mapper.updatePayment(vo);
	}

	@Override
	public int deletePayment(String paymentNo) {
		// TODO Auto-generated method stub
		return mapper.deletePayment(paymentNo);
	}
	
	@Override
	public List<PaymentVO> selectMyPaymentList(String userId) {
		
		return mapper.selectMyPaymentList(userId);
	}

	@Override
	public String checkbilling(String userId) {
		
		return mapper.checkbilling(userId);
	}
	
	@Override
	public int cancelPayment(String oldPaymentNo) {
		// TODO Auto-generated method stub
		return mapper.cancelPayment(oldPaymentNo);
	}

	public String getUserId() {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	return authentication.getName();		// 기업 ID 
	}

	@Override
	public int comePayment(PaymentVO vo) {
		// TODO Auto-generated method stub
		return mapper.comePayment(vo);
	}

	@Override
	public int checkPayment(String userId) {
		// TODO Auto-generated method stub
		return mapper.checkPayment(userId);
	}

	@Override
	public int updateComPaymentStatus(String userId) {
			
		return mapper.updateComPaymentStatus(userId);
	}

	@Override
	public String getPaymentNo(String userId) {
		// TODO Auto-generated method stub
		return mapper.getPaymentNo(userId);
	}

	@Override
	public PaymentVO selectStauts(String userId) {
		// TODO Auto-generated method stub
		return mapper.selectStauts(userId);
	}

	@Override
	public int minuseaining(String paymentNo) {
		return mapper.minuseaining(paymentNo);
	}

	@Override
	public String newPaymentNo(String userId) {
		// TODO Auto-generated method stub
		return mapper.newPaymentNo(userId);
	}

	@Override
	public PaymentVO selectScheduledByUserId(String userId) {
		// TODO Auto-generated method stub
		return mapper.selectScheduledByUserId(userId);
	}

	@Override
	public void monthlySubscriptionRefresh() {
	
	}

	@Override
	public List<AdminPaymentVO> selectPaymentStatistics() {
		// TODO Auto-generated method stub
		return mapper.selectPaymentStatistics();
	}

	@Override
	public PaymentVO cancleSub(String userId) {
		// TODO Auto-generated method stub
		return mapper.cancleSub(userId);
	}

	@Override
	public List<String> allcompanyName() {
		// TODO Auto-generated method stub
		return mapper.allcompanyName();
	}

	@Override
	public List<String> selectNewSubscribers() {
		// TODO Auto-generated method stub
		return mapper.selectNewSubscribers();
	}

	@Override
	public List<String> selectChurnedSubscribers() {
		// TODO Auto-generated method stub
		return mapper.selectChurnedSubscribers();
	}

	@Override
	public List<String> selectLeftSubscribers() {
		// TODO Auto-generated method stub
		return mapper.selectLeftSubscribers();
	}


	@Override
	public List<Integer> selectMonthlySalesCompare(int year) {
		// TODO Auto-generated method stub
		return null;
	}


	



	

	
}
