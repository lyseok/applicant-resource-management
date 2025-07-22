package kr.or.ddit.company.payment.product.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.company.payment.product.service.PaymentProductService;
import kr.or.ddit.vo.common.PaymentProductVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminProductController {

//	@Autowired
//	private FileUploadService fileUploadService;
	
	@Autowired
	PaymentProductService service;
	
	@GetMapping("/delete/product")
	public String deleteProduct(
			@RequestParam String productNo		
			, Model model
		) {
		
		PaymentProductVO vo = service.selectPaymentProductByPk(productNo);
		vo.setProductStatus("D");
		service.updatePaymentProduct(vo);
		log.info("vo값 : {}" , vo);
		model.addAttribute("message : {}" ,"삭제가 완료되었습니다");
		return "redirect:/admin/product/list";
	}
	
	@PostMapping("/update/done")
	public String productDone(PaymentProductVO productVO
			, Model model
			) {
//		if(!productVO.getProductImgFile().isEmpty()) {
//			String uploadedFileName = fileUploadService.saveFile(productVO.getProductImgFile());
//			productVO.setProductImg(uploadedFileName);
//		}
		log.info("넘어온 상품정보 : {}",productVO.getProductName());
		productVO.setProductStatus("A");
		service.updatePaymentProduct(productVO);
		model.addAttribute("product",productVO);
		model.addAttribute("message", "상품수정이 완료되었습니다");
		log.info("model : {}", model);
		return "admin/payment/UpdateResult";
	}
	
	@GetMapping("/update/product")
	public String productUpdate(
		@RequestParam("productNo") String productNo
		, Model model
			){
		PaymentProductVO product = service.selectPaymentProductByPk(productNo);
		model.addAttribute("product",product);
		log.info("상품 업데이트 모델 : {}", model);
		return "admin/payment/UpdateProduct";
	}
	
	
	
	@GetMapping("/detail")
	public String productDetail(
			@RequestParam("productNo") 
			String productNo
			,Model model
			) {
		PaymentProductVO product = service.selectPaymentProductByPk(productNo);
		model.addAttribute("product", product);
		return "admin/payment/AdminProductDetail";
		
	}
	
	@PostMapping("/insert")
	public String insertProduct(@ModelAttribute PaymentProductVO productVO, Model model) {

		String type = productVO.getProductType();

		LocalDate productPeriod = LocalDate.now();

		if ("정기권".equals(type)) {

			productPeriod = productPeriod.plusDays(30); // 적용됨

			String formattedPeriod = productPeriod.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

			productVO.setProductPeriod(formattedPeriod);
			productVO.setProductType("L");

		} else if ("단건".equals(type)) {

			productPeriod = productPeriod.plusDays(7);

			String formattedPeriod = productPeriod.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
			productVO.setProductType("S");
			productVO.setProductPeriod(formattedPeriod);

		}

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

		log.info("등록된 상품 {} ", productVO);
		model.addAttribute("productVO", productVO);
		service.insertPaymentProduct(productVO);
		return "company/payment/product/ResultProductInsert";
	}
	
	@GetMapping("/product/list")
	String listForm(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "filterType", required = false) String filterType, Model model) {
		int pageSize = 10;
		int offset = (page - 1) * pageSize;
		List<PaymentProductVO> productList = service.selectPaymentProductList();
		
		List<PaymentProductVO> filterList = productList.stream()
								.filter(p->"A".equals(p.getProductStatus()))
								.toList();
	
		if("S".equals(filterType)) {
			filterList = productList.stream()
					.filter(p->"S".equalsIgnoreCase(p.getProductType()))
					.toList();
		} else if ("L".equalsIgnoreCase(filterType)) {
		    filterList = productList.stream()
		            .filter(p -> "L".equalsIgnoreCase(p.getProductType()))
		            .toList();
		    }
		
		int totalItems = filterList.size();
		int totalPages = (int)Math.ceil((double) totalItems/pageSize);
		List<PaymentProductVO> pageList = filterList.stream()
										.skip(offset)
										.limit(pageSize)
										.toList();
		
		
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("productList", pageList);
		
		return "admin/payment/AdminproductList";
	}
	
	@GetMapping("/add")
	String addForm() {
		return "admin/payment/InsertProduct";
	}
}
