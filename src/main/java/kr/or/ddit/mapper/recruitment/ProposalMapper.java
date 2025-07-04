package kr.or.ddit.mapper.recruitment;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import kr.or.ddit.vo.recruitment.ProposalVO;

@Mapper
public interface ProposalMapper {
	public List<ProposalVO> selectProposalList();
	public ProposalVO selectProposalByPk(String proposalCode);
	public int InsertProposal(ProposalVO proposal);
	public int updateProposal(ProposalVO proposal);
	public int deleteProposal(String proposalCode);
	
}
