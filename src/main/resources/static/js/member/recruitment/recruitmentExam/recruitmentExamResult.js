/**
 *
 */

document.addEventListener('DOMContentLoaded', () => {
  const recruitExamNo = new URLSearchParams(window.location.search).get(
    'recruitExamNo'
  );
  const applicantId = new URLSearchParams(window.location.search).get(
    'applicantId'
  );

  axios
    .get(`/ajax/mypage/recruitment_exam/result/${recruitExamNo}/${applicantId}`)
    .then((resp) => {
      console.log(resp.data);
    });
});
