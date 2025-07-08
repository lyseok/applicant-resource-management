const getProjectList = async () => {
  const resp = await axios.get('/ajax/member/project');
  console.log(resp);
  console.log(resp.data);
}

getProjectList();