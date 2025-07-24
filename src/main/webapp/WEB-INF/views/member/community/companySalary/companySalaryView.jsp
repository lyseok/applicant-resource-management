<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1.0" />
  <title>연봉 검색 UI</title>
  <script src = "/js/member/community/companySalary/companySalaryList.js"></script>
  <style>
   :root {
      --violet40: #ede9fe;
      --violet70: #7c3aed;
      --gray100: #f5f5f5;
      --gray200: #e5e5e5;
      --gray500: #6b7280;
      --gray700: #374151;
      --border: 1px solid #d1d5db;
      --radius: 8px;
    }
    * { box-sizing: border-box; margin: 0; padding: 0; }
    body { font-family: sans-serif; color: var(--gray700); background: #fff; }
    .container { max-width: 1000px; margin: 2rem auto; padding: 0 1rem; }

    /* 타이틀 */
    .title {
      font-size: 1.5rem;
      color: var(--violet70);
      margin-bottom: 1rem;
    }

    /* 탭 + 검색창 */
    .tabs-container {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 1rem;
    }
    .tabs {
      display: flex;
      gap: .5rem;
    }
    .tab {
      padding: .5rem 1rem;
      border: var(--border);
      border-radius: var(--radius);
      background: #fff;
      color: var(--gray500);
      cursor: pointer;
      font-size: .95rem;
    }
    .tab.active {
      background: var(--violet70);
      color: #fff;
      border-color: var(--violet70);
    }
    .search-box {
      position: relative;
      width: 30%;
    }
    .search-box input {
      width: 100%;
      padding: .75rem 1rem;
      border: var(--border);
      border-radius: var(--radius);
      font-size: 1rem;
    }
    .autocomplete-list {
      position: absolute;
      top: 100%; left: 0; right: 0;
      background: #fff;
      border: var(--border);
      border-top: none;
      border-radius: 0 0 var(--radius) var(--radius);
      max-height: 200px;
      overflow-y: auto;
      z-index: 10;
      list-style: none;
    }
    .autocomplete-list li {
      padding: .5rem 1rem;
      cursor: pointer;
    }
    .autocomplete-list li:hover {
      background: var(--gray100);
    }

    /* 연봉 슬라이더 */
    .salary-filter {
      background: var(--violet40);
      padding: 1rem;
      border-radius: var(--radius);
      margin-bottom: 2rem;
    }
    .salary-filter label {
      font-weight: bold;
      display: block;
      margin-bottom: .75rem;
    }
    .salary-controls {
      display: flex;
      align-items: center;
      gap: 1rem;
      position: relative;
    }
    .salary-controls select {
      padding: .5rem;
      border: var(--border);
      border-radius: var(--radius);
      background: #fff;
    }
    .slider-container {
      flex: 1;
      position: relative;
      height: 16px;
    }
    .slider-track {
      position: absolute;
      top: 50%; left: 0; right: 0;
      height: 4px;
      background: var(--gray200);
      transform: translateY(-50%);
      border-radius: 2px;
    }
    .slider-range {
      position: absolute;
      top: 50%;
      height: 4px;
      background: var(--violet70);
      transform: translateY(-50%);
      border-radius: 2px;
    }
    .handle {
      position: absolute;
      top: 50%;
      width: 24px; height: 24px;
      background: #fff;
      border: 2px solid var(--violet70);
      border-radius: 50%;
      transform: translate(-50%, -50%);
      cursor: grab;
    }
    .handle span {
      position: absolute;
      bottom: 100%;
      left: 50%;
      transform: translateX(-50%);
      margin-bottom: 6px;
      background: var(--gray700);
      color: #fff;
      padding: 4px 8px;
      border-radius: var(--radius);
      font-size: .75rem;
      white-space: nowrap;
    }

    /* 결과 리스트 */
    .list {
      list-style: none;
      margin: 0;
      padding: 0;
    }
    .item {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 3rem 0;
      border-bottom: var(--border);
      cursor: pointer;
    }
    .info {
      display: flex;
      align-items: center;
      gap: 1rem;
    }
    .logo {
      width: 48px; height: 48px;
      object-fit: contain;
      background: #eee;
    }
    .text {
      display: flex;
      flex-direction: column;
    }
    .name {
      font-weight: bold;
    }
    .meta {
      font-size: .9rem;
      color: var(--gray500);
    }
    .salary {
      font-weight: bold;
    }
  </style>
</head>
<body>
  <div class="container">
    <!-- 동적 타이틀 -->
    <div class="title" id="salary-title">0개 기업의 연봉이 등록되어 있습니다.</div>

    <!-- 탭 + 검색 -->
    <div class="tabs-container">
      <div class="tabs">
        <div class="tab active">전체</div>
        <div class="tab">기업형태별</div>
        <div class="tab">업종별</div>
      </div>
      <div class="search-box">
        <input id="search-input" placeholder="기업명을 검색해 보세요." autocomplete="off"/>
        <ul id="autocomplete-list" class="autocomplete-list"></ul>
      </div>
    </div>

    <!-- 연봉 슬라이더 -->
    <div class="salary-filter">
      <label>연봉</label>
      <div class="salary-controls">
        <select id="min-select">
          <option value="0">0만원 ~</option>
          <option value="1000">1,000만원 ~</option>
          <option value="2000">2,000만원 ~</option>
          <option value="3000">3,000만원 ~</option>
        </select>
        <div class="slider-container">
          <div class="slider-track"></div>
          <div class="slider-range" id="slider-range"></div>
          <div class="handle" id="min-handle"><span id="min-label">0만원</span></div>
          <div class="handle" id="max-handle"><span id="max-label">1억원↑</span></div>
        </div>
        <select id="max-select">
          <option value="10000">~ 1억원 이상</option>
          <option value="9000">~ 9,000만원</option>
          <option value="8000">~ 8,000만원</option>
        </select>
      </div>
    </div>

    <!-- 결과 리스트 -->
    <ul id="salary-list" class="list"></ul>
  </div>
</body>
</html>
