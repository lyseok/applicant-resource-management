(() => {
  let companyList = [];
  const searchForm = document.getElementById('searchForm');
  const searchInput = document.querySelector('#search_input');
  const dropdown = document.querySelector('#companyDropdown');
  console.log('searchInput : ', searchInput);
  initCompanyList();
  const memberBtn = document.querySelector('.member_btn');
  const memberLayer = document.querySelector('.layer_member');
  if (memberBtn) {
    memberBtn.addEventListener('click', function (event) {
      event.stopPropagation();
      const isOpen = memberLayer.style.display === 'block';
      memberLayer.style.display = isOpen ? 'none' : 'block';
    });
  }
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
  window.userInfo = null;

  axios.get('/ajax/userinfo').then((res) => {
    const data = res.data;
    window.userInfo = data;

    // --- 기존 헤더 처리 ---
    let name = '비회원';
    if (data.userType === 'company') name = data.userName;
    else if (data.userType === 'admin') name = '관리자';
    else if (data.userType === 'member') name = data.userName;
    const userNameEl = document.getElementById('user_name');
    if (userNameEl) userNameEl.textContent = name;

    if (data.userImg != null) {
      const userImg = document.querySelector('.user_photo');
      if (userImg) {
        userImg.innerHTML = '';
        const img = document.createElement('img');
        img.src = `${data.userImg}`;
        img.style.width = '100%';
        img.style.objectFit = 'cover';
        userImg.append(img);
      }
    }

    // --- 사이드 메뉴 잠금 해제 여기서 바로 실행 ---
    unlockMenu();
  });
  
  function unlockMenu() {
    if (!window.userInfo || !window.userInfo.payment || window.userInfo.payment === 'N') {
      console.log("[DEBUG] Payment info not ready or N");
      return;
    }
    const menuEls = document.querySelectorAll('#layout-menu .off');
    console.log("[DEBUG] Found menuEls count:", menuEls.length);
    if (menuEls.length === 0) {
      // 메뉴가 아직 안 그려졌을 수 있음 → 100ms 뒤 재시도
      setTimeout(unlockMenu, 100);
      return;
    }

    const firstUnlock = !sessionStorage.getItem("menuUnlocked");
    menuEls.forEach(el => {
      el.classList.remove('off');
      if (firstUnlock && el.classList.contains('menu-link')) {
        el.classList.add('unlock-anim');
        setTimeout(() => el.classList.remove('unlock-anim'), 2000);
      }
    });
    if (firstUnlock) sessionStorage.setItem("menuUnlocked", "true");
    console.log("[DEBUG] Off classes removed & animation applied");
  }
  
  const logoutBtnEls = document.querySelectorAll('.logoutBtn');
  logoutBtnEls.forEach((btn) => {
    btn.addEventListener('click', () => {
      console.log('test');
      axios
        .post('/common/auth/revoke', {}, { withCredentials: true })
        .then((resp) => (location.href = '/'));
    });
  });
  const customSelect = document.querySelector('.custom-select');
  const selectStyled = customSelect.querySelector('.select-styled');
  const optionsList = customSelect.querySelector('.select-options');
  selectStyled.addEventListener('click', (e) => {
    e.stopPropagation();
    const isVisible = optionsList.style.display === 'block';
    optionsList.style.display = isVisible ? 'none' : 'block';
  });
  optionsList.querySelectorAll('li').forEach((li) => {
    li.addEventListener('click', (e) => {
      const value = li.getAttribute('data-value');
      const text = li.textContent;
      selectStyled.querySelector('span').textContent = text;
      selectStyled.dataset.value = value;
      optionsList.style.display = 'none';
    });
  });
  document.addEventListener('click', (e) => {
    if (!customSelect.contains(e.target)) {
      optionsList.style.display = 'none';
    }
  });
  searchForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const selectedCategory = selectStyled.dataset.value || 'recruit';
    const keyword = searchInput.value.trim();
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
  searchInput.addEventListener('input', () => {
    const keyword = searchInput.value.trim().toLowerCase();
    const selectedCategory = selectStyled.dataset.value || 'recruit';
    if (selectedCategory !== 'company' || keyword.length === 0) {
      dropdown.style.display = 'none';
      return;
    }
    const matches = companyList
      .filter((c) => c.COM_NAME.toLowerCase().includes(keyword))
      .slice(0, 5);
    dropdown.innerHTML = '';
    if (matches.length > 0) {
      matches.forEach((c) => {
        const li = document.createElement('li');
        li.textContent = c.COM_NAME;
        li.addEventListener('click', () => {
          searchInput.value = c.COM_NAME;
          searchInput.dataset.userid = c.USER_ID;
          dropdown.style.display = 'none';
        });
        dropdown.appendChild(li);
      });
      dropdown.style.display = 'block';
    } else {
      dropdown.style.display = 'none';
    }
  });
})();
