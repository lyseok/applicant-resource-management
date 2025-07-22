package kr.or.ddit.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProjectDTO implements Serializable {
    private String projectNo;
    private String projectName;
    private String authorityCode;
    private String userPosition;
}
