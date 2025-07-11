/**
 * 
 */
aboardForm.onsubmit = async (e) => {
	e.preventDefault();

	let response = await fetch('/ajax/admin/board/admin_board/${boardTypeCode}', {
		method: 'POST',
		body: new FormData(aboardForm)
	});

	let result = await response.json();

	alert(result.message);
};