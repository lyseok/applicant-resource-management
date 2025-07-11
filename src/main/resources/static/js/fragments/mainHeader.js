const logoutBtnEl = document.querySelector('.logoutBtn');

if (logoutBtnEl) {
  logoutBtnEl.addEventListener('click', () => {
    axios.post("/common/auth/revoke", {}, {
      withCredentials: true
    }).then(resp => location.href = "/");
  });
}