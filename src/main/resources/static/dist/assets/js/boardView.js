document.addEventListener('DOMContentLoaded', function() {
  var btnMore = document.querySelector('.btn_more_cont');
  var benefit = document.querySelector('.jv_benefit');

  if (!btnMore || !benefit) return; // 버튼이나 혜택 영역이 없으면 그냥 종료

  btnMore.addEventListener('click', function() {
    benefit.classList.toggle('expand');
  });
});


function scrollToWithOffset(selector, offsetPx) {
  const el = document.querySelector(selector);
  if (!el) return;
  
  const topPos = el.getBoundingClientRect().top + window.pageYOffset;
  window.scrollTo({
    top: topPos + offsetPx,
    behavior: 'smooth'
  });
}

// 모든 .spr_jview 버튼에 클릭 리스너 등록
document.querySelectorAll('.spr_jview.ready').forEach(btn => {
  btn.addEventListener('click', function() {
    // 1) 기존에 on 붙어있던 버튼들에서 on 제거
    document
      .querySelectorAll('.spr_jview.ready.on')
      .forEach(el => el.classList.remove('on'));

    // 2) 클릭된 버튼에 on 추가
    this.classList.add('on');

    // 3) scrollToWithOffset 호출
    //    data-* 속성에서 타깃 ID와 offset을 가져오도록 했습니다
    const target = this.getAttribute('data-target');
    const offset = parseInt(this.getAttribute('data-offset'), 10) || 0;
    scrollToWithOffset(target, offset);
  });
});


document.querySelectorAll('.board_view_wrap').forEach(el => {
  const parentInner = el.closest('.inner');
  if (parentInner) {
    parentInner.style.maxWidth = '100%';
  }
});
