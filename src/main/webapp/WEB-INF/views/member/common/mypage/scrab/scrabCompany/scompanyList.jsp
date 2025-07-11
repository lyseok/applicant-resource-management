<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<title>띹잡 마이페이지 | 관심기업 리스트</title>

<body>
<%-- scompany인데 srecruit로 넣어버려서 이동시켜야 함 --%>
<div>
	<p class="h4">스크랩 공고/관심기업</p>
	
	<%-- 스크랩(개수)/관심기업(개수) --%>
	<ul>
		<li>
			<a href="/member/common/mypage/scrab/scrabRecruitment/srecruitList">
				스크랩 공고<span>${scompany.member.scrabRecruitment.srecruitList.size() }</span>
			</a>
		</li>
		<li>
			<a href="/member/common/mypage/scrab/scrabCompany/scompanyList">
				관심기업 <span>${scompany.scompanyList.size()}</span>
			</a>
		</li>
	</ul>
	<%-- 목록 관리, 검색 바 --%>
	<div>
		<div>
			<span>
				<input type="checkbox">
				<label for="selectAll">전체선택</label>
			</span>
		</div>
			<button id="scrabListSD">삭제</button>
			<button id="scrabListMV">이동</button>
		<div>
			<select class="user_folder" title="사용자 폴더" onmousedown="">
				<option value selected>전체(${scompany. })</option>
				<option value selected>분류없음(${scompany. })</option>
			</select>
		</div>
		<button id="addSRecruit" type="button" class="btn_folder" onmouseon="">
			폴더 관리			
		</button>
	</div>
	<div>
		<div>
			<select name="status">
				<option value label="전체" selected>전체</option>
				<option value="ing" label="진행중">진행중</option>
				<option value="end" label="마감">마감</option>
			</select>
		</div>
	</div>
	<div>
		<select name="sort" class="sorting">
			<option value="scrabDate" label="스크랩일순" selected>스크랩일순</option>
			<option value="endDate" label="마감임박순" selected>마감임박순</option>
			<option value="startDate" label="등록일순" selected>등록일순</option>
			<option value="updateDate" label="수정일순" selected>수정일순</option>
		</select>
	</div>
	<div>
		<select name="page_count" id="scrabListPC">
			<option value="20" label="20개씩" selected>20개씩</option>
			<option value="50" label="50개씩">50개씩</option>
			<option value="100" label="100개씩">100개씩</option>
		</select>
	</div>
	<div class="InpBox Line filter">
		<span class="Chk">
			<input type="checkbox" value="y">
			<label>지원한 공고 제외</label>
		</span>
	</div>
	<div class="search_area TypoBox">
		<input type="text" class="Typo search" name="keyword" value placeholder="키워드 입력">
		<button type="button" id="keyword_srecruit_search" onmousedown=""></button>
	</div>
	<%-- 공고 목록 --%>
	<div class="activity_list basic">
		<span class="blind">공고 리스트</span>
		<div class="list_recruit">
			<ul class="wrap_list">
				<li class="row" data-idx="">
				<%-- 체크박스 --%>
				<div class="InpBox scrap_check">
					<span class="Chk">
						<input type="checkbox" name="scrap_check[]" class="idx_chk checkbox_idx">
						<label class="Lbi">
							<span class="blind">선택</span>
						</label>
					</span>
				</div>
				<%-- 공고 하나 --%>
				<%-- 공고 회사 이름 --%>
				<div class="col_corp">
					<a href="/member/common/mypage/scrab/scrabRecruitment/srecruitDetail">${srecruit.comName}</a>
				</div>
				<%-- 공고 제목 --%>
				<div class="col_informs">
					<strong class="tit">
						<a href="/member/common/mypage/scrab/scrabRecruitment/srecruitDetail"></a>
					</strong>
					<ul class="inform">
						<li>${srecruit.recruitmentNotice.yearCode}</li>
						<li>${srecruit.recruitmentNotice.education}</li>
						<li>${srecruit.recruitmentNotice.jobCode}</li>
						<li>${srecruit.recruitmentNotice.cityCode} ${srecruit.recruitmentNotice.districtCode}</li>						
					</ul>
				</div>
				<div class="col_btns"> 
					<button class="sri_btn_ml" title="클릭시 입사지원 창이 뜹니다." onclick="">
						<span class="sri_btn_immediately">입사지원</span>
					</button>
					<span class="date">${srecruit.recruitmentNotice.recruitmentFinishDate}</span>
				</div>
				<div class="col_delete">
					<button type="button" class="delete_scrab_list_btn">
						<span class="blind">스크랩 공고 삭제</span>
					</button>
				</div>
				<%-- 공고 스크랩 날짜 --%>
				<button id="memo" type="button" class="btnText txt memo_summary">
					<span class="scrab_date">${srecruit.scrabRecruitmentDate} 스크랩</span>
					공고와 완련된 중요 메모를 남기세요
				</button>
			</li>
		</ul>
	</div>
</div>
	
<!-- 사람인 벤치마킹 끝 -->	
	
</div>
	
	<!-- 관심기업 존재 여부에 따른 분기 -->
	<c:if test="${not empty scompany}">
	<tr>
	    <td>${scompany.userId}</td>
		<td>${scompany.companyId}</td>
		<td>${scompany.scrabCompanyDate}</td>
	</tr>
	</c:if>
	
	<c:if test="${empty scompany}">
	    <td>관심 기업이 없습니다, 등록해보세요!</td>
	</c:if>

</body>
