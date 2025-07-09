<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
  <meta charset="UTF-8">
  <title>사람인 이력서 섹션별 추가폼</title>
  <link rel="stylesheet" href="/css/member/resume/resume.css" >
</head>
<body>
  <div class="resume-wrap">
    <!-- Header -->
    <div class="resume-header">
      <div class="profile-img">🙍‍♂️</div>
      <div class="header-info">
        <span class="name">이예솔</span><span class="age">1997 (28세)</span>
        <div class="contact">gptresume@gmail.com | 010-****-0064</div>
        <div class="info-badge">대표 이력서 번호 001</div>
      </div>
    </div>

    <!-- 학력 -->
    <div class="section" id="section-edu">
      <div class="section-title">
        <h6>학력<span class="must">필수</span></h6>
        <button class="add-btn" onclick="toggleForm('edu')">+ 추가</button>
      </div>
      <div class="section-content" style="color: #b8bfc9;">졸업학교 정보를 입력해 주세요</div>
      <!-- 추가 폼 (display: none) -->
      <form class="section-form-wrap" id="form-edu" onsubmit="submitForm(event,'edu')">
        <div class="section-form-row">
          <label>학교명</label>
          <input type="text" name="school" placeholder="예: 서울대학교">
        </div>
        <div class="section-form-row">
          <label>전공</label>
          <input type="text" name="major" placeholder="예: 컴퓨터공학과">
        </div>
        <div class="section-form-row">
          <label>졸업구분</label>
          <select name="status">
            <option>졸업</option>
            <option>재학</option>
            <option>중퇴</option>
            <option>수료</option>
          </select>
        </div>
        <div class="section-form-btns">
          <button type="button" class="btn-form cancel" onclick="toggleForm('edu',true)">취소</button>
          <button type="submit" class="btn-form">저장</button>
        </div>
      </form>
    </div>

    <!-- 경력 -->
    <div class="section" id="section-career">
      <div class="section-title">
        <h6>경력<span class="must">필수</span></h6>
        <button class="add-btn" onclick="toggleForm('career')">+ 추가</button>
      </div>
      <div class="section-content" style="color: #b8bfc9;">
        경력사항 또는 인턴, 현장실습 등 실제로 해본 경험이 있다면 자세히 작성해주세요!
      </div>
      <form class="section-form-wrap" id="form-career" onsubmit="submitForm(event,'career')">
        <div class="section-form-row">
          <label>회사명</label>
          <input type="text" name="company" placeholder="예: 네이버">
        </div>
        <div class="section-form-row">
          <label>직무/부서</label>
          <input type="text" name="role" placeholder="예: 프론트엔드 개발자">
        </div>
        <div class="section-form-row">
          <label>근무기간</label>
          <input type="text" name="period" placeholder="예: 2021.01 ~ 2022.12">
        </div>
        <div class="section-form-row">
          <label>상세내용</label>
          <textarea name="desc" placeholder="담당업무 및 주요성과 등"></textarea>
        </div>
        <div class="section-form-btns">
          <button type="button" class="btn-form cancel" onclick="toggleForm('career',true)">취소</button>
          <button type="submit" class="btn-form">저장</button>
        </div>
      </form>
    </div>

    <!-- 스킬 -->
    <div class="section" id="section-skill">
      <div class="section-title">
        <h6>스킬<span class="must">필수</span></h6>        
        <button class="add-btn" onclick="toggleForm('skill')">+ 추가</button>
      </div>
      <div class="section-content">
        <div class="self-intro-box" style="color:#1b479a;">직무와 관련해여 내가 사용가능한 툴을 추가해보세요.</div>
      </div>
      <form class="section-form-wrap" id="form-skill" onsubmit="submitForm(event,'skill')">
        <div class="section-form-row">
          <label>기술/스킬명</label>
          <input type="text" name="skill" placeholder="예: JavaScript, React, Node.js">
        </div>
        <div class="section-form-row">
          <label>수준</label>
          <select name="level">
            <option>상</option>
            <option>중</option>
            <option>하</option>
          </select>
        </div>
        <div class="section-form-btns">
          <button type="button" class="btn-form cancel" onclick="toggleForm('skill',true)">취소</button>
          <button type="submit" class="btn-form">저장</button>
        </div>
      </form>
    </div>

    <!-- 경험(보유경험 테이블) -->
    <div class="section" id="section-exp">
      <div class="section-title">
        <h6>경험<span class="must">필수</span></h6>        
        <button class="add-btn" onclick="toggleForm('exp')">+ 추가</button>
      </div>
      <div class="section-content" style="color: #b8bfc9;">
        교육이수내역, 해외연수, 대외활동 등 경험을 작성해주세요!
      </div>
      <form class="section-form-wrap" id="form-exp" onsubmit="submitForm(event,'exp')">
        <div class="section-form-row">
          <label>활동명</label>
          <input type="text" name="exp" placeholder="예: 멋쟁이사자처럼 11기">
        </div>
        <div class="section-form-row">
          <label>기관/회사명</label>
          <input type="text" name="org" placeholder="예: 네이버 커넥트재단">
        </div>
        <div class="section-form-row">
          <label>활동구분</label>
          <select name="type">
            <option>교육</option>
            <option>활동</option>
            <option>연수</option>
          </select>
        </div>
        <div class="section-form-row">
          <label>활동설명</label>
          <textarea name="desc" placeholder="경험한 내용, 느낀 점"></textarea>
        </div>
        <div class="section-form-btns">
          <button type="button" class="btn-form cancel" onclick="toggleForm('exp',true)">취소</button>
          <button type="submit" class="btn-form">저장</button>
        </div>
      </form>
    </div>
    
    <!-- 활동(고용지원 테이블) -->
    <div class="section" id="section-support">
      <div class="section-title">
        <h6>고용지원여부</h6>        
        <button class="add-btn" onclick="toggleForm('support')">+ 추가</button>
      </div>
      <div class="section-content" style="color: #b8bfc9;">
        장애 등 고용지원 대상자에 해당되는 경우를 적어주세요.
      </div>
      <form class="section-form-wrap" id="form-exp" onsubmit="submitForm(event,'support')">
        <div class="section-form-row">
          <label>고용지원 대상명</label>
          <input type="text" name="exp" placeholder="예: 멋쟁이사자처럼 11기">
        </div>
        <div class="section-form-row">
          <label>기관/회사명</label>
          <input type="text" name="org" placeholder="예: 네이버 커넥트재단">
        </div>
        <div class="section-form-row">
          <label>활동설명</label>
          <textarea name="desc" placeholder="경험한 내용, 느낀 점"></textarea>
        </div>
        <div class="section-form-btns">
          <button type="button" class="btn-form cancel" onclick="toggleForm('support',true)">취소</button>
          <button type="submit" class="btn-form">저장</button>
        </div>
      </form>
    </div>

    
    <!-- 자격증 -->
    <div class="section" id="section-license">
      <div class="section-title">
        <h6>자격증</h6>        
        <button class="add-btn" onclick="toggleForm('license')">+ 추가</button>
      </div>
      <div class="section-content" style="color: #b8bfc9;">
        보유 자격증 정보를 입력해주세요
      </div>
      <form class="section-form-wrap" id="form-cert" onsubmit="submitForm(event,'license')">
        <div class="section-form-row">
          <label>명칭</label>
          <input type="text" name="name" placeholder="자격/어학/수상명">
        </div>
        <div class="section-form-row">
          <label>기관명</label>
          <input type="text" name="org" placeholder="발급/수여기관">
        </div>
        <div class="section-form-row">
          <label>상세</label>
          <textarea name="desc" placeholder="상세 내용"></textarea>
        </div>
        <div class="section-form-btns">
          <button type="button" class="btn-form cancel" onclick="toggleForm('license',true)">취소</button>
          <button type="submit" class="btn-form">저장</button>
        </div>
      </form>
    </div>
    
    <!-- 어학 -->
    <div class="section" id="section-language">
      <div class="section-title">
        <h6>어학</h6>        
        <button class="add-btn" onclick="toggleForm('language')">+ 추가</button>
      </div>
      <div class="section-content" style="color: #b8bfc9;">
        보유 어학자격증 정보를 입력해주세요
      </div>
      <form class="section-form-wrap" id="form-cert" onsubmit="submitForm(event,'language')">
        <div class="section-form-row">
          <label>명칭</label>
          <input type="text" name="name" placeholder="자격/어학/수상명">
        </div>
        <div class="section-form-row">
          <label>기관명</label>
          <input type="text" name="org" placeholder="발급/수여기관">
        </div>
        <div class="section-form-row">
          <label>상세</label>
          <textarea name="desc" placeholder="상세 내용"></textarea>
        </div>
        <div class="section-form-btns">
          <button type="button" class="btn-form cancel" onclick="toggleForm('language',true)">취소</button>
          <button type="submit" class="btn-form">저장</button>
        </div>
      </form>
    </div>
    
    <!-- 수상 -->
    <div class="section" id="section-award">
      <div class="section-title">
        <h6>수상<span class="must">필수</span></h6>        
        <button class="add-btn" onclick="toggleForm('award')">+ 추가</button>
      </div>
      <div class="section-content" style="color: #b8bfc9;">
        상세 수상 내역을 입력해주세요
      </div>
      <form class="section-form-wrap" id="form-cert" onsubmit="submitForm(event,'award')">
        <div class="section-form-row">
          <label>명칭</label>
          <input type="text" name="name" placeholder="수상명">
        </div>
        <div class="section-form-row">
          <label>기관명</label>
          <input type="text" name="org" placeholder="발급/수여기관">
        </div>
        <div class="section-form-row">
          <label>상세</label>
          <textarea name="desc" placeholder="상세 내용"></textarea>
        </div>
        <div class="section-form-btns">
          <button type="button" class="btn-form cancel" onclick="toggleForm('award',true)">취소</button>
          <button type="submit" class="btn-form">저장</button>
        </div>
      </form>
    </div>

    <!-- 포트폴리오  -->
    <div class="section" id="section-portfolio">
      <div class="section-title">
        <h6>포트폴리오<span class="must">필수</span></h6>        
        <button class="add-btn" onclick="toggleForm('portfolio')">+ 추가</button>
      </div>
      <div class="section-content" style="color: #b8bfc9;">
        포트폴리오 및 기타문서를 추가해주세요
      </div>
      <form class="section-form-wrap" id="form-portfolio" onsubmit="submitForm(event,'portfolio')">
        <div class="section-form-row">
          <label>문서명</label>
          <input type="text" name="doc" placeholder="예: 포트폴리오.pdf">
        </div>
        <div class="section-form-row">
          <label>설명</label>
          <textarea name="desc" placeholder="문서 설명"></textarea>
        </div>
        <div class="section-form-btns">
          <button type="button" class="btn-form cancel" onclick="toggleForm('portfolio',true)">취소</button>
          <button type="submit" class="btn-form">저장</button>
        </div>
      </form>
    </div>

    <!-- 자기소개서 -->
    <div class="section" id="section-selfintro">
      <div class="section-title">
        <h6>자기소개서<span class="must">필수</span></h6>        
        <button class="add-btn" onclick="toggleForm('selfintro')">+ 추가</button>
      </div>
      <div class="section-content">
        <div style="white-space:pre-line;">frawrf affewfas</div>
        <div class="edit-icons">
          <span onclick="alert('수정')">✏️</span>
          <span onclick="alert('삭제')">🗑️</span>
        </div>
      </div>
      <form class="section-form-wrap" id="form-selfintro" onsubmit="submitForm(event,'selfintro')">
        <div class="section-form-row">
          <label>자기소개서 제목</label>
          <input type="text" name="title" placeholder="예: 성장하는 개발자">
        </div>
        <div class="section-form-row">
          <label>자기소개서 내용</label>
          <textarea name="content" placeholder="자기소개서를 입력하세요"></textarea>
        </div>
        <div class="section-form-btns">
          <button type="button" class="btn-form cancel" onclick="toggleForm('selfintro',true)">취소</button>
          <button type="submit" class="btn-form">저장</button>
        </div>
      </form>
    </div>

    <!-- 병역 -->
    <div class="section" id="section-prefer">
      <div class="section-title">
        <h6>병역<span class="must">필수</span></h6>        
        <button class="add-btn" onclick="toggleForm('prefer')">+ 추가</button>
      </div>
      <div class="section-content" style="color: #b8bfc9;">
        취업우대사항을 추가해주세요.
      </div>
      <form class="section-form-wrap" id="form-prefer" onsubmit="submitForm(event,'prefer')">
        <div class="section-form-row">
          <label>우대사항</label>
          <div>
            <div class="check-row">
              <label><input type="checkbox" name="prefer" value="보훈대상자"> 보훈대상자</label>
              <label><input type="checkbox" name="prefer" value="영어능통자"> 영어능통자</label>
              <label><input type="checkbox" name="prefer" value="근무지선택가능"> 근무지선택가능</label>
            </div>
          </div>
        </div>
        <div class="section-form-btns">
          <button type="button" class="btn-form cancel" onclick="toggleForm('prefer',true)">취소</button>
          <button type="submit" class="btn-form">저장</button>
        </div>
      </form>
    </div>
  </div>
  <script>
    // 폼 토글 함수: 하나만 열리게
    function toggleForm(key, closeOnly = false) {
      const ids = [
        'edu','career','skill','exp','cert','portfolio','selfintro','prefer'
      ];
      ids.forEach(id => {
        const form = document.getElementById('form-' + id);
        if (!form) return;
        if (id === key && !closeOnly) form.classList.add('active');
        else form.classList.remove('active');
      });
      // 스크롤 자연스럽게 이동 (폼이 열릴 때만)
      if (!closeOnly) {
        setTimeout(() => {
          document.getElementById('form-'+key).scrollIntoView({behavior:'smooth',block:'center'});
        }, 80);
      }
    }
    // 임시 폼 submit
    function submitForm(e, key) {
      e.preventDefault();
      alert("입력하신 값이 임시로 저장되었습니다!");
      toggleForm(key, true);
    }
  </script>
</body>