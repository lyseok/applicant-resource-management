let companyList = [];

// 검색 이벤트 처리
const searchForm = document.getElementById('searchForm');
const searchInput = document.querySelector('#search_input');
const dropdown = document.querySelector('#companyDropdown');
console.log('searchInput : ', searchInput);
initCompanyList();
// 버튼과 레이어 변수 저장
const memberBtn = document.querySelector('.member_btn');
const memberLayer = document.querySelector('.layer_member');

if (memberBtn) {
  // 멤버 버튼 클릭 이벤트
  memberBtn.addEventListener('click', function (event) {
    event.stopPropagation();
    const isOpen = memberLayer.style.display === 'block';
    // 토글
    memberLayer.style.display = isOpen ? 'none' : 'block';
  });
}

// 외부 클릭시 닫기 (버튼/레이어 아닌 부분 클릭시)
document.addEventListener('click', function (event) {
  if (memberBtn) {
    if (
      !memberBtn.contains(event.target) &&
      !memberLayer.contains(event.target)
    ) {
      memberLayer.style.display = 'none';
    }
  }
});

axios.get('/ajax/userinfo').then((res) => {
  const data = res.data;
  let name = '비회원';
  if (data.userType === 'company') name = data.userName;
  else if (data.userType === 'admin') name = '관리자';
  else if (data.userType === 'member') name = data.userName;
  if (document.getElementById('user_name')) {
    document.getElementById('user_name').textContent = name;
  }
  if (data.userImg != null) {
    const userImg = document.querySelector('.user_photo');
    userImg.innerHTML = "";
    const img = document.createElement("img");
    img.src = `${data.userImg}`;
    img.style.width = "100%";
    img.style.objectFit = "cover";
    userImg.append(img);
  }
});

// 로그아웃 버튼 이벤트
const logoutBtnEls = document.querySelectorAll('.logoutBtn');
logoutBtnEls.forEach((btn) => {
  btn.addEventListener('click', () => {
    console.log('test');
    axios
      .post(
        '/common/auth/revoke',
        {},
        {
          withCredentials: true,
        }
      )
      .then((resp) => (location.href = '/'));
  });
});

const customSelect = document.querySelector('.custom-select');
const selectStyled = customSelect.querySelector('.select-styled');
const optionsList = customSelect.querySelector('.select-options');

// 클릭 시 토글
selectStyled.addEventListener('click', (e) => {
  e.stopPropagation(); // 다른 곳 클릭 시 닫히는 기능 대비
  const isVisible = optionsList.style.display === 'block';
  optionsList.style.display = isVisible ? 'none' : 'block';
});

// 옵션 선택 시 값 반영 및 닫기
optionsList.querySelectorAll('li').forEach((li) => {
  li.addEventListener('click', (e) => {
    const value = li.getAttribute('data-value');
    const text = li.textContent;
    selectStyled.querySelector('span').textContent = text;
    selectStyled.dataset.value = value;
    optionsList.style.display = 'none';
  });
});

// 바깥 클릭 시 닫기
document.addEventListener('click', (e) => {
  if (!customSelect.contains(e.target)) {
    optionsList.style.display = 'none';
  }
});

searchForm.addEventListener('submit', (e) => {
  e.preventDefault(); // 기본 제출 동작 방지

  const selectedCategory = selectStyled.dataset.value || 'recruit'; // 기본값 설정
  const keyword = searchInput.value.trim();

  console.log('선택된 카테고리:', selectedCategory);
  console.log('입력된 검색어:', keyword);

  if (selectedCategory === 'recruit') {
    location.href = `/search/recruit?keyword=` + keyword;
  } else {
    const company = companyList.find((c) => c.COM_NAME === keyword);
    location.href =
      `/member/company_view?no=` + (company ? company.USER_ID : '');
  }
});

function initCompanyList() {
  axios.get('/ajax/company_name').then((res) => {
    companyList = res.data;
    console.log('Company List Initialized:', companyList);
  });
}

// 입력 이벤트
searchInput.addEventListener('input', () => {
  const keyword = searchInput.value.trim().toLowerCase();
  const selectedCategory = selectStyled.dataset.value || 'recruit';
  console.log(keyword);
  if (selectedCategory !== 'company' || keyword.length === 0) {
    dropdown.style.display = 'none';
    return;
  }

  // 필터링 후 5개만
  const matches = companyList
    .filter((c) => c.COM_NAME.toLowerCase().includes(keyword))
    .slice(0, 5);

  // 드롭다운 렌더링
  dropdown.innerHTML = '';
  if (matches.length > 0) {
    matches.forEach((c) => {
      const li = document.createElement('li');
      li.textContent = c.COM_NAME;
      li.addEventListener('click', () => {
        searchInput.value = c.COM_NAME; // 보여줄 값
        searchInput.dataset.userid = c.USER_ID; // 숨겨둘 USER_ID
        dropdown.style.display = 'none';
      });
      dropdown.appendChild(li);
    });
    dropdown.style.display = 'block';
  } else {
    dropdown.style.display = 'none';
  }
});
