<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<head>
		<title>프로젝트 공고 등록</title>
		<meta charset="UTF-8">
		<!-- Toast UI Editor CDN -->
		<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
		<script defer src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
		<script defer src="/js/member/project/announcement/announcementForm.js"></script>
		<style>
		.form-label{
			font-size:14px;
		}
			.form-control,
			.form-select{
				font-size:14px;
			}
			
			#tagInput:focus {
				outline: none;
				box-shadow: none;
			}

			#jobSearchResult {
				z-index: 1100;
			}

			.position-row {
				background: #f7f7f7;
				border-radius: 18px;
			}

			.position-row .form-select {
				min-width: 90px;
				max-width: 110px;
			}

			.position-row .delete-role {
				color: #888;
				background: none;
				border: none;
				font-size: 1.3em;
				margin-left: 10px;
				cursor: pointer;
			}

			.position-row+.position-row {
				margin-top: 12px;
			}
		</style>
	</head>

	<body>
		<div class="container">
			<h2 class="h1 mb-4 fw-bold">프로젝트 공고 등록</h2>
			<form id="projectForm">

				<!-- 프로젝트 제목 -->
				<div class="mb-3">
					<label class="form-label fw-bold">제목</label>
					<input type="text" class="form-control" name="prjEmpTitle" placeholder="프로젝트 제목을 입력하세요" maxlength="60"
						>
				</div>

				<!-- 태그 입력 -->
				<div class="mb-3">
					<label class="form-label fw-bold">기술 태그</label>
					<div id="tagInputBox" class="rounded d-flex flex-wrap align-items-center" style="min-height:48px;">
						<input type="text" class="form-control flex-grow-1 mb-2" id="tagInput" placeholder="태그를 입력 후 엔터">
						<div class="tagInputWrap"></div>
					</div>
				</div>

				<!-- 모집 팀원 -->
				<div class="mb-3">
					<label class="form-label fw-bold mb-2">모집 팀원</label>
					<div class="d-flex gap-2 mb-2">
						<input type="text" class="form-control" id="jobSearchInput" autocomplete="off" placeholder="직무를 검색하세요"
							style="max-width:240px;">
						<select id="roleCountSelect" class="form-select" style="max-width:110px;">
							<option value="1">1명</option>
							<option value="2">2명</option>
							<option value="3">3명</option>
							<option value="4">4명</option>
							<option value="5">5명</option>
						</select>
						<button type="button" class="btn btn_violet_line fw-500" id="addRoleBtn">추가</button>
					</div>
					<ul id="roleList" class="list-unstyled mb-0"></ul>
					<ul id="jobSearchResult" class="list-group position-absolute w-100 shadow"
						style="display:none; max-width:240px; z-index:2000;"></ul>
				</div>

				<!-- 프로젝트 주제 -->
				<div class="mb-3">
					<label class="form-label fw-bold">프로젝트 주제</label>
					<input type="text" class="form-control" name="prjTopic" placeholder="주제를 입력하세요" maxlength="100">
				</div>

				<!-- 기간 -->
				<div class="row g-4 mb-3">
					<div class="col-md-6">
						<label class="form-label fw-bold">시작 예정일</label>
						<input type="date" class="form-control" name="prjStartPlanDate">
					</div>
					<div class="col-md-6">
						<label class="form-label fw-bold">마감 예정일</label>
						<input type="date" class="form-control" name="prjEndPlanDate">
					</div>
				</div>

				<!-- 에디터 -->
				<div class="mb-4">
					<label class="form-label fw-bold">프로젝트 소개/업무 내용</label>
					<div id="editor"></div>
					<input type="hidden" name="prjAnncContent" id="prjAnncContent">
				</div>
				<div class="d-flex justify-content-end gap-2 mt-3">
					<a href="/board/project" class="btn btn-outline-secondary">취소</a>
					<button type="submit" class="btn btn_violet">등록</button>
				</div>
			</form>
		</div>

	</body>