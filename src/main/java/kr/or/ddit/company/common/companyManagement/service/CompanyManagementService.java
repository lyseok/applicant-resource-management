package kr.or.ddit.company.common.companyManagement.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.Multipart;
import kr.or.ddit.dto.CompanyInfoDTO;
import kr.or.ddit.vo.common.CompanyVO;

public interface CompanyManagementService {
	public CompanyVO readCompanyManagementById(String userId);
	public int editCompanyInfo(CompanyInfoDTO companyInfoDTO
								,MultipartFile logoFile
								,MultipartFile backFile
								,List<MultipartFile> extraFiles);

	
}
