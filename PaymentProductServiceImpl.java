package kr.or.ddit.company.payment.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.mapper.common.PaymentProductMapper;
import kr.or.ddit.vo.common.PaymentProductVO;
import kr.or.ddit.vo.common.ProductListResponseVO;

@Service
public class PaymentProductServiceImpl implements PaymentProductService {

	@Autowired
	PaymentProductMapper mapper;
	
	@Transactional(readOnly = true)
	public PaymentProductVO getProductDetail(String productNo) {
	    PaymentProductVO product = selectPaymentProductByPk(productNo);
	    if (product == null) {
	        throw new IllegalArgumentException("존재하지 않는 상품입니다. productNo=" + productNo);
	    }
	    return product;
	}

	
	@Transactional(readOnly = true)
	public ProductListResponseVO getProductList(String filterType, int page) {
	    int pageSize = 10;
	    int offset = (page - 1) * pageSize;

	    List<PaymentProductVO> productList = selectPaymentProductList();

	    // 필터링
	    List<PaymentProductVO> filterList = productList;
	    if ("BUSINESS".equalsIgnoreCase(filterType)) {
	        filterList = productList.stream()
	                .filter(p -> "BUSINESS".equalsIgnoreCase(p.getProductType()))
	                .toList();
	    } else if ("PREMIUM".equalsIgnoreCase(filterType)) {
	        filterList = productList.stream()
	                .filter(p -> "PREMIUM".equalsIgnoreCase(p.getProductType()))
	                .toList();
	    }

	    // 페이징
	    int totalItems = filterList.size();
	    int totalPages = (int) Math.ceil((double) totalItems / pageSize);
	    List<PaymentProductVO> pageList = filterList.stream()
	            .skip(offset)
	            .limit(pageSize)
	            .toList();

	    return new ProductListResponseVO(pageList, totalPages);
	}

	
	@Override
	public List<PaymentProductVO> selectPaymentProductList() {
		// TODO Auto-generated method stub
		return mapper.selectPaymentProductList();
	}

	@Override
	public PaymentProductVO selectPaymentProductByPk(String productNo) {
		// TODO Auto-generated method stub
		return mapper.selectPaymentProductByPk(productNo);
	}

	@Override
	public int insertPaymentProduct(PaymentProductVO vo) {
		// TODO Auto-generated method stub
		return mapper.insertPaymentProduct(vo);
	}

	@Override
	public int updatePaymentProduct(PaymentProductVO vo) {
		// TODO Auto-generated method stub
		return mapper.updatePaymentProduct(vo);
	}

	@Override
	public int deletePaymentProduct(String productNo) {
		// TODO Auto-generated method stub
		return mapper.deletePaymentProduct(productNo);
	}

	@Override
	public PaymentProductVO selectPaymentProductByName(String productName) {
		// TODO Auto-generated method stub
		return mapper.selectPaymentProductByName(productName);
	}

	public List<PaymentProductVO> selectPaymentProductListByPk(String productNo) {
		
		return mapper.selectPaymentProductListByPk(productNo);
	}


	



}
