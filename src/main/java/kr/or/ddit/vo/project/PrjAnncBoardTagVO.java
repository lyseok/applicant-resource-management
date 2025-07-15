package kr.or.ddit.vo.project;

import java.io.Serializable;

import kr.or.ddit.vo.common.TagVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of={"tagNo", "prjAnncNo"})
public class PrjAnncBoardTagVO implements Serializable {
	private String tagNo;
	private String prjAnncNo;
	private String tagDate;
	
	private TagVO tag;
}
