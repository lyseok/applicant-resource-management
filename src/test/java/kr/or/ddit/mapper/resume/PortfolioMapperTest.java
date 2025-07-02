package kr.or.ddit.mapper.resume;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.resume.PortfolioVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
class PortfolioMapperTest {
	@Autowired
	PortfolioMapper mapper;

	@Test
	void testSeletPortfolioList() {
		mapper.seletPortfolioList().forEach(port->{
			log.info("{}", port);
		});
	}

	@Test
	void testSeletPortfolioDetail() {
		PortfolioVO vo = mapper.seletPortfolioDetail("POR001");
		log.info("{}", vo);
	}

	@Test
	void testInsertPortfolio() {
		PortfolioVO vo = new PortfolioVO();
		vo.setResumeNo("RSM001");
		vo.setPorFileOriginalName("originName.png");
		vo.setPorFileSaveName("saveFileName");
		vo.setPorFileSize("567392");
		vo.setPorFilePath("/uploads/merong/hihi");
		mapper.insertPortfolio(vo);
		mapper.seletPortfolioDetail(vo.getPorCode());
		log.info("{}", vo);
	}

	@Test
	void testUpdatePortfolio() {
		PortfolioVO vo = new PortfolioVO();
		vo.setPorCode("POR0000004");
		vo.setResumeNo("RSM001");
		vo.setPorFileOriginalName("바꿀거임.png");
		vo.setPorFileSaveName("저장파일명이지롱");
		vo.setPorFileSize("567392");
		vo.setPorFilePath("/uploads/merong/hihi");
		mapper.updatePortfolio(vo);
		mapper.seletPortfolioDetail(vo.getPorCode());
		log.info("{}", vo);
	}

	@Test
	void testDeletePortfolio() {
		mapper.deletePortfolio("POR0000004");
		PortfolioVO vo = mapper.seletPortfolioDetail("POR0000004");
		log.info("{}", vo);
	}

}
