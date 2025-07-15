package kr.or.ddit.vo.project;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of={"rcrtPsncntNo", "prjAnncNo"})
public class PrjRcrtPsncntVO implements Serializable {
	private String rcrtPsncntNo;
	private String prjAnncNo;
	@NotBlank
	private String jobCode;
	@Min(value = 1, message = "1보다 작을 수 없습니다.")
	@Max(value = 5, message = "5보다 클 수 없습니다.")
	private Integer rcrtPsncnt;
	private String jobCodeName;
	
	private List<PrjAplcntVO> aplcntList;
}
