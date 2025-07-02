const formEl = document.querySelector('#login-form');

formEl.addEventListener('submit', async (e) => {
  e.preventDefault();

  const json = axios.formToJSON(e.target);
  const resp = await axios.post('/common/auth', json, {
    withCredentials: true
  });

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