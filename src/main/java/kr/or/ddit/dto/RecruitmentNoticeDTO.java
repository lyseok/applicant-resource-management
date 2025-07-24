package kr.or.ddit.dto;

import java.util.List;

import lombok.Data;

@Data
public class RecruitmentNoticeDTO {
    private String recruitmentNo;
    private String recruitmentTitle;
    private String userId;
    private String comName; 
    private int viewCnt;
    private int scrabCnt;

    private String jobCode;
    private String jobName; 

    private String yearCode;
    private String yearCodeName;

    private String preferential;
    private String cityCode;
    private String cityName;
    private String districtCode;
    private String districtName;

    private String recruitmentSalary;
    private String recruitmentImg;
    private String recruitmentStartDate;
    private String recruitmentFinishDate;
    private String recruitFinishYn;
    private String recContent;

    private List<RecruitmentPositionDTO> positionList;
    private RecruitmentEducationDTO education;  
    private List<RecruitmentSkillDTO> skillList;
}
