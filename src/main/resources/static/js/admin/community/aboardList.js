/**
 * 
 */

//전역변수 바깥에 선언
let boardType; 

document.addEventListener("DOMContentLoaded", function() {
	
	//삼항연산자로, 해당 boardTypeCode가 있으면 선택, 선택되지 않았으면 기본값(faq)
	//해당 boardTypeCode에서 개인/기업 탭 분리, 선택되지 않았으면 기본값(개인)
		//로그인 유저의 경우 해당 유저 타입에 따라 다른 탭 나오게 할수있나? 추가사항.
	//boardTypeCode가 BRDD-002면 UFAQ, CFAQ 선택, 선택되지 않았으면 기본값(전체)
	
	//찾아오려는 데이터를 const로 미리 정의(.이 여러개면 찾아가기 힘드니까)
	
	boardType = document.querySelector('#aboardType-Select').href
	boardType.add
	
	typeCodes = document.querySelectorAll('#aboardTypeCode span');
	faqTypes = document.querySelectorAll('#faqTypeCode span');
	
	// const aboardTypeCode = document.getElementById('aboardTypeCode');  // 짧은, 샘플 소스 만들때만
	document.querySelector('#aboardTypeCode').addEventListener("click", ()=>{  //그외는 다 querySelector(All) 쓰기!
		//화면상 이벤트가 click인지, change인지 보고 선택
		typeCodes.forEach
	})	
});