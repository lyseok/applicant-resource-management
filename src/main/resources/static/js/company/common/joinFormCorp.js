/**
 * 
 */
const id = document.getElementById('id');
const duplicationBtn = document.getElementById('id_duplicate');
const focusMsg = document.getElementById("idFocusMsg");  // 안내 문구
const msgInvalid = document.getElementById('idCheckMsg1');
const msgValid = document.getElementById('idCheckMsg2');
const signupBtn = document.getElementById('btn_submit');

// 비밀번호 검증
const passwordInput = document.getElementById("password1");
const validMsg = document.getElementById("password1_good_txt");
const warningMsg = document.getElementById("password1_warning_txt");

const brInput = document.getElementById("brNumber");
const msg = document.getElementById("msg_corp_code");
const companyArea = document.getElementById("area_input_company");

const serviceKey = "%2BJC5tK7N7UE0GM%2FpoYcJBHIIb%2FuzMV33idwD93rG%2BSZGxM5fQNNsVsnEKSeA8HatKJ%2FicIZeQWsUnUE2E65CmA%3D%3D";

brInput.addEventListener("input", function() {
	let val = brInput.value.replace(/\D/g, ""); // 숫자만

	// 하이픈 자동 추가
	if (val.length <= 3) {
		brInput.value = val;
	} else if (val.length <= 5) {
		brInput.value = `${val.slice(0, 3)}-${val.slice(3)}`;
	} else if (val.length <= 10) {
		brInput.value = `${val.slice(0, 3)}-${val.slice(3, 5)}-${val.slice(5)}`;
	}

	// 조건에 따라 메시지 표시
	if (val.length === 10) {
		msg.innerText = "";
		checkBizNumber(val);
	} else {
		msg.innerText = "사업자등록번호 10자리를 정확히 입력해 주세요.";
		msg.style.color = "red";
		companyArea.style.display = "none";
	}
});

function checkBizNumber(b_no) {
	const payload = { b_no: [b_no] };

	fetch(`https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=${serviceKey}`, {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			"Accept": "application/json",
		},
		body: JSON.stringify(payload)
	})
		.then(res => res.json())
		.then(data => {
			const result = data?.data?.[0];
			if (result?.b_stt === "계속사업자") {
				msg.innerText = "✅ 유효한 사업자번호입니다.";
				msg.style.color = "green";
				companyArea.style.display = "block";
			} else {
				msg.innerText = "❌ 유효하지 않은 사업자번호입니다.";
				msg.style.color = "red";
				companyArea.style.display = "none";
			}
		})
		.catch(err => {
			msg.innerText = "❗ API 요청 실패";
			msg.style.color = "red";
			console.error("사업자번호 검증 실패", err);
			companyArea.style.display = "none";
		});
}

id.addEventListener("input", () => {
	const value = id.value.trim();
	const isValidFormat = /^[a-zA-Z0-9_]{4,20}$/.test(value);

	// 모두 숨기고 시작
	focusMsg.style.display = "none";
	msgValid.style.display = "none";
	msgInvalid.style.display = "none";

	// 입력 없으면 그냥 비워둠
	if (value === "") return;

	if (!isValidFormat) {
		// 형식이 틀렸을 때만 안내 문구 보여줌
		focusMsg.style.display = "block";
		duplicationBtn.disabled = true;
	} else {
		duplicationBtn.disabled = false;
	}
});

duplicationBtn.addEventListener("click", async () => {
	const idValue = id.value.trim();

	if (!idValue) {
		alert("아이디를 입력해주세요!");
		return;
	}

	const resp = await axios.get(`ajax/user/${idValue}`);
	const msg = resp.data;

	if (msg.includes("중복")) {
		msgInvalid.style.display = 'block';
		msgValid.style.display = 'none';
		signupBtn.disabled = true;
	} else if (msg.includes("사용 가능")) {
		msgInvalid.style.display = 'none';
		msgValid.style.display = 'block';
	}

	console.log(msg);
})

passwordInput.addEventListener("input", () => {
	const value = passwordInput.value;
	const patterns = [
		/[A-Z]/,       // 대문자
		/[a-z]/,       // 소문자
		/[0-9]/,       // 숫자
		/[^A-Za-z0-9]/ // 특수문자
	];

	const matchCount = patterns.filter(p => p.test(value)).length;

	if (value.length >= 8 && value.length <= 16 && matchCount >= 3) {
		validMsg.textContent = "안전한 비밀번호입니다.";
		validMsg.style.display = "block";
		warningMsg.style.display = "none";
	} else {
		warningMsg.style.display = "block";
		validMsg.style.display = "none";
	}
});

document.getElementById("btn_add1_search").addEventListener("click", function() {
	new daum.Postcode({
		oncomplete: function(data) {
			const addr = data.userSelectedType === 'R' ? data.roadAddress : data.jibunAddress;
			document.getElementById("comAddr1").value = addr;
			document.getElementById("comAddr2").focus(); // 상세주소로 포커스 이동
		}
	}).open();
});

function validateEmail(email) {
	return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

function requestCode() {
	const email = emailInput.value.trim();

	if (!validateEmail(email)) {
		emailMsg.style.display = "block";
		return;
	}
	emailMsg.style.display = "none";

	confirmWrap.style.display = "block";
	verifyBtn.disabled = false;

	sendBtn.style.display = "none";
	resendBtn.style.display = "inline-block";

	successMsg.style.display = "none";
	errorMsg.style.display = "none";
	startTimer();

	fetch("/email/send", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify({ email })
	})
		.then(res => res.json())
		.then(data => {
			if (data.success) {
			} else {
				alert(data.message || "인증 요청 실패");
			}
		})
		.catch(() => {
			alert("서버 오류가 발생했습니다.");
		});
}

function verifyCode() {
	const email = emailInput.value.trim();
	const code = codeInput.value.trim();

	fetch("/email/verify", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify({ email, code })
	})
		.then(res => res.json())
		.then(data => {
			console.log("data", data);
			if (data.success) {
				successMsg.style.display = "block";
				errorMsg.style.display = "none";
				verifyBtn.disabled = true;
				resendBtn.disabled = true;
				clearInterval(timer);
				document.querySelector("#mail_confirm_complete").value = "y";

				confirmWrap.style.display = "none";

				signupBtn.disabled = false;
			} else {
				errorMsg.style.display = "block";
				successMsg.style.display = "none";
			}
		})
		.catch(() => {
			alert("인증 실패. 다시 시도해주세요.");
		});
}