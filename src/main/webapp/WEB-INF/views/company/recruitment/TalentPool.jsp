<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인재풀 목록</title>
<style>
/* 여기에는 이전 답변에서 제공했던 CSS 코드를 그대로 붙여넣습니다.
    (길어서 생략했지만, 실제 코드에는 꼭 포함되어야 합니다.)
*/
body { font-family: 'Noto Sans KR', sans-serif; margin: 20px; background-color: #f5f6f7; }
.user-card { 
    display: flex; align-items: flex-start; background-color: #ffffff; border-radius: 8px; padding: 20px; 
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.08); margin-bottom: 20px; gap: 20px; position: relative; 
    max-width: 900px; margin-left: auto; margin-right: auto;
}
.user-avatar { width: 60px; height: 60px; background-color: #e0e0e0; border-radius: 50%; display: flex; justify-content: center; align-items: center; flex-shrink: 0; }
.user-avatar::before { content: '👤'; font-size: 30px; color: #666; }
.user-info { flex-grow: 1; }
.user-meta { display: flex; align-items: center; font-size: 16px; color: #333; margin-bottom: 8px; }
.user-name { font-weight: bold; margin-right: 8px; }
.user-age, .user-experience { color: #666; margin-right: 8px; }
.new-badge { background-color: #ff5722; color: #ffffff; font-size: 12px; padding: 2px 6px; border-radius: 4px; font-weight: bold; margin-left: 4px; white-space: nowrap; }
.user-position-salary { font-size: 15px; color: #444; margin-bottom: 4px; }
.position-name { font-weight: 600; }
.working-period { color: #777; font-size: 14px; }
.user-description { font-size: 14px; color: #555; line-height: 1.5; margin-bottom: 8px; }
.user-career-path, .user-education { font-size: 14px; color: #666; margin-bottom: 4px; }
.company-team { font-weight: 500; }
.user-skills { display: flex; flex-wrap: wrap; gap: 8px; margin-top: 10px; }
.skill-tag { background-color: #f0f2f5; color: #555; font-size: 13px; padding: 6px 10px; border-radius: 6px; white-space: nowrap; }
.action-buttons { display: flex; flex-direction: column; gap: 10px; flex-shrink: 0; }
.save-button, .reject-button { padding: 10px 15px; border: 1px solid; border-radius: 6px; font-weight: bold; cursor: pointer; white-space: nowrap; min-width: 120px; text-align: center; }
.save-button { background-color: #4285f4; color: #ffffff; border-color: #4285f4; }
.save-button:hover { background-color: #3367d6; }
.reject-button { background-color: #ffffff; color: #777; border-color: #ccc; }
.reject-button:hover { background-color: #f0f0f0; }
</style>
<script>
	function goToDetail(memName) {
    	location.href = '/member/recruitment/detail?no=' + memName;
	}
</script>
</head>
<body>

<h1>인재풀 검색 결과</h1> 

<c:if test="${not empty talentpoolList}">
    <c:forEach  items="${talentpoolList}" var="talent">
        <div class="user-card">
            <div class="user-avatar">
                </div>
               <c:url value="/TalentPool/detail" var="detailURL">
               	<c:param name="what" value="${talent.userName}" />
               </c:url>
            <div class="user-info" onclick="location.href='${detailURL }'">
                <div class="user-meta">
                    <span class="user-name">${talent.userName }</span>
                    <%-- <span class="user-age">${talent.gender} ${talent.age}세</span>  --%>
                    <span class="user-experience">경력 ${talent.careerYear}년 
                        <c:if test="${talent.careerYear}">
                            <span class="new-badge">New</span>
                        </c:if>
                    </span>
                </div>
                <div class="user-position-salary">
                    <span class="position-name">${talent.jobCode}</span>
                    <span class="working-period">
                        <c:if test="${not empty talent.startWorkDate}">
                            (${talent.startWorkDate} 
                            <c:if test="${not empty talent.retireDate}">- ${talent.retireDate}</c:if>
                            <c:if test="${empty talent.retireDate}">- 재직중</c:if>
                            )
                        </c:if>
                        <c:if test="${not empty talent.salary}">
                            , 연봉 ${talent.salary}
                        </c:if>
                    </span>
                </div>
                <div class="user-description">
                    ${talent.responsibility}
                </div>
                
                <%--
                <div class="user-career-path">
                    <c:forEach var="prevCareer" items="${talent.previousCareers}">
                        <span class="company-team">${prevCareer.companyName} ${prevCareer.teamName}</span> (${prevCareer.duration})
                    </c:forEach>
                </div>
                --%>
                <div class="user-career-path">
                    <span class="company-team">${talent.jobCode}</span> (1년 2개월)
                    <span class="company-team">${talent.jobCode}</span> (2년)
                </div>

                <%--
                <div class="user-education">
                    ${talent.educationInfo}
                </div>
                --%>
                <div class="user-education">
                    서울대학교 컴퓨터 공학과 (졸업)
                </div>

                <div class="user-skills">
                    <%--
                    <c:forEach var="skill" items="${talent.skillTags}">
                        <span class="skill-tag">${skill}</span>
                    </c:forEach>
                   ` --%>
                   <%-- <c:forEach begin="1" end="${SkillList.size() -1}">
                   		#{talent.mySkilName }
                   </c:forEach> --%>
                </div>
            </div>
            <div class="action-buttons">
                <button class="save-button">후보자 저장</button>
                <button class="reject-button">이직제안 하기</button>
            </div>
        </div>
    </c:forEach>
</c:if>

<c:if test="${empty talentpoolList}">
    <p style="text-align: center; margin-top: 50px; color: #777;">검색 결과가 없습니다.</p>
</c:if>

</body>
</html>