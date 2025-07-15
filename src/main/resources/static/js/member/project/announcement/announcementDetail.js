// 모달 데이터(MOCK)
const mockResumes = [
  {id: "1", title: "이훈석의 이력서 입니다", createdAt: "2025.06.04 (수) 19:57 작성", hasAttachment: false},
  {id: "2", title: "프론트엔드 개발자 이력서", createdAt: "2025.05.15 (월) 14:30 작성", hasAttachment: true},
  {id: "3", title: "백엔드 개발자 포트폴리오", createdAt: "2025.04.20 (목) 10:15 작성", hasAttachment: true},
];
const applicationFields = ["기획", "디자인", "프론트엔드"];

let selectedField = "";
let selectedResume = mockResumes[0];

const selectField = document.getElementById('selectField');
const selectedResumeCard = document.getElementById('selectedResumeCard');
const resumeList = document.getElementById('resumeList');
const btnShowResumeList = document.getElementById('btnShowResumeList');
const btnSaveApplication = document.getElementById('btnSaveApplication');
const modalProjectTitle = document.getElementById('modalProjectTitle');

// 필드 옵션 채우기
selectField.innerHTML += applicationFields.map(field => `<option value="${field}">${field}</option>`).join('');

// 필드 선택
selectField.onchange = function() {
  selectedField = this.value;
};

// // 이력서 카드 렌더
// function renderSelectedResumeCard() {
//   selectedResumeCard.innerHTML = `
//     <div class="card mb-0 selected-card">
//       <div class="card-body py-2 px-3">
//         <p class="mb-1 text-secondary" style="font-size: .92em;">${selectedResume.createdAt}</p>
//         <h6 class="mb-1">${selectedResume.title}</h6>
//         <div class="d-flex align-items-center gap-2 text-secondary" style="font-size:.96em;">
//           <i class="bi bi-file-earmark-text"></i>
//           <span>${selectedResume.hasAttachment ? "첨부파일이 있습니다" : "첨부파일이 없습니다"}</span>
//         </div>
//       </div>
//     </div>
//   `;
// }
// renderSelectedResumeCard();

// // 이력서 목록 렌더
// function renderResumeList() {
//   resumeList.innerHTML = mockResumes.map(resume => `
//     <div class="card mb-2 resume-card ${selectedResume.id === resume.id ? 'selected-card' : ''}" data-id="${resume.id}">
//       <div class="card-body py-2 px-3">
//         <p class="mb-1 text-secondary" style="font-size: .92em;">${resume.createdAt}</p>
//         <h6 class="mb-1">${resume.title}</h6>
//         <div class="d-flex align-items-center gap-2 text-secondary" style="font-size:.96em;">
//           <i class="bi bi-file-earmark-text"></i>
//           <span>${resume.hasAttachment ? "첨부파일이 있습니다" : "첨부파일이 없습니다"}</span>
//         </div>
//       </div>
//     </div>
//   `).join('');
//   // 카드 클릭시 이력서 선택
//   resumeList.querySelectorAll('.resume-card').forEach(card => {
//     card.onclick = function() {
//       const rid = this.getAttribute('data-id');
//       selectedResume = mockResumes.find(r => r.id === rid);
//       renderSelectedResumeCard();
//       resumeList.style.display = 'none';
//     };
//   });
// }

// // 이력서 변경 버튼
// btnShowResumeList.onclick = function() {
//   renderResumeList();
//   resumeList.style.display = resumeList.style.display === 'none' ? 'block' : 'none';
// };

// 임시저장 버튼
btnSaveApplication.onclick = function() {
  if (!selectedField) {
    alert("지원 부문을 선택해주세요!");
    selectField.focus();
    return;
  }
  alert(`[임시저장]\n지원부문: ${selectedField}\n이력서: ${selectedResume.title}`);
  // 모달 닫기 (부트스트랩 모달 제어)
  const modal = bootstrap.Modal.getOrCreateInstance(document.getElementById('applicationModal'));
  modal.hide();
};

// 모달 열기용 예시 (프로젝트 제목 넘기기)
function openApplicationModal(title) {
  modalProjectTitle.textContent = title;
  const modal = new bootstrap.Modal(document.getElementById('applicationModal'));
  modal.show();
}