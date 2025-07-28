package kr.or.ddit.dto;

import java.util.List;

import lombok.Data;


@Data
public class PaginationDTO<T> {
	private int currentPage;     // 현재 페이지
    private int totalRecord;     // 전체 레코드 수
    private int screenSize = 10; // 한 페이지당 게시글 수
    private int blockSize = 5;   // 페이지 블럭 수 (1~5, 6~10)

    private int totalPage;
    private int startRow;
    private int endRow;
    private int startPage;
    private int endPage;

    private List<T> dataList;    // 실제 데이터 리스트

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        this.totalPage = (int) Math.ceil((double) totalRecord / screenSize);
        this.startRow = (currentPage - 1) * screenSize + 1;
        this.endRow = Math.min(currentPage * screenSize, totalRecord);

        this.startPage = ((currentPage - 1) / blockSize) * blockSize + 1;
        this.endPage = Math.min(startPage + blockSize - 1, totalPage);
    }
}
