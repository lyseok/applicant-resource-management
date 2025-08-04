package kr.or.ddit.dto;

import java.util.List;

import lombok.Data;

@Data
public class CompanyReviewStatsDTO {
	private int totalReviewCount;      
    private int reviewUserCount;           
    private double overallAvg;            
    private List<QuestionAvgDTO> questionAvgList;  
    private List<TopJobStatsDTO> topJobStatsList;

    @Data
    public static class QuestionAvgDTO {
    	private String topJobCode; 
        private String reviewSubjectCode;
        private String reviewSubjectName;
        private double avgScore;
    }

    @Data
    public static class TopJobStatsDTO {
        private String topJobCode;
        private String topJobName;
        private double topJobOverallAvg;           
        private List<QuestionAvgDTO> questionAvgList; 
    }
	
	
}
