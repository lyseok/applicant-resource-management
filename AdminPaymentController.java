package kr.or.ddit.admin.common.payment.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.company.payment.payment.service.PaymentServiceImpl;
import kr.or.ddit.company.payment.product.service.PaymentProductService;
import kr.or.ddit.vo.common.AdminPaymentVO;
import kr.or.ddit.vo.common.PaymentProductVO;
import kr.or.ddit.vo.common.PaymentVO;
import kr.or.ddit.vo.common.ProductListResponseVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminPaymentController {

//	@Autowired
//	private FileUploadService fileUploadService;

	@Autowired
	PaymentProductService service;

	@Autowired
	PaymentServiceImpl pservice;

	@GetMapping("/add")
	String addForm() {
		return "admin/payment/InsertProduct";
	}

	// 관리자 상품보기
	@GetMapping("/product/list")
	public String listForm(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "filterType", required = false) String filterType, Model model) {

		ProductListResponseVO response = pservice.getFilteredProductList(filterType, page);

		model.addAttribute("totalPages", response.getTotalPages());
		model.addAttribute("currentPage", page);
		model.addAttribute("productList", response.getProducts());

		return "admin/payment/AdminproductList";
	}

	// 상품 삭제
	@GetMapping("/delete/product")
	public String deleteProduct(@RequestParam String productNo, RedirectAttributes redirectAttributes) {
		service.deletePaymentProduct(productNo);
		redirectAttributes.addFlashAttribute("message", "삭제가 완료되었습니다.");
		return "redirect:/admin/product/list";
	}

	// 관리자 상품 수정
	@GetMapping("/update/product")
	public String productUpdate(@RequestParam("productNo") String productNo, Model model) {
		PaymentProductVO product = pservice.getProductByNo(productNo);
		model.addAttribute("product", product);
		return "admin/payment/UpdateProduct";
	}

	@PostMapping("/update/done")
	public String productDone(PaymentProductVO productVO, RedirectAttributes redirectAttributes) {
		log.info("productVO : {}", productVO);
		log.info("redirectAttributes : {}", redirectAttributes);
		pservice.updateProduct(productVO);
		redirectAttributes.addFlashAttribute("message", "상품수정이 완료되었습니다");
		return "redirect:/admin/product/list";
	}

	@GetMapping("/detail")
	public String productDetail(@RequestParam("productNo") String productNo, Model model) {
		PaymentProductVO product = service.selectPaymentProductByPk(productNo);
		model.addAttribute("product", product);
		return "admin/payment/AdminProductDetail";

	}

	@PostMapping("/insert")
	public String insertProduct(@ModelAttribute PaymentProductVO productVO, @RequestParam String productType,
			Model model) {
		log.info("productType: {}", productType);
		log.info("등록된 상품 {} ", productVO);
		model.addAttribute("productVO", productVO);
		service.insertPaymentProduct(productVO);
		return "company/payment/product/ResultProductInsert";
	}

	

	@GetMapping("/payment/sales-data")
	@ResponseBody
	public Map<String, Object> getSalesData(@RequestParam int year) {
		int lastYear = year - 1;
		List<Integer> thisYearSales = pservice.selectMonthlySales(year);
		List<Integer> lastYearSales = pservice.selectMonthlySales(lastYear);

		log.info("thisYearSales : {}", thisYearSales);
		log.info("lastYearSales : {}", lastYearSales);
		Map<String, Object> result = new HashMap<>();
		result.put("thisYearSales", thisYearSales);
		result.put("lastYearSales", lastYearSales);
		return result;
	}

	@GetMapping("/select/product")
	public String selectFormUI(Model model) {
		List<AdminPaymentVO> getPaymentStatistics = pservice.selectPaymentStatistics();
		List<String> allcompany = pservice.allcompanyName();
		List<String> newSubscribers = pservice.selectNewSubscribers();
		List<String> ChurnedSubscribers = pservice.selectChurnedSubscribers();
		List<String> LeftSubscribers = pservice.selectLeftSubscribers();

		log.info("이건 뭐라 나오나 보자 : {}", allcompany);

		int currentYear = LocalDate.now().getYear();
		int lastYear = currentYear - 1;

		List<Integer> thisYearSales = pservice.selectMonthlySales(currentYear);
		List<Integer> lastYearSales = pservice.selectMonthlySales(lastYear);

		model.addAttribute("newSubs", newSubscribers.size()); // 신규 구독자
		model.addAttribute("activeSubs", allcompany.size()); // 회원수
		model.addAttribute("churnedSubs", ChurnedSubscribers.size()); // 해지 구독자
		model.addAttribute("leftSubscribers", LeftSubscribers); // 최근 해지

		log.info("getPaymentStatistics", getPaymentStatistics);

		// 전년도 올해 매출
		model.addAttribute("lastYearSales", lastYearSales);
		model.addAttribute("thisYearSales", thisYearSales);

		model.addAttribute("getPaymentStatistics", getPaymentStatistics);

		return "admin/payment/SellingProduct";
	}

