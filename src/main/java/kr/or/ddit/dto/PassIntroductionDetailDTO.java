package kr.or.ddit.dto;

import java.io.Serializable;
import java.util.List;

import kr.or.ddit.mapper.recruitment.PasserMapper;
import kr.or.ddit.vo.recruitment.PasserVO;
import kr.or.ddit.vo.resume.EducationVO;
import kr.or.ddit.vo.resume.IntroductionVO;
import lombok.Data;

@Data
public class PassIntroductionDetailDTO implements Serializable{

    private String passerCode;
    private String passIntrodocutionNo;
  
    private String recruitmentNo;
    private String recruitmentTitle;
    private String jobCode;
    private String jobCodeName;
    private String compName;
    private String comId;
    
    private String resumeNo;
    private String resumeName;

    private List<EducationVO> education;
    private IntroductionVO introduction;
    
}
