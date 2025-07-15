package kr.or.ddit.vo.recruitment;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.common.CompanyVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitmentNo")
public class RecruitmentNoticeVO implements Serializable{

	@NotBlank(groups = UpdateGroup.class)
	private String recruitmentNo;
	private String userId;
	@NotBlank
	private String recruitmentTitle;
	@NotBlank
	private String jobCode;
	private String jobCodeName;
	@NotBlank
	private String yearCode;
	private String yearCodeName;
	@NotBlank
	private String recContent;
	private String preferential;
	@NotBlank
	private String cityCode;
	private String cityCodeName;
	@NotBlank
	private String districtCode;
	private String districtCodeName;
	private String recPositionNumber;
	@NotBlank
	private String recruitmentChargerTel;
	@NotBlank
	private String recruitmentSalary;
	private String welfare;
	@NotBlank
	private String recruitmentDesk;
	private String recruitmentImg;
	private String recruitmentStartdate;
	@NotBlank
	private String recruitmentReceiptStart;
	@NotBlank
	private String recruitmentFinishDate;
	private String recruitmentDelDate;
	
	
	@Valid
	private List<RecruitmentPositionVO> positionList;
	@Valid
	private RecruitmentEducationVO education;
	@Valid
	private List<RecruitmentSkillVO> skillList;
	@Valid
	private List<RecruitProcessVO> processList;
	private CompanyVO company;
}
