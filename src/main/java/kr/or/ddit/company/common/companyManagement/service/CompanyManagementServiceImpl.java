package kr.or.ddit.company.common.companyManagement.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.Multipart;
import kr.or.ddit.common.file.S3Uploader;
import kr.or.ddit.common.file.service.FileService;
import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.dto.CompanyInfoDTO;
import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.common.InduCodeMapper;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.FilesVO;
import kr.or.ddit.vo.common.InduCodeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CompanyManagementServiceImpl implements CompanyManagementService {
	
	private final CompanyMapper companyMapper;
	private final CodeMapProvider provider;
	private final FileService fileService;
	private final S3Uploader s3Uploader;
	
	@Override
	public CompanyVO readCompanyManagementById(String userId) {
		CompanyVO company = companyMapper.selectCompanyInfoById(userId);
		String no = company.getIndustryType();
		String name = provider.getInduName(no);
		company.setIndustryType(name);
	
		String typeName = provider.getCodeName(company.getComType());
		String sizeName = provider.getCodeName(company.getComSize());
		company.setComType(typeName);
		company.setComSize(sizeName);
		return company;
	}

	@Transactional
	@Override
	public int editCompanyInfo(CompanyInfoDTO companyInfoDTO , MultipartFile logoFile, MultipartFile backgroundFile, List<MultipartFile> extraFiles) {
		String comId = getUserId();
		log.info("👤 [회사정보 수정] 요청 userId = {}", comId);
		log.info("📂 fileList = {}", companyInfoDTO.getFileList());

		CompanyVO companyVO = new CompanyVO();
		companyVO.setUserId(comId);
		companyVO.setComInfo(companyInfoDTO.getComInfo());
	    companyVO.setComNum(companyInfoDTO.getComNum());
	    companyVO.setComEmail(companyInfoDTO.getComEmail());
	    companyVO.setComUrl(companyInfoDTO.getComUrl());
	    companyVO.setComMem(companyInfoDTO.getComMem());
	    companyVO.setInsuranceYn(companyInfoDTO.getInsuranceYn());
	    companyVO.setIndustryType(companyInfoDTO.getIndustryType());
	    companyVO.setComType(companyInfoDTO.getComType());
	    companyVO.setComSize(companyInfoDTO.getComSize());
	    companyVO.setCeoName(companyInfoDTO.getCeoName());
	    companyVO.setComAddr(companyInfoDTO.getComAddr());
	    companyVO.setComMainBiz(companyInfoDTO.getComMainBiz());
	    companyVO.setComCapital(companyInfoDTO.getComCapital());
	    
	    companyVO.setComLogo(companyInfoDTO.getComLogo());
	    companyVO.setComBackgroundImg(companyInfoDTO.getComBackgroundImg());
	    
	    if(logoFile != null && !logoFile.isEmpty()) {
	    
			try {
				String logoUrl = s3Uploader.upload(logoFile);
				log.info("로고 -----{}", logoUrl);
				companyVO.setComLogo(logoUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    if(backgroundFile != null && !backgroundFile.isEmpty()) {
	    	String backgroundUrl;
			try {
				backgroundUrl = s3Uploader.upload(backgroundFile);
				log.info("배경 -----{}", backgroundUrl);
				companyVO.setComBackgroundImg(backgroundUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    int updateCount = companyMapper.updateCompanyInfoById(companyVO);
	    
	   if(extraFiles != null && !extraFiles.isEmpty()) {
		   List<String> urls = new ArrayList<>();
		   for (MultipartFile file : extraFiles) {
			   if(file != null && !file.isEmpty()) {
				   try {
					String url = s3Uploader.upload(file);
					urls.add(url);
					fileService.saveUploadFile(file, url, 827);
					} catch (IOException e) {
						e.printStackTrace();
					}
			   }
		   }
		   fileService.updateFilesWithOrder(comId, urls);
	   }
	    return updateCount;
	}

	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}

	
}
