package kr.or.ddit.company.common.companyManagement.service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		
//		List<FilesVO> files = fileService.getFilesBySource(userId);
//	    company.setFileList(files);
//	    if (!files.isEmpty()) {
//	        // 첫 번째 로고 URL을 편의 프로퍼티로 꺼내주고 싶다면
//	        company.setComLogo(files.get(0).getFilePath());
//	    }
		return company;
	}

	@Transactional
	@Override
	public int editCompanyInfo(CompanyInfoDTO companyInfoDTO) {
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
	   
	    int updateCount = companyMapper.updateCompanyInfoById(companyVO);
	    
	    if(companyInfoDTO.getFileList() != null && !companyInfoDTO.getFileList().isEmpty()) {
	    	List<String> filePaths = companyInfoDTO.getFileList().stream()
	    		.map(FilesVO::getFilePath)
	    		.collect(Collectors.toList());
	    	  log.info("📎 파일 경로 목록 = {}", filePaths);
	          log.info("🔧 파일 업데이트 호출: sourceNo = {}, filePaths = {}", comId, filePaths);
	    	fileService.updateFilesWithOrder(comId,filePaths);
	    }
	    return updateCount;
	}
	

	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}
	
	

	
}
