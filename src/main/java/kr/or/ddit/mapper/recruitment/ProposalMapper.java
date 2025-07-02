package kr.or.ddit.mapper.recruitment;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import kr.or.ddit.vo.recruitment.ProposalVO;

@Mapper
public interface ProposalMapper {
	public List<ProposalVO> selectProposalList();
	public List<ProposalVO> selectProposalUser(String userId);
	public List<ProposalVO> selectProposalNotice(String noticeNo);
	public ProposalVO selectProposal(String userId);
	public int InsertProposal(ProposalVO proposal);
	public int updateProposal(ProposalVO proposal);
	public int deleteProposal(String proposalNo);
}
