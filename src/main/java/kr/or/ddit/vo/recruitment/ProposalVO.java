package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "proposalCode")
public class ProposalVO implements Serializable{

	private String proposalCode;
	private String userId;
	private String proposalName;
	private String proposalContent;
	private String recruitmentNoticeNo;
	private String proposalDelDate;
}
