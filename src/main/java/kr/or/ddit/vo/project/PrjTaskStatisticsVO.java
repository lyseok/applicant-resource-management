package kr.or.ddit.vo.project;

import java.io.Serializable;

import lombok.Data;

@Data
public class PrjTaskStatisticsVO implements Serializable{
    private int total;         // 전체 작업 수
    private int completed;     // 완료 (PEND-003)
    private int inProgress;    // 진행중 (PEND-002)
    private int todo;          // 할 일 (PEND-001)
    private int overdue;       // 마감일 초과(미완료)
    private double completionRate; // 완료율 (0~100)
}
