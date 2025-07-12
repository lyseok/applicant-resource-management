<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>실시간 채용공고</title>
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
    </style>
    <script>
        function goToDetail(recruitmentNo) {
            location.href = '/company/recruit_notice/' + recruitmentNo;
        }
    </script>
</head>
<body>

<h2>실시간 채용공고</h2>

<c:if test="${not empty recruitmentList}">
    <c:forEach items="${recruitmentList}" var="recruitment">
        <div class="recruitment-card" onclick="goToDetail('${recruitment.recruitmentNo}')">
            <div class="recruitment-title">
                ${recruitment.recruitmentTitle}
            </div>
            <div class="recruitment-meta">
                기업: ${recruitment.userId} |
                직무: ${recruitment.jobCode} |
                지역: ${recruitment.cityCode} |
                복지: ${recruitment.welfare}
            </div>
        </div>
    </c:forEach>
</c:if>

<c:if test="${empty recruitmentList}">
    <p>채용공고가 없습니다.</p>
</c:if>

</body>
</html>
