package kr.or.ddit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkHistoryDTO {
    
    private String workHistNo;      // 작업내역번호
    private String prjNo;           // 프로젝트번호
    private String prjName;         // 프로젝트명 (조인)
    private String userId;          // 사용자ID
    private String userName;        // 사용자명 (조인)
    private LocalDateTime workDate; // 작업일시
    private String workTable;       // 작업테이블
    private String workType;        // 작업타입
    private String workTarget;      // 작업대상
    private String workContent;     // 작업내용
}
