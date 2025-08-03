// 선택된 템플릿 번호
let selectedTemNo = null;

function showLoading() {
  document
    .getElementById('loadingSpinner')
    .style.setProperty('display', 'flex', 'important');
}
function hideLoading() {
  document
    .getElementById('loadingSpinner')
    .style.setProperty('display', 'none', 'important');
}

// 템플릿 추가 버튼 클릭 시 모달 열기
document.addEventListener('DOMContentLoaded', function () {
  const addTemBtn = document.getElementById('addTemModal');
  const templateModal = new bootstrap.Modal(
    document.getElementById('templateModal')
  );
  const templateForm = document.getElementById('templateForm');

  if (addTemBtn) {
    addTemBtn.addEventListener('click', function () {
      selectedTemNo = null;
      if (templateForm) {
        templateForm.reset();
      }
      templateModal.show();
    });
  }
});

// 메일 템플릿 목록 불러오기 및 렌더링
document.addEventListener('DOMContentLoaded', function () {
  const resumeContainer = document.getElementById('resumeListContainer');

  // 만약 해당 컨테이너가 없으면 아무것도 하지 않음
  if (!resumeContainer) return;

  axios
    .get('/ajax/company/mail/list')
    .then((res) => {
      const list = res.data;
      temCnt.innerHTML = list.length;
      if (!Array.isArray(list) || list.length === 0) {
        resumeContainer.innerHTML = `
          <div class="intoructionList p-6 d-flex flex-column align-items-center gap-2">
            <span>띹잡에 등록된 템플릿이 없어요!</span>
          </div>
        `;
        return;
      }

      const html = list
        .map(
          (template) => `
        <li class="pt-5 pb-5 border-bottom" data-temno="${template.temNo}">
          <div class="d-block h4 fw-bold mb-2">${template.temTitle}</div>
          <p class="text-secondary mb-2">${template.temContent}</p>
          <p class="text-muted fs-14">생성일: ${template.createDate}</p>
        </li>
      `
        )
        .join('');
      resumeContainer.innerHTML = `<ul>${html}</ul>`;
    })
    .catch((err) => {
      console.error('메일 템플릿 목록 불러오기 실패:', err);
    });
});

const templateForm = document.getElementById('templateForm');
const templateModalEl = document.getElementById('templateModal');
const templateModal = new bootstrap.Modal(templateModalEl);

templateForm.addEventListener('submit', function (e) {
  e.preventDefault();

  const title = document.getElementById('templateTitle').value.trim();
  const content = document.getElementById('templateContent').value.trim();

  if (!title || !content) {
    alert('제목과 내용을 모두 입력해주세요.');
    return;
  }

  const data = {
    temTitle: title,
    temContent: content,
  };

  const method = selectedTemNo ? 'put' : 'post';
  const url = selectedTemNo
    ? `/ajax/company/mail/${selectedTemNo}`
    : '/ajax/company/mail';

  axios[method](url, data)
    .then(() => {
      alert(
        selectedTemNo
          ? '템플릿이 수정되었습니다.'
          : '템플릿이 성공적으로 등록되었습니다.'
      );
      templateModal.hide();
      templateForm.reset();
      selectedTemNo = null;
      location.reload();
    })
    .catch((err) => {
      console.error('템플릿 저장 실패:', err);
      alert('템플릿 저장에 실패했습니다.');
    });
});

// 템플릿 상세 모달 관련 변수 및 이벤트 위임

const detailModalEl = document.getElementById('templateDetailModal');

const detailModal = new bootstrap.Modal(detailModalEl);
const detailTemTitle = document.getElementById('detailTemTitle');
const detailTemContent = document.getElementById('detailTemContent');
const detailCreateDate = document.getElementById('detailCreateDate');

const resumeListContainer = document.getElementById('resumeListContainer');

resumeListContainer.addEventListener('click', function (e) {
  const item = e.target.closest('li');
  if (!item) return;
  // 템플릿 번호 저장
  selectedTemNo = item.getAttribute('data-temno');
  // 템플릿 제목, 내용, 생성일 가져오기
  const titleEl = item.querySelector('.fw-bold');
  const contentEl = item.querySelector('.text-secondary');
  const dateEl = item.querySelector('.text-muted');
  if (!titleEl || !contentEl || !dateEl) return;

  detailTemTitle.innerText = titleEl.innerText;
  detailTemContent.innerText = contentEl.innerText;
  detailCreateDate.innerText = dateEl.innerText;

  detailModal.show();
});

// "수정" 버튼 클릭 시 등록 모달에 상세 정보 채워넣기
document
  .getElementById('editTemplateBtn')
  .addEventListener('click', function () {
    if (!selectedTemNo) return;
    document.getElementById('templateTitle').value = detailTemTitle.innerText;
    document.getElementById('templateContent').value =
      detailTemContent.innerText;
    detailModal.hide();
    templateModal.show();
  });

// "삭제" 버튼 클릭 시 삭제 요청
document
  .getElementById('deleteTemplateBtn')
  .addEventListener('click', function () {
    if (!selectedTemNo) return;
    if (!confirm('정말 이 템플릿을 삭제하시겠습니까?')) return;

    axios
      .delete(`/ajax/company/mail/${selectedTemNo}`)
      .then(() => {
        alert('템플릿이 삭제되었습니다.');
        detailModal.hide();
        location.reload();
      })
      .catch((err) => {
        console.error('템플릿 삭제 실패:', err);
        alert('삭제 중 오류가 발생했습니다.');
      });
  });
