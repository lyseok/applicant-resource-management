
    // 폼 토글 함수: 하나만 열리게
    function toggleForm(key, closeOnly = false) {
      const ids = [
        'edu','career','skill','exp','cert','portfolio','selfintro','prefer'
      ];
      ids.forEach(id => {
        const form = document.getElementById('form-' + id);
        const secCon = form.closest('#section-' + id).querySelector(".section-content");
        if (!form) return;
        if (id === key && !closeOnly) {
        	form.classList.add('active');
        	secCon.classList.add("d-none");
        }
        else{
			form.classList.remove('active');    	
        	secCon.classList.remove("d-none");
		} 
      });
      // 스크롤 자연스럽게 이동 (폼이 열릴 때만)
      if (!closeOnly) {
        setTimeout(() => {
          document.getElementById('form-'+key).scrollIntoView({behavior:'smooth',block:'center'});
        }, 80);
      }
    }
    
    
    // 임시 폼 submit
    function submitForm(e, key) {
      e.preventDefault();
      alert("입력하신 값이 임시로 저장되었습니다!");
      toggleForm(key, true);
    }