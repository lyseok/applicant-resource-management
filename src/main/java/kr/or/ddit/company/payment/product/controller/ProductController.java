package kr.or.ddit.company.payment.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.company.payment.product.service.PaymentProductService;
import kr.or.ddit.company.payment.product.service.PaymentProductServiceImpl;
import kr.or.ddit.vo.common.PaymentProductVO;
import kr.or.ddit.vo.common.ProductListResponseVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/company/payment/product")
public class ProductController {

	@Autowired
	PaymentProductServiceImpl service;

//	// service 분리
//	@GetMapping("/detail")
//	public String detailForm(@RequestParam String productNo, @ModelAttribute("billingKey") String billingKey,
//			Model model) {
//		PaymentProductVO product = service.getProductDetail(productNo);
//		model.addAttribute("product", product);
//		model.addAttribute("billingKey", billingKey);
//		return "company/payment/product/ProductDetail";
//	}

	// service 분리
	@GetMapping("/list")
	public String listForm(
	        @RequestParam(value = "page", defaultValue = "1") int page,
	        @RequestParam(value = "filterType", required = false) String filterType,
	        Model model
	) {
	    ProductListResponseVO response = service.getProductList(filterType, page);

	    model.addAttribute("productList", response.getProducts());
	    model.addAttribute("totalPages", response.getTotalPages());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("filterType", filterType);

	    return "company/payment/product/ProductList";
	}
 

//	@Value("${file.upload-dir}")
//	private String RealuploadPath;

	@GetMapping("/detail")
	public String detailForm(
		@RequestParam String productNo,
		Model model,
		@ModelAttribute("billingKey")String billingKey
			) {
		PaymentProductVO product = service.selectPaymentProductByPk(productNo);
		model.addAttribute("product",product);
		model.addAttribute("billingKey",billingKey);
		
		return "company/payment/product/ProductDetail";
	}

//	@GetMapping("/list")
//	String listForm(@RequestParam(value = "page", defaultValue = "1") int page,
//			@RequestParam(value = "filterType", required = false) String filterType, Model model) {
//		int pageSize = 10;
//		int offset = (page - 1) * pageSize;
//		List<PaymentProductVO> productList = service.selectPaymentProductList();
//
//		List<PaymentProductVO> filterList = productList;
//		if("S".equals(filterType)) {
//			filterList = productList.stream()
//					.filter(p->"S".equalsIgnoreCase(p.getProductType()))
//					.toList();
//		} else if ("L".equalsIgnoreCase(filterType)) {
//		    filterList = productList.stream()
//		            .filter(p -> "L".equalsIgnoreCase(p.getProductType()))
//		            .toList();
//		    }
//		
//		int totalItems = filterList.size();
//		int totalPages = (int)Math.ceil((double) totalItems/pageSize);
//		List<PaymentProductVO> pageList = filterList.stream()
//										.skip(offset)
//										.limit(pageSize)
//										.toList();
//		
//		
//		model.addAttribute("totalPages", totalPages);
//		model.addAttribute("currentPage", page);
//		model.addAttribute("productList", pageList);
//		
//		return "company/payment/product/ProductList";
//	}

}
