/**
 * 
 */
function showLoading() {
  document.getElementById('loadingSpinner').style.setProperty('display', 'flex', 'important');
}
function hideLoading() {
  document.getElementById('loadingSpinner').style.setProperty('display', 'none', 'important');
}



document.querySelector('.searchBarBtn').addEventListener('click', function(e) {
    e.preventDefault();
    const keyword = document.getElementById('listKeyword').value;
    window.location.href = '/mypage/resume/search?keyword=' + encodeURIComponent(keyword);
});
