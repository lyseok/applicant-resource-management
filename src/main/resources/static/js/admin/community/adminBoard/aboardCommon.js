/**
 * 
 */
function renderBoardList(targetElement, boardList) {
  let html = "";

  if (!boardList || boardList.length === 0) {
    targetElement.innerHTML = "<li>게시글이 없습니다.</li>";
    return;
  }

  boardList.forEach(item => {
    html += `
      <li>
        <strong>[${item.boardTypeCode}]</strong>
        <a href="/admin/board/admin_board/detail?no=${item.boardNo}&type=${item.boardTypeCode}">
          ${item.boardTitle}
        </a>
        <div>${item.boardWriteDate}</div>
      </li>`;
  });

  targetElement.innerHTML = html;
}
