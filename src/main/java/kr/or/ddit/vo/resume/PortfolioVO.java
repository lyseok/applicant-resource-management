package kr.or.ddit.vo.resume;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "porCode")
public class PortfolioVO implements Serializable{

	private String porCode;
	private String resumeNo;
	private String porFileOriginalName;
	private String porFileSaveName;
	private String porFileSize;
	private String porFilePath;

}
