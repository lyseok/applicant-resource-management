/* 삭제버튼 클릭 시 삭제 */
function confirmDelete() {
    // 사용자에게 확인 메시지를 띄웁니다.
    // '확인'을 누르면 true, '취소'를 누르면 false를 반환합니다.
    if (confirm('삭제하시겠습니까?')) {
        return true; // 사용자가 '확인'을 눌렀으므로 삭제 진행
    } else {
		alert("삭제 취소되었습니다.")
        return false; // 사용자가 '취소'를 눌렀으므로 삭제 취소
    }
}


/*document.addEventListener("DOMContentLoaded", ()=>{
	const editBtn = document.querySelector(".edit_btn");
	editBtn.addEventListener("click", (e)=>{
		e.preventDefault();
	})
	console.log(editBtn);
})*/