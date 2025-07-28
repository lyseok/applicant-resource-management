<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<head>
		<title>커뮤니티 리스트</title>
		<script defer src="/js/member/project/announcement/prjNoticeList.js"></script>
		<style>
			/* 검색 입력 스타일 */
			.search-input-wrapper {
				position: relative;
			}

			.search-input-wrapper .search-icon {
				position: absolute;
				left: 15px;
				top: 50%;
				transform: translateY(-50%);
				color: #6c757d;
				z-index: 2;
			}

			.search-input-wrapper .search-input {
				padding-left: 45px;
				height: 48px;
				border: 2px solid #e9ecef;
				border-radius: 8px;
				font-size: 14px;
			}

			.search-input-wrapper .search-input:focus {
				border-color: #28a745;
				box-shadow: 0 0 0 0.2rem rgba(40, 167, 69, 0.25);
			}

			/* 태그 입력 컨테이너 스타일 */
			.tag-input-wrapper {
				position: relative;
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
			}

			.tag-input-container:focus-within {
				border-color: #28a745;
				box-shadow: 0 0 0 0.2rem rgba(40, 167, 69, 0.25);
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

			.tag-chip {
				display: inline-flex;
				align-items: center;
				background-color: #f8f9fa;
				border: 1px solid #dee2e6;
				border-radius: 16px;
				padding: 4px 8px 4px 12px;
				font-size: 13px;
				color: #495057;
				white-space: nowrap;
			}

			.tag-chip .remove-tag {
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

			.tag-chip .remove-tag:hover {
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
									slidesPerView: 3,
								});
							});
						</script>

						<!-- ★★★★★★★★★★★★ 일반 게시글은 여기서부터 참고 ★★★★★★★★★★★★ -->

						<!-- 검색 영역 -->
						<div class="row align-items-center mb-4 g-2">
							<!-- 검색 입력 -->
							<div class="col-12 col-md-6">
								<div class="search-input-wrapper">
									<span class="material-symbols-outlined">
										search
									</span>
									<input type="text" class="form-control search-input" placeholder="팀 프로젝트, 사이드프로젝트를 검색해보세요!" />
									<button class="btn btn-success search-btn" id="searchBtn" type="button">
										검색
									</button>
								</div>
							</div>
							<!-- 검색 버튼 -->
							<div class="col-12 text-end mt-2">
							</div>
							<!-- 태그 입력 -->
							<div class="col-12 col-md-6">
								<div class="tag-input-wrapper">
									<div class="tag-input-container">
										<span class="tag-prefix">#</span>
										<div class="tags-display"></div>
										<input type="text" class="tag-input" placeholder="태그로 검색해보세요!" />
										<button type="button" class="tag-reset-btn" title="초기화">
											<span class="material-symbols-outlined">
												close
											</span>
										</button>
									</div>
								</div>
							</div>

							<!-- 글쓰기 버튼 -->
							<div class="col-12 col-md-3 text-end">
								<button class="btn btn-write mt-2 mt-md-0" id="writeBtn" type="button">
									<i class="bi bi-pencil-square me-1"></i> 글쓰기
								</button>
							</div>
						</div>

						<div class="Post_post">
							<h2 class="Post_title Post_titleAddPadding">게시글</h2>
							<div class="Post_filter">
								<div class="Post_category d-flex justify-content-between align-items-center overflow-visible">
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
									</div>
									<div>
										<select class="Select_root Select_size38 Select_round w140" name="selectName">
											<option value="date" selected="">최신순</option>
											<option value="pop">인기순</option>
											<option value="like">시작일순</option>
										</select>
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