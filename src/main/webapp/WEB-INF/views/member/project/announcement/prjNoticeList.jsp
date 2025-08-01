<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<head>
		<title>커뮤니티 리스트</title>
		<script defer src="/js/member/project/announcement/prjNoticeList.js"></script>
		<style>
			/* 검색 입력 스타일 */
			.search-input-wrapper{
				display:flex;
				gap:10px;
			}
			.search-box{
				display:flex;
				width:100%;
				align-items:center;
				border-radius: 8px;
				border: 2px solid #e9ecef;
			}

			.search-input-wrapper .search-icon {
				flex-basis:48px;
				color: #6c757d;
				text-align:center;
				
			}

			.search-input-wrapper .search-input {
				height: 44px;
				font-size: 14px;
			}


			input:focus-visible,
			input:focus{
				outline:none !important;
				box-shadow:none  !important;
			}
			
			
			
			
			
			
			/* 태그 입력 컨테이너 스타일 */
			.tag-wrap{
				gap:10px;
			}
			.tag-input-wrapper {
				position: relative;
				width:calc(100% - 100px);
			}

			.tag-input-container {
				display: flex;
				align-items: center;
				min-height: 48px;
				padding: 8px 45px 8px 15px;
				border: 2px solid #e9ecef;
				border-radius: 8px;
				background-color: #fff;
				cursor: text;
				flex-wrap: wrap;
				gap: 6px;
				width:100%;
			}

			.tag-prefix {
				color: #6c757d;
				font-weight: 500;
				font-size: 16px;
				margin-right: 4px;
			}

			.tags-display {
				display: flex;
				flex-wrap: wrap;
				gap: 6px;
				align-items: center;
			}

			.badge-tag .remove-tag {
				margin-left: 6px;
				cursor: pointer;
				color: #6c757d;
				font-weight: bold;
				font-size: 14px;
				line-height: 1;
				padding: 2px;
				border-radius: 50%;
				width: 16px;
				height: 16px;
				display: flex;
				align-items: center;
				justify-content: center;
			}

			.badge-tag .remove-tag:hover {
				background-color: #e9ecef;
				color: #495057;
			}

			.tag-input {
				border: none;
				outline: none;
				flex: 1;
				min-width: 120px;
				font-size: 14px;
				padding: 4px 0;
				background: transparent;
			}

			.tag-input::placeholder {
				color: #adb5bd;
			}

			.tag-reset-btn {
				position: absolute;
				right: 12px;
				top: 50%;
				transform: translateY(-50%);
				background: none;
				border: none;
				color: #6c757d;
				cursor: pointer;
				padding: 4px;
				border-radius: 4px;
				display: flex;
				align-items: center;
				justify-content: center;
			}

			.tag-reset-btn:hover {
				background-color: #f8f9fa;
				color: #495057;
			}

			/* 검색 버튼 스타일 */
			.search-btn {
				background-color: #28a745;
				border-color: #28a745;
				padding: 12px 24px;
				font-weight: 500;
				border-radius: 8px;
				min-width: 80px;
			}

			.search-btn:hover {
				background-color: #218838;
				border-color: #1e7e34;
			}

			/* 반응형 스타일 */
			@media (max-width: 768px) {
				.tag-input-container {
					min-height: 44px;
					padding: 6px 40px 6px 12px;
				}

				.search-input-wrapper .search-input {
					height: 44px;
					padding-left: 40px;
				}

				.tag-input {
					min-width: 100px;
				}
			}
			
			/* 리스트 스타일 */
			.badge-recruit {
			    background: var(--violet80);
			}
		</style>
	</head>

	<body>
		<main class="AppContainer_root">
			<div class="AppContainer_inner_Nnowg">
				<div class="MainContainer_layout">
					<div class="MainContainer_mainContent">
						<div class="Recommend_recommend">

							<div class="Recommend_recommend_titleWrap">
								<h2 class="Recommend_title">최근 인기글🔥</h2>
							</div>

							<div class="Recommend_recommend_list">
								<div class="swiper recommend_list_wrap swiper-initialized swiper-horizontal">

									<div class="swiper-wrapper"></div>

								</div>
							</div>
						</div>
						<script>
							document.addEventListener('DOMContentLoaded', () => {
								const recommendSwiper = new Swiper('.recommend_list_wrap', {
									slidesPerView: 3.2,
								});
							});
						</script>

						<!-- ★★★★★★★★★★★★ 일반 게시글은 여기서부터 참고 ★★★★★★★★★★★★ -->

						<!-- 검색 영역 -->
						<div class="row align-items-center mb-4 g-2">
							<!-- 검색 입력 -->
							<div class="search-input-wrapper">
								<div class="search-box">
									<span class="material-symbols-outlined search-icon">search</span>
									<input type="text" class="form-control search-input border-0" placeholder="팀 프로젝트, 사이드프로젝트를 검색해보세요!" />
								</div>
								<button class="btn btn_violet_line search-btn justify-content-center fs-16 fw-500" id="searchBtn" type="button">
									검색
								</button>
							</div>
							
								<!-- 태그 입력 -->
								<div class="tag-input-wrapper">
									<div class="tag-input-container">
										<span class="tag-prefix">#</span>
										<div class="tags-display"></div>
										<input type="text" class="tag-input" placeholder="태그로 검색해보세요!" />
										<button type="button" class="tag-reset-btn" title="초기화">
											<span class="material-symbols-outlined">close</span>
										</button>
									</div>
								</div>
						</div>

						<div class="Post_post">
							<h2 class="Post_title Post_titleAddPadding">게시글</h2>
							<div class="Post_filter">
								<div class="Post_category d-flex align-items-center justify-content-between overflow-visible">
									<div class="d-flex">
										<div class="Post_categoryItem">
											<input id="status_all" type="radio" value="status_all" name="category" checked />
											<label for="status_all"><span>전체</span></label>
										</div>
										<div class="Post_categoryItem">
											<input id="status_n" type="radio" value="status_n" name="category" />
											<label for="status_n"><span>모집 중</span></label>
										</div>
										<div class="Post_categoryItem">
											<input id="status_y" type="radio" value="status_y" name="category" />
											<label for="status_y"><span>모집완료</span></label>
										</div>
										<select class="Select_root Select_size38 Select_round w140" name="selectName">
											<option value="date" selected="">최신순</option>
											<option value="pop">인기순</option>
											<option value="like">시작일순</option>
										</select>
									</div>
									<!-- 글쓰기 버튼 -->
									<div>
										<button class="btn btn_violet" id="writeBtn" type="button">
											<i class="bi bi-pencil-square me-1"></i> 글쓰기
										</button>
									</div>
								</div>
							</div>
							
							<div class="PostList_postList">

								<ul></ul>

								<!-- 페이징 영역, 부트스트랩 -->
								<div class="PageBox"></div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
	</body>