function startSpellCheck() {
	const textarea = document.getElementById("textInput");
	const resultBox = document.getElementById("spellResult");
	const text = textarea.value.trim();

	if (!text) {
		alert("검사할 문장을 입력하세요.");
		return;
	}

	fetch("/spell/check", {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded" },
		body: new URLSearchParams({ text })
	})
	.then(res => res.json())
	.then(data => {
		const wrongWords = Object.entries(data)
			.filter(([_, isCorrect]) => isCorrect === false)
			.map(([word], index) => `잘못된 부분 ${index + 1}번째 → '${word}'`);

		resultBox.innerHTML = wrongWords.length > 0
			? `<pre>${wrongWords.join('\n')}</pre>`
			: "<span style='color:gray;'>오타가 없습니다.</span>";
	})
	.catch(() => {
		resultBox.innerHTML = "<span style='color:red;'>오류 발생</span>";
	});
}


// 선택/복사 기능은 필요 시 유지
function copySpell() {
	const textarea = document.getElementById("textInput");
	if (!textarea.value.trim()) {
		alert("복사할 내용이 없습니다.");
		return;
	}
	textarea.select();
	document.execCommand("copy");
	alert("복사 완료");
}

function resetSpell() {
	document.getElementById("textInput").value = "";
	document.getElementById("spellResult").innerHTML = "";
}
