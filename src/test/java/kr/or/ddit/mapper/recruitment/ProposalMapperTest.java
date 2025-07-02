package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.ProposalVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ProposalMapperTest {

	@Autowired
	ProposalMapper mapper;
	
	@Test
	void testSelectProposalList() {
		mapper.selectProposalList().forEach(propo ->{
			log.info("{}", propo);
		});
	}
	@Test
	void testSelectProposalUser() {
		mapper.selectProposalUser("USER001").forEach(propo ->{
			log.info("{}", propo);
		});
	}

	@Test
	void testSelectProposal() {
		ProposalVO vo =	mapper.selectProposal("USER002");
		log.info("{}", vo);
	}

	@Test
	void testInsertProposal() {
	    ProposalVO proposal = new ProposalVO();
        proposal.setUserId("testUser1");
        proposal.setProposalName("테스트 제안1");
        proposal.setProposalContent("이것은 테스트를 위한 제안 내용입니다.");
        proposal.setRecruitmentNoticeNo("9999");
        
        mapper.InsertProposal(proposal);
        
        ProposalVO vo =	mapper.selectProposal("9999");
		log.info("{}", vo);
        
	}

	@Test
	void testUpdateProposal() {
		 ProposalVO proposal = new ProposalVO();
	        proposal.setUserId("testUser2");
	        proposal.setProposalName("테스트 수정1");
	        proposal.setProposalContent("이것은 테스트를 위한 제안 내용입니다.1");
	        proposal.setRecruitmentNoticeNo("9990");
	        
	        mapper.updateProposal(proposal);
	        
	        ProposalVO vo =	mapper.selectProposal("9990");
			log.info("{}", vo);
	}

	@Test
	void testDeleteProposal() {
		mapper.deleteProposal("PR002");
		assertNull(mapper.selectProposal("USR001"));
	}

}
