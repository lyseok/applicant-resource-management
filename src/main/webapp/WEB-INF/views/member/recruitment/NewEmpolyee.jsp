<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신입 초봉관련 페이지</title>
	<style>
        .recruitment-card {
            border: 1px solid #ddd;
            padding: 15px;
            margin: 10px 0;
            border-radius: 10px;
            cursor: pointer;
        }
        .recruitment-card:hover {
            background-color: #f9f9f9;
        }
        .recruitment-title {
            font-size: 18px;
            font-weight: bold;
        }
        .recruitment-meta {
            font-size: 14px;
            color: #666;
        }
         .salary-badge {
        background-color: #ffe6e6;
        color: #d60000;
        font-weight: bold;
        padding: 8px 12px;
        border-radius: 15px;
        white-space: nowrap;
        font-size: 14px;
    }
    </style>
<script>
	function goToDetail(recruitmentNo) {
    	location.href = '/member/recruitment/detail?no=' + recruitmentNo;
	}
</script>
</head>
<body>
<h2>채용공고 리스트</h2>
<c:if test="${not empty salaryList}">
	<c:forEach items="${salaryList }" var="salaryList">
		<div class="recruitment-card" onclick="goToDetail('${salaryList.recruitmentNo}')">
		<div class="recruitment-title">
			${salaryList.recruitmentTitle}
		</div>
			<div class="recruitment-meta">
			기업: ${salaryList.userId} |
                직무: ${salaryList.jobCode} |
                지역: ${salaryList.cityCode} |
                복지: ${salaryList.welfare}
			</div>
	<div class="salary-badge">
        연봉: ${salaryList.recruitmentSalary}
    </div>
		</div>
	</c:forEach>
</c:if>

<c:if test="${empty salaryList }">
	<p>채용공고가 없습니다.</p>
</c:if>

</body>
</html>