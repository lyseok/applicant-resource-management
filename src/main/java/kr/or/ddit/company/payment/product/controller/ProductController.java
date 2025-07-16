package kr.or.ddit.company.payment.product.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.company.payment.product.service.paymentProductService;
import kr.or.ddit.member.common.mypage.scrab.scrabCompany.service.MemberScrabCompanyService;
import kr.or.ddit.vo.common.PaymentProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/company/payment/product")
public class ProductController {

	@Autowired
	paymentProductService service;

	@Value("${file.upload-dir")
	private String RealuploadPath;

	@PostMapping
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

		MultipartFile file = productVO.getProductImgFile();
		if (file != null && !file.isEmpty()) {
			try {
				String uploadPath = RealuploadPath;
				String saveName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
				Path path = Paths.get(uploadPath, saveName);
				Files.copy(file.getInputStream(), path, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
				productVO.setProductImg(saveName);
			} catch (IOException e) {
				e.printStackTrace();
				return "fail";
			}
		}

		log.info("등록된 상품 {} ", productVO);
		model.addAttribute("productVO", productVO);
		service.insertPaymentProduct(productVO);
		return "company/payment/product/ResultProductInsert";
	}

	@GetMapping("/add")
	String addForm() {
		return "company/payment/product/InsertProduct";
	}

	@GetMapping("/list")
	String listForm(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "filterType", required = false) String filterType, Model model) {
		int pageSize = 10;
		int offset = (page - 1) * pageSize;
		List<PaymentProductVO> productList = service.selectPaymentProductList();

		List<PaymentProductVO> filterList = productList;
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
		
		return "company/payment/product/ProductList";
	}
}
