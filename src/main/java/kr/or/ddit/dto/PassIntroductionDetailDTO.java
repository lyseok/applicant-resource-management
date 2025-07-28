package kr.or.ddit.dto;

import java.io.Serializable;
import java.util.List;

import kr.or.ddit.vo.resume.EducationVO;
import kr.or.ddit.vo.resume.IntroductionVO;
import lombok.Data;

@Data
public class PassIntroductionDetailDTO implements Serializable{

    private String passerCode;
    private String passIntroductionNo;
    private String introductionCode;  
  
    private String recruitmentNo;
    private String recruitmentTitle;
    private String jobCode;
    private String jobCodeName;
    private String compName;
    private String comId;
    
    private String resumeNo;
    private String resumeName;

    private EducationVO education;
    private IntroductionVO introduction;
    
}