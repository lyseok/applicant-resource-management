package kr.or.ddit.vo.project;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="prjAnncNo")
public class PrjAnncBbsVO implements Serializable {
	private String prjAnncNo;
	private String userId;
	@NotBlank
	private String prjEmpTitle;
	@NotBlank
	private String prjTopic;
	private String anncCreateDate;
	@NotBlank
	private String prjStartPlanDate;
	@NotBlank
	private String prjEndPlanDate;
	private Integer prjAnncHit;
	private String prjAnncContent;
	private String anncEndPlanDate;
	private String anncEndYn;
	
	private List<PrjAnncBoardTagVO> prjAnncBoardTagList;
	@Valid
	private List<PrjRcrtPsncntVO> prjRcrtPsncntList;
}
