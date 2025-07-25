/**
 * 
 */

document.addEventListener("DOMContentLoaded", () => {
	// 아이디 검증 및 중복 검사
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

	// 이메일 검증 및 인증
	const sendBtn = document.querySelector("#mail_send_code");
	const resendBtn = document.querySelector("#mail_re_send_code");
	const verifyBtn = document.querySelector(".confirm-action");
	const emailInput = document.querySelector("#mail_email_id");
	const codeInput = document.querySelector("#email_code");
	const confirmWrap = document.querySelector("#mail_confirm_wrap");
	const successMsg = document.querySelector("#mail_msg_good");
	const errorMsg = document.querySelector("#email_confirm_msg");
	const emailMsg = document.querySelector("#mail_msg_email1");

	let timer;
	let countdown = 180;

	const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-z]{2,}$/;

	signupBtn.disabled = true;

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
		}else{
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
				document.getElementById("memAdd1").value = addr;
				document.getElementById("memAdd2").focus(); // 상세주소로 포커스 이동
			}
		}).open();
	});

	function startTimer() {
		countdown = 180;
		const remainArea = document.querySelector("#confirm_remain_mail_time_area");
		remainArea.style.display = "block";

		timer = setInterval(() => {
			let minutes = String(Math.floor(countdown / 60)).padStart(2, "0");
			let seconds = String(countdown % 60).padStart(2, "0");
			remainArea.innerHTML = `<span>남은 시간 ${minutes}:${seconds}</span>`;

			if (countdown-- <= 0) {
				clearInterval(timer);
				remainArea.innerHTML = "<span>인증 시간이 만료되었습니다.</span>";
				verifyBtn.disabled = true;
			}
		}, 1000);
	}

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
	console.log(confirmWrap)

	sendBtn.addEventListener("click", requestCode);
	resendBtn.addEventListener("click", requestCode);
	verifyBtn.addEventListener("click", verifyCode);
})