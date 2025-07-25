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
const password2 = document.getElementById('password2');
const msgError = document.getElementById('msg_password_match');
const msgGood = document.getElementById('msg_password_good');
const validMsg = document.getElementById("password1_good_txt");
const warningMsg = document.getElementById("password1_warning_txt");

const brInput = document.getElementById("brNumber");
const msg = document.getElementById("msg_corp_code");
const companyArea = document.getElementById("area_input_company");

const select = document.getElementById('comCreateYear');
const thisYear = new Date().getFullYear();

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

// 업종 레이어
const industryGroups = document.querySelector('.industry-groups');
const btnSave = document.querySelector('.btn_save');
const btnReset = document.querySelector('.btn_reset');
const btnClose = document.querySelectorAll('.btn_close');
const industryCodeInput = document.getElementById('industry_code');
const industryKeywordInput = document.getElementById('industry_keyword');
const selectedArea = document.getElementById('industry_selected_area');
const searchInput = document.getElementById('search_industry');

const openBtn = document.querySelector('.btn_job_category');
const layer = document.getElementById('layer_desire_industry');
const closeBtns = layer.querySelectorAll('.btn_close, .btn_layer_close');

let selectedIndustry = null;
let industryData = [];

const serviceKey = "%2BJC5tK7N7UE0GM%2FpoYcJBHIIb%2FuzMV33idwD93rG%2BSZGxM5fQNNsVsnEKSeA8HatKJ%2FicIZeQWsUnUE2E65CmA%3D%3D";

brInput.addEventListener("blur", async () => {
    const brNumber = brInput.value.trim().replace(/-/g, ''); // 하이픈 제거
    if (brNumber.length !== 10) {
        msg.textContent = "사업자등록번호 10자리를 정확히 입력해주세요.";
        msg.style.color = "red";
        companyArea.style.display = "none";
        return;
    }

    try {
        // 1) 외부 API 유효성 체크
        const isValid = await checkBizNumber(brNumber);
        if (!isValid) {
            msg.textContent = "❌ 유효하지 않은 사업자등록번호입니다.";
            msg.style.color = "red";
            companyArea.style.display = "none";
            return;
        }

        // 2) 내부 중복 체크
        const resp = await axios.get(`/ajax/${brNumber}`);
        const data = resp.data;

        if (data.includes("이미 가입")) {
            msg.textContent = data;
            msg.style.color = "red";
            companyArea.style.display = "none";
        } else {
            msg.textContent = data;
            msg.style.color = "green";
            companyArea.style.display = "block";
        }
    } catch (err) {
        console.error(err);
        msg.textContent = "검증 중 오류가 발생했습니다.";
        msg.style.color = "red";
        companyArea.style.display = "none";
    }
});

// 외부 API 호출
function checkBizNumber(b_no) {
    const payload = { b_no: [b_no] };
    return fetch(`https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=${serviceKey}`, {
        method: "POST",
        headers: { "Content-Type": "application/json", "Accept": "application/json" },
        body: JSON.stringify(payload)
    })
        .then(res => res.json())
        .then(data => data?.data?.[0]?.b_stt === "계속사업자")
        .catch(() => false);
}

// 모달 열기
openBtn.addEventListener('click', () => {
	layer.style.display = 'block';
});

// 닫기 버튼 (취소 / 닫기)
closeBtns.forEach(btn => btn.addEventListener('click', () => {
	layer.style.display = 'none';
}));

// 1. 데이터 불러오기
fetch('/ajax/industry')
	.then(res => res.json())
	.then(data => {
		industryData = transformData(data);
		renderIndustryList(industryData);
	})
	.catch(err => console.error(err));

// 평면 데이터를 Depth1→Depth2 계층 구조로 변환
function transformData(flatData) {
	const grouped = {};
	flatData.forEach(row => {
		if (!grouped[row.INDUCLASSCODE]) {
			grouped[row.INDUCLASSCODE] = {
				induClassNo: row.INDUCLASSCODE,
				induClassName: row.INDUCLASSNAME,
				induList: []
			};
		}
		grouped[row.INDUCLASSCODE].induList.push({
			induNo: row.INDUNO,
			induName: row.INDUNAME
		});
	});
	return Object.values(grouped);
}

// 2. 렌더링 (라디오 버튼)
function renderIndustryList(data) {
	industryGroups.innerHTML = '';

	data.forEach(depth1 => {
		const section = document.createElement('div');
		section.classList.add('industry-section');

		section.innerHTML = `
                <h5 class="industry-title">${depth1.induClassName}</h5>
                <div class="industry-options">
                    ${depth1.induList.map(d2 => `
                        <label class="industry-option">
                            <input type="radio" name="industry" value="${d2.induNo}" data-name="${d2.induName}">
                            <span>${d2.induName}</span>
                        </label>
                    `).join('')}
                </div>
            `;
		industryGroups.appendChild(section);

		// 라디오 버튼 클릭 → 선택
		section.querySelectorAll('input[type="radio"]').forEach(radio => {
			radio.addEventListener('change', () => {
				selectedIndustry = { code: radio.value, name: radio.dataset.name };
			});
		});
	});
}

// 3. 검색 (Depth2 필터링)
searchInput.addEventListener('input', () => {
	const keyword = searchInput.value.trim().toLowerCase();
	if (keyword === '') {
		renderIndustryList(industryData);
		return;
	}
	const filtered = industryData.map(d1 => ({
		...d1,
		induList: d1.induList.filter(d2 => d2.induName.toLowerCase().includes(keyword))
	})).filter(d1 => d1.induList.length > 0);
	renderIndustryList(filtered);
});

// 4. 완료
btnSave.addEventListener('click', () => {
	if (!selectedIndustry) {
		alert('업종을 선택하세요.');
		return;
	}
	industryCodeInput.value = selectedIndustry.code;
	selectedArea.innerHTML = `<span>${selectedIndustry.name}</span>`;
	document.getElementById('layer_desire_industry').style.display = 'none';
});

// 5. 초기화
btnReset.addEventListener('click', () => {
	selectedIndustry = null;
	industryCodeInput.value = '';
	selectedArea.innerHTML = '<span class="form_placeholder" aria-hidden="true">업종선택</span>';
	document.querySelectorAll('input[name="industry"]').forEach(r => r.checked = false);
});

// 6. 닫기
btnClose.forEach(btn => btn.addEventListener('click', () => {
	document.getElementById('layer_desire_industry').style.display = 'none';
}));

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

for (let y = thisYear; y >= 1900; y--) {
	const opt = document.createElement('option');
	opt.value = y;
	opt.textContent = y;
	select.appendChild(opt);
}

function checkPasswordMatch() {
	if (!password2.value) {
		msgError.style.display = 'none';
		msgGood.style.display = 'none';
		return;
	}
	if (passwordInput.value === password2.value) {
		msgError.style.display = 'none';
		msgGood.style.display = 'block';
	} else {
		msgError.style.display = 'block';
		msgGood.style.display = 'none';
	}
}

passwordInput.addEventListener('input', checkPasswordMatch);
password2.addEventListener('input', checkPasswordMatch);

sendBtn.addEventListener("click", requestCode);
resendBtn.addEventListener("click", requestCode);
verifyBtn.addEventListener("click", verifyCode);