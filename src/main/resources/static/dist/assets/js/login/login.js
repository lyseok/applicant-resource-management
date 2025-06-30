// 회원 구분 비교값 치환
function trans_memType(a) {
  switch (a) {
    case "GG": return "M";
    case "GI": return "C";
    case "SF": return "S";
    case "HH": return "H";
    case "Edu": return "E";
    default: return "";
  }
}

// 로그인 실패 횟수에 따른 캡차 노출 여부
function isCapchaShowByLoginfail() {
  const el = document.querySelector("#Login_Stat");
  const v = el ? parseInt(el.value, 10) : 0;
  return v >= 3 && v <= 7;
}

// 시간 조건에 따른 캡차 사용 여부
function capchaCheckUseTime() {
  if (!isCapchaShowByLoginfail()) {
    const el = document.querySelector("#CapchaCheckUseTime");
    return el && el.value === "True";
  }
  return false;
}

// 캡차 보이기
function capchaShow() {
  const formWrite = document.querySelector("#loginFormWrite");
  if (formWrite) formWrite.classList.add("loginCaptcha");
  document.querySelectorAll(".devLoginImgTxt").forEach(el => el.classList.remove("devHide"));
}

// 캡차 숨기기
function capchaHide() {
  const formWrite = document.querySelector("#loginFormWrite");
  if (formWrite) formWrite.classList.remove("loginCaptcha");
  document.querySelectorAll(".devLoginImgTxt").forEach(el => el.classList.add("devHide"));
  const alarmMsg = document.querySelector(".alarmMsg");
  if (alarmMsg) alarmMsg.textContent = "";
}

// 로그인 처리
function login_send() {
  const form = document.form; // name="form" 이라는 가정
  const dbEl = document.querySelector("#DB_Name");
  let now_mtype = trans_memType(dbEl ? dbEl.value : "");

  const corpChk = document.querySelector("#btnCorpMemberType");
  if (now_mtype !== "M") {
    if (corpChk && corpChk.checked) {
      if (dbEl) dbEl.value = "SF";
    } else {
      if (dbEl) dbEl.value = "GI";
    }
  }

  const idInput = document.querySelector("#M_ID");
  if (!idInput || !idInput.value.trim()) {
    alert("아이디를 입력하세요.");
    idInput && idInput.focus();
    return false;
  }

  const pwdInput = document.querySelector("#M_PWD");
  if (!pwdInput || !pwdInput.value.trim()) {
    alert("비밀번호를 입력하세요.");
    pwdInput && pwdInput.focus();
    return false;
  }

  const loginStatEl = document.querySelector("#Login_Stat");
  const stat = loginStatEl ? parseInt(loginStatEl.value, 10) : 0;
  const gtxt = document.querySelector("#gtxt");
  if ((stat > 2 || capchaCheckUseTime()) && (!gtxt || !gtxt.value.trim())) {
    alert("그림 문자를 입력하세요.");
    gtxt && gtxt.focus();
    return false;
  }

  // re_url 처리
  if (form.re_url) {
    const re = form.re_url;
    if (re.value && !/^https?:\/\//.test(re.value)) {
      const host = document.querySelector("#returnHost");
      re.value = (host ? host.value : "") + re.value;
    }
  }

  // 회원 구분값 일치 여부 체크
  const mTypeEl = document.querySelector("#m_type");
  if (mTypeEl && mTypeEl.value && mTypeEl.value !== now_mtype) {
    form.re_url && (form.re_url.value = "");
  }
  if (mTypeEl && mTypeEl.value === "CHS" && !(now_mtype === "C" || now_mtype === "H" || now_mtype === "S")) {
    form.re_url && (form.re_url.value = "");
  }

  form.target = "_top";
  const hostEl = document.querySelector("#jkwww_host");
  form.action = (hostEl ? hostEl.value : "") + "/Login/Login.asp";

  return form.action.startsWith("https");
}

// 캡차 이미지 새로고침
function RefreshImage(valImageId) {
  const img = document.getElementById(valImageId);
  if (!img) return;
  const now = new Date();
  img.src = img.src.split("?")[0] + "?x=" + now.toUTCString();
}

// 기업회원 혜택소개 토글
function fChkIntroGI(mtype) {
  let isGI = false;
  const corpChk = document.querySelector("#btnCorpMemberType");
  if (mtype === "Co" && corpChk && !corpChk.checked) {
    isGI = true;
  }
  document.querySelectorAll(".text-balloon").forEach(el => {
    el.style.display = isGI ? "" : "none";
  });
  document.querySelectorAll(".section-company").forEach(el => {
    el.style.display = isGI ? "" : "none";
  });

  if (isGI && !isCapchaShowByLoginfail() && capchaCheckUseTime()) {
    const alarmMsg = document.querySelector(".alarmMsg");
    if (alarmMsg) alarmMsg.textContent = "안전한 로그인을 위해 그림 문자를 입력해 주세요.";
    capchaShow();
  }
}

// ID 입력 UI 세팅
function setInputUI(mtype) {
  const idInput = document.querySelector("#M_ID");
  if (!idInput) return;
  idInput.placeholder = "NO.1 잡코리아·알바몬 통합 ID";
  if (mtype === "GG") {
    idInput.classList.remove("corp");
  } else {
    idInput.classList.add("corp");
  }
}

// IP 보안 UI 세팅
function setIPUI() {
  const ipEl = document.querySelector("#IP_ONOFF");
  const btn = document.querySelector("#btnSecurity");
  if (!ipEl || !btn) return;
  if (ipEl.value === "N") {
    btn.classList.remove("on");
    btn.textContent = "OFF";
  } else {
    btn.classList.add("on");
    btn.textContent = "ON";
  }
}

