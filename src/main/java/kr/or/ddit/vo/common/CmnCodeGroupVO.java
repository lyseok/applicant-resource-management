package kr.or.ddit.vo.common;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="codeGroupNo")
public class CmnCodeGroupVO implements Serializable {
	@NotBlank
	private String codeGroupNo;
	@NotBlank
	private String codeGroupName;
	private String description;
	@NotBlank
	private String useYn;
	private String crateDate;
	private String updateDate;

	private List<CmnCodeVO> cmnCodeList;
}
