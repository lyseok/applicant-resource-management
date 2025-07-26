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
      --violet40: #ede9fe;    /* 연봉 필터 배경 */
      --violet70: #7c3aed;    /* 주요 포인트 컬러 */
      --gray100: #f5f5f5;
      --gray200: #e5e5e5;
      --gray500: #6b7280;
      --gray700: #374151;
      --border: 1px solid #d1d5db;
      --radius: 8px;          /* 둥근 모서리 반경 */
    }
    * { box-sizing: border-box; margin:0; padding:0 }
    body {
      font-family: sans-serif;
      color: var(--gray700);
      background: #fff;
    }
    .container {
      max-width: 1000px;
      margin: 2rem auto;
      padding: 0 1rem;
    }

    /* 제목 */
    .title {
      display: flex;
      align-items: center;
      color: var(--violet70);
      font-size: 1.5rem;
      margin-bottom: 1rem;
    }
    .title svg {
      width: 32px;
      height: 32px;
      margin-right: 0.5rem;
    }

    /* 탭 + 검색창을 같은 줄에 배치 */
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

    /* 검색창 */
    .search-box {
      width: 30%;
    }
    .search-box input {
      width: 100%;
      padding: .75rem 1rem;
      border: var(--border);
      border-radius: var(--radius);
      font-size: 1rem;
    }

    /* 검색 조건 + 연봉 슬라이더 한 줄 배치 */
    .search-conditions {
      display: flex;
      align-items: center;
      gap: 1.5rem;
      margin-bottom: 2rem;
    
    }
    .search-conditions select {
      padding: .5rem;
      border: var(--border);
      border-radius: var(--radius);
      background: #fff;
      font-size: .9rem;
    }
    .search-conditions label {
      font-size: .9rem;
      display: flex;
      align-items: center;
      gap: .25rem;
    }

    /* 연봉 필터 박스 */
    .salary-filter {
      flex: 1;
      padding: 12px 16px;
      background: var(--violet40);
      border-radius: var(--radius);
      /* border 제거 */
    }
    .salary-filter label {
      display: block;
      font-weight: bold;
      margin-bottom: .75rem;
      font-size: .95rem;
    }
    .salary-controls {
      display: flex;
      align-items: center;
      gap: 1rem;
    }
    .tabs-container select {
      min-width: 6rem;
      padding: .5rem;
      border: var(--border);
      border-radius: var(--radius);
      background: #fff;
      font-size: .9rem;
    }
    .slider-container {
      position: relative;
      flex: 1;
      height: 16px;
     
    }
    .slider-track {
      position: absolute;
      top: 50%;
      left: 0;
      right: 0;
      height: 4px;
      background: var(--gray200);
      transform: translateY(-50%);
      border-radius: 2px;
      
    }
    .slider-track::before {
      content: '';
      position: absolute;
      top: 50%;
      left: 0;
      right: 0;
      height: 1px;
      transform: translateY(-50%);
      background-image: repeating-linear-gradient(
        to right,
        var(--gray200) 0,
        var(--gray200) 1px,
        transparent 1px,
        transparent 20px
      );
    }
    .slider-range {
      position: absolute;
      top: 50%;
      left: 0;
      right: 0;
      height: 4px;
      background: var(--violet70);
      transform: translateY(-50%);
      border-radius: 2px;
    }
    .handle {
      position: absolute;
      top: 50%;
      width: 30px;
      height: 30px;
      background: #fff;
      border: 2px solid var(--violet70);
      border-radius: 50%;
      transform: translate(-50%, -50%);
    }
    .min-handle { left: 0%; }
    .max-handle { left: 100%; }
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
    .handle span::after {
      content: '';
      position: absolute;
      top: 100%;
      left: 50%;
      transform: translateX(-50%);
      border: 5px solid transparent;
      border-top-color: var(--gray700);
    }

    /* 결과 리스트 */
    .list {
      list-style: none;
      margin: 0;
      padding: 0;
    }
    .item {
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 3rem 0;
      border-bottom: var(--border);
    }
    .info {
      display: flex;
      align-items: center;
      gap: 1rem;
    }
    .logo {
      width: 48px;
      height: 48px;
      object-fit: contain;
    }
    .text {
      display: flex;
      flex-direction: column;
    }
    .name {
      font-weight: bold;
      font-size: 1.1rem;
    }
    .meta {
      font-size: .9rem;
      color: var(--gray500);
      margin-top: .25rem;
    }
    .salary {
      font-size: 1.25rem;
      font-weight: bold;
      color: var(--gray700);
    }

    /* 페이지네이션 */
    .pagination {
      display: flex;
      list-style: none;
      gap: .5rem;
      justify-content: center;
      margin: 2rem 0;
      padding: 0;
    }
    .pagination li {
      width: 32px;
      height: 32px;
      display: flex;
      align-items: center;
      justify-content: center;
      border: var(--border);
      border-radius: var(--radius);
      cursor: pointer;
      color: var(--gray500);
      font-size: .9rem;
    }
    .pagination li.active {
      background: var(--violet70);
      color: #fff;
      border-color: var(--violet70);
    }
    
  
  </style>
</head>
<body>
  <div class="container">
    <!-- 제목 -->
    <div class="title" id ="salary-title">
      <svg viewBox="0 0 24 24" fill="currentColor">
        <path d="M3 13h4v8H3v-8zm7-6h4v14h-4V7zm7 4h4v10h-4V11z"/>
      </svg>
     
    </div>

    <!-- 탭 + 검색창 -->
    <div class="tabs-container">
      <div class="tabs">
        <div class="tab active">전체</div>
        <div class="tab">산업별</div>
        <div class="tab">직무별</div>
        
        
        <select>
        <option>1차분류 선택</option>
        <option>전기·전자·제어</option>
        <option>게임·애니메이션</option>
      </select>
      
      </div>
      
      
     
      <!-- 검색창 -->
      <div class="search-box">
        <input type="text" placeholder="기업명을 검색해 보세요." />
      </div>
      
      
   
    </div>

    <!-- 검색조건 + 연봉 슬라이더 -->
    <div class="search-conditions">
    
     
      <div class="salary-filter">
        <label>연봉</label>
        <div class="salary-controls">
          <select>
            <option>1,000만원 ~</option>
            <option>2,000만원 ~</option>
            <option>3,000만원 ~</option>
          </select>
          <div class="slider-container">
            <div class="slider-track"></div>
            <div class="slider-range"></div>
            <div class="handle min-handle"><span>1,000만원</span></div>
            <div class="handle max-handle"><span>1억원 이상</span></div>
          </div>
          <select>
            <option>~ 1억원 이상</option>
            <option>~ 9,000만원</option>
            <option>~ 8,000만원</option>
          </select>
        </div>
      </div>
    </div>

    <!-- 결과 리스트 -->
    <ul class="list" id = "salary-list">
    </ul>

    <!-- 페이지네이션 -->
    <ul class="pagination">
      <li class="active">1</li>
      <li>2</li>
      <li>3</li>
      <li>4</li>
      <li>5</li>
      <li>…</li>
      <li>10</li>
    </ul>
  </div>
</body>
</html>