// ─────────────────────────────────────────────────────────────────────────────

document.addEventListener("DOMContentLoaded", function() {
  // 회원 구분 라디오 클릭 처리
  const contentSpans = document.querySelectorAll("#memberCategory span");
  const radios        = document.querySelectorAll(".inpRadio");
  const groups        = document.querySelectorAll(".group");
  const tempDb        = document.querySelector("#temp_dbname");

  radios.forEach((radio, i) => {
    radio.addEventListener("click", () => {
      groups.forEach(g => g.classList.remove("on"));
      contentSpans[i] && contentSpans[i].classList.add("on");
      if (!tempDb) return;
      switch (i) {
        case 0: tempDb.value = "M"; break;
        case 1: tempDb.value = "C"; break;
        case 2: tempDb.value = "S"; break;
        case 3: tempDb.value = "H"; break;
        case 4: tempDb.value = "E"; break;
      }
    });
  });

  // 캡차 자동 노출
  const failShow = isCapchaShowByLoginfail();
  const timeUse  = capchaCheckUseTime();
  if (failShow || timeUse) {
    capchaShow();
    if (timeUse) {
      const alarmMsg = document.querySelector(".alarmMsg");
      if (alarmMsg) alarmMsg.textContent = "안전한 로그인을 위해 그림 문자를 입력해 주세요.";
    }
  }

  // 로그인 시 m_type 자동 세팅
  const mTypeEl = document.querySelector("#m_type");
  if (mTypeEl && mTypeEl.value) {
    const v = mTypeEl.value;
    groups.forEach(g => g.classList.remove("on"));
    radios.forEach(r => r.checked = false);

    if (v === "C" || v === "CHS") {
      tempDb && (tempDb.value = v === "CHS" ? "CHS" : "C");
      groups[1] && groups[1].classList.add("on");
      radios[1] && (radios[1].checked = true);
    } else if (v === "S") {
      tempDb && (tempDb.value = "S");
      groups[2] && groups[2].classList.add("on");
      radios[2] && (radios[2].checked = true);
    } else if (v === "H") {
      tempDb && (tempDb.value = "S");
      groups[3] && groups[3].classList.add("on");
      radios[3] && (radios[3].checked = true);
    } else if (v === "E") {
      tempDb && (tempDb.value = "E");
      groups[4] && groups[4].classList.add("on");
      radios[4] && (radios[4].checked = true);
    }
  }

  // 개발회원/일반회원 탭 클릭 처리
  const tab = document.querySelector("#devMemTab");
  if (tab) {
    tab.querySelectorAll("a").forEach(link => {
      link.addEventListener("click", function(e) {
        e.preventDefault();
        tab.querySelectorAll("li").forEach(li => li.classList.remove("on"));
        this.parentElement.classList.add("on");

        const type = this.getAttribute("data-m-type");
        const DBNameEl = document.querySelector("#DB_Name");
        const devCoTab = document.querySelector("#devCoTab");
        const devSNS   = document.querySelector("#devSNS");
        const btnIds   = ["btnFbLogin","btnNvLogin","btnGlLogin","btnKaLogin","btnApLogin"];
        const linkReg  = document.querySelector("#link_regist");
        const keepIds  = document.querySelectorAll(".keep-id");
        let mtype     = "";

        if (type === "Co") {
          DBNameEl   && (DBNameEl.value = "");
          devCoTab   && (devCoTab.style.display = "");
          btnIds.forEach(id => {
            const b = document.querySelector("#" + id);
            b && (b.style.display = "none");
          });
          linkReg    && (linkReg.href = linkReg.dataset.co);
          keepIds.forEach(el => el.style.display = "none");
          const cChk = document.querySelector("#btnCorpMemberType");
          mtype = cChk && cChk.checked ? "SF" : "GI";
        } else {
          DBNameEl   && (DBNameEl.value = "GG");
          devCoTab   && (devCoTab.style.display = "none");
          devSNS     && devSNS.classList.remove("devHide");
          btnIds.forEach(id => {
            const b = document.querySelector("#" + id);
            b && (b.style.display = "");
          });
          linkReg    && (linkReg.href = linkReg.dataset.gg);
          keepIds.forEach(el => el.style.display = "");
          mtype = "GG";
        }

        fChkIntroGI(type);
        setInputUI(mtype);
      });
    });
  }

  // 법인회원 체크박스 변경
  const corpR = document.querySelector("#btnCorpMemberType");
  if (corpR) {
    corpR.addEventListener("change", function() {
      if (!isCapchaShowByLoginfail()) {
        if (capchaCheckUseTime()) {
          const alarmMsg = document.querySelector(".alarmMsg");
          if (alarmMsg) alarmMsg.textContent = "안전한 로그인을 위해 그림 문자를 입력해 주세요.";
          capchaShow();
        } else {
          capchaHide();
        }
      }
      fChkIntroGI("Co");
      setInputUI(this.checked ? "SF" : "GI");
    });
  }

  // 로그인 유지 경고
  const lbSave = document.querySelector("#lb_idSave");
  if (lbSave) {
    lbSave.addEventListener("click", function() {
      if (this.checked) {
        if (!confirm(
          "개인정보 보호를 위해 본인 기기에서만 사용하세요.\n" +
          "이 기능을 이용함으로써 발생하는 보안 문제의 책임은 본인에게 있습니다."
        )) {
          this.checked = false;
        }
      }
    });
  }

  // 보안 팝업 열기
  document.querySelectorAll(".devSecPop").forEach(el => {
    el.addEventListener("click", () => {
      window.open("/Login/IP_Security_Pop", "popName", "width=510,height=395");
    });
  });
});
