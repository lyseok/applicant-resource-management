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
	void testSelectProposalByPk() {
		ProposalVO vo =	mapper.selectProposalByPk("");
		log.info("{}", vo);
	}

	@Test
	void testInsertProposal() {
	    ProposalVO proposal = new ProposalVO();
        proposal.setUserId("USR001");
        proposal.setProposalName("테스트 제안1");
        proposal.setProposalContent("이것은 테스트를 위한 제안 내용입니다.");
        proposal.setRecruitmentNoticeNo("RECR000001");
   
        
        mapper.InsertProposal(proposal);
        log.info("{}", proposal);
        
        
        
	}

	@Test
	void testUpdateProposal() {
		 ProposalVO proposal = new ProposalVO();
	        proposal.setUserId("USR001");
	        proposal.setProposalCode("PROP000001");
	        proposal.setProposalName("테스트 수정2");
	        proposal.setProposalContent("이것은 테스트를 위한 제안 내용입니다.");
	        proposal.setRecruitmentNoticeNo("RECR000001");
	        
	        mapper.updateProposal(proposal);
	        
	        ProposalVO vo =	mapper.selectProposalByPk("PROP000001");
			log.info("{}", vo);
	}

	@Test
	void testDeleteProposal() {
		mapper.deleteProposal("PROP000001");
		assertNull(mapper.selectProposalByPk("PROP000001"));
	}

}
