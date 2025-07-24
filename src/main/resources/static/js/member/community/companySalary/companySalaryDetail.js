/**
 * 
 */
document.addEventListener('DOMContentLoaded', ()=>{
	 const urlParams = new URLSearchParams(window.location.search);
  	 const companyId = urlParams.get('companyId');
     console.log("선택된 USER_ID:", companyId);
})                                        