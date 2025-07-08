/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {
	const optionsMap = {
		"UFAQ": {
			"UFAQ-U1": "이력서등록/관리",
			"UFAQ-U2": "회원정보/아이디/비밀번호",
			"UFAQ-U3": "입사지원/관리",
			"UFAQ-U4": "채용정보 검색/관리",
			"UFAQ-U5": "회원가입/탈퇴",
			"UFAQ-U6": "추천/나의검색",
			"UFAQ-U7": "계정 통합",
			"UFAQ-U8": "기타회원 서비스",
			"UFAQ-U9": "KoMate"
		},
		"CFAQ": {
			"CFAQ-C1": "채용정보 등록/관리",
			"CFAQ-C2": "유료서비스/결제",
			"CFAQ-C3": "인재풀",
			"CFAQ-C4": "회원,기업정보/아이디/비밀번호",
			"CFAQ-C5": "회원가입/탈퇴",
			"CFAQ-C6": "지원자 관리/면접관리",
			"CFAQ-C7": "인적성검사",
			"CFAQ-C8": "채용홈페이지",
			"CFAQ-C9": "계정통합",
			"CFAQ-C10": "기타회원서비스",
			"CFAQ-C11": "KoMate"
		}
	};

	const boardTypeSelect = document.getElementById('boardTypeCode');
	const faqTypeSelect = document.getElementById('faqType');
	const faqTypeWrapper = document.getElementById('faqTypeWrapper');

	const faqSubTypeSelect = document.getElementById('faqSubType');
	const faqSubTypeWrapper = document.getElementById('faqSubTypeWrapper');

	// 1차 선택 시 FAQ 유형 드러내기
	boardTypeSelect.addEventListener('change', function() {
		faqTypeSelect.value = "";
		faqSubTypeSelect.innerHTML = '<option value="">-- 선택하세요 --</option>';
		faqSubTypeWrapper.style.display = 'none';

		if (this.value === 'BRDD-002') {
			faqTypeWrapper.style.display = 'block';
		} else {
			faqTypeWrapper.style.display = 'none';
		}
	});

	// 2차 선택 시 상세 FAQ 드러내기
	faqTypeSelect.addEventListener('change', function() {
		const selected = this.value;
		faqSubTypeSelect.innerHTML = '<option value="">-- 선택하세요 --</option>';
		faqSubTypeWrapper.style.display = 'none';

		if (optionsMap[selected]) {
			Object.entries(optionsMap[selected]).forEach(([value, label]) => {
				const option = document.createElement('option');
				option.value = value;
				option.textContent = label;
				faqSubTypeSelect.appendChild(option);
			});
			faqSubTypeWrapper.style.display = 'block';
		}
	});
});

