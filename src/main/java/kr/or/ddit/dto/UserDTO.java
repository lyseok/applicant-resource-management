package kr.or.ddit.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserDTO implements Serializable {
	private String userId;
    private String userRole;
    private String userType;
    private String userName;
    private String userEmail;
    private String userPhone;
    private boolean canAccessAllProjects;
    private List<ProjectDTO> accessibleProjects;
}
