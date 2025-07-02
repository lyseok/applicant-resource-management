package kr.or.ddit.mapper.recruitment;

import java.util.List;

import kr.or.ddit.vo.recruitment.ProposalVO;

public interface ProposalMapper {
	public List<ProposalVO> selectProposalList();
	public ProposalVO selectProposal();
}
