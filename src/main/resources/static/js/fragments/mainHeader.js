const logoutBtnEl = document.querySelector('.logoutBtn');

logoutBtnEl.addEventListener('click', () => {
  axios.post("/common/auth/revoke", {}, {
    withCredentials:true
  }).then(resp=>location.href="/");
});