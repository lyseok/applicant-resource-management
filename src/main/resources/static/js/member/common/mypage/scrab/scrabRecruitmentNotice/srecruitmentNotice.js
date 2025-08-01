function deleteRecruit(recruitmentNo) {
    if (!confirm("정말 삭제하시겠습니까?")) return;

    fetch(`/ajax/member/scrabRecruitment/${recruitmentNo}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            alert("삭제되었습니다.");
            location.reload(); // 새로고침
        } else {
            alert("삭제 실패. 관리자에게 문의하세요.");
        }
    })
    .catch(err => console.error(err));
}
