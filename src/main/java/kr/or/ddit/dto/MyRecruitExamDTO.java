package kr.or.ddit.dto;

import lombok.Data;

@Data
public class MyRecruitExamDTO {
	private String recruitExamNo;
    private String recruitExamName;
    private String recruitExamStartDate;
    private Integer recruitExamTime;
    private String applicantId;
    private boolean taken; 
}
