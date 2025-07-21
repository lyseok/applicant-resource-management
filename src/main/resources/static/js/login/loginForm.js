const formEl = document.querySelector('#login-form');

formEl.addEventListener('submit', async (e) => {
  e.preventDefault();

  const json = axios.formToJSON(e.target);
  let resp;
  try {
  resp = await axios.post('/common/auth', json, {
    withCredentials: true
  });
  // 로그인 성공시 이후 처리...
} catch (err) {
  alert('로그인에 실패했습니다.\n아이디 또는 비밀번호를 다시 확인해 주세요.');
}

  if(resp.status === 200) {
    const {data} = resp;
    if(data.roles.includes('ROLE_USER')){
      location.href="/";
    } else if(data.roles.includes('ROLE_COMPANY')) {
      location.href="/company";
    } else if(data.roles.includes('ROLE_ADMIN')) {
      location.href="/admin";
    }
  }
});

const memAuth = document.querySelector('#mem-auth');
const compAuth = document.querySelector('#comp-auth');
const adminAuth = document.querySelector('#admin-auth');
const username = document.querySelector('#username');
const password = document.querySelector('#password');

memAuth.addEventListener("click", () => {
	username.value='testUser';
	password.value='java';
});
compAuth.addEventListener("click", () => {
	username.value='testCompany';
	password.value='java';
});
adminAuth.addEventListener("click", () => {
	username.value='testAdmin';
	password.value='java';
});