//	@GetMapping("/delete/product")
//	public String deleteProduct(
//			@RequestParam String productNo		
//			, Model model
//		) {
//		
//		PaymentProductVO vo = service.selectPaymentProductByPk(productNo);
//		vo.setProductStatus("D");
//		service.updatePaymentProduct(vo);
//		log.info("vo값 : {}" , vo);
//		model.addAttribute("message : {}" ,"삭제가 완료되었습니다");
//		return "redirect:/admin/product/list";
//	}

//	@PostMapping("/update/done")
//	public String productDone(PaymentProductVO productVO
//			, Model model
//			) {
//		log.info("넘어온 상품정보 : {}",productVO.getProductName());
//		productVO.setProductStatus("A");
//		service.updatePaymentProduct(productVO);
//		model.addAttribute("product",productVO);
//		model.addAttribute("message", "상품수정이 완료되었습니다");
//		log.info("model : {}", model);
//		return "admin/payment/UpdateResult";
//	}

//	@GetMapping("/update/product")
//	public String productUpdate(
//		@RequestParam("productNo") String productNo
//		, Model model
//			){
//		PaymentProductVO product = service.selectPaymentProductByPk(productNo);
//		model.addAttribute("product",product);
//		log.info("상품 업데이트 모델 : {}", model);
//		return "admin/payment/UpdateProduct";
//	}

//		if ("정기권".equals(type)) {
//
////			productPeriod = productPeriod.plusDays(30); // 적용됨
////
////			String formattedPeriod = productPeriod.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
////
////			productVO.setProductPeriod(formattedPeriod);
//			productVO.setProductType("L");
//
//		} else if ("단건".equals(type)) {
//
////			productPeriod = productPeriod.plusDays(7);
////
////			String formattedPeriod = productPeriod.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//			productVO.setProductType("S");
////			productVO.setProductPeriod(formattedPeriod);
//
//		}

//		MultipartFile file = productVO.getProductImgFile();
//		if (file != null && !file.isEmpty()) {
//			try {
//				String uploadPath = RealuploadPath;
//				String saveName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//				Path path = Paths.get(uploadPath, saveName);
//				Files.copy(file.getInputStream(), path, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
//				productVO.setProductImg(saveName);
//			} catch (IOException e) {
//				e.printStackTrace();
//				return "fail";
//			}
//		}

//	@GetMapping("/product/list")
//	String listForm(@RequestParam(value = "page", defaultValue = "1") int page,
//			@RequestParam(value = "filterType", required = false) String filterType, Model model) {
//		int pageSize = 10;
//		int offset = (page - 1) * pageSize;
//		List<PaymentProductVO> productList = service.selectPaymentProductList();
//		
//		List<PaymentProductVO> filterList = productList.stream()
//								.filter(p->"A".equals(p.getProductStatus()))
//								.toList();
//	
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
//		return "admin/payment/AdminproductList";
//	}

}
