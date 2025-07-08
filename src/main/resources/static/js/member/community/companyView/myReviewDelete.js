/**
 * 
 */

document.addEventListener('DOMContentLoaded', ()=> {
   	document.querySelectorAll('.delete-btn').forEach(btn => {
		btn.addEventListener('click', () => {
			const reviewNo = btn.getAttribute('data-review-no');
			 if (!confirm('삭제 하시겠습니까?')) return;
			   			
			axios.get(`/ajax/member/companyReview/myReviewDelete/${reviewNo}`)
				  .then(resp => {
						if(resp.data === 'success'){
							btn.textContent = '삭제요청성공!';
							btn.disabled = true;
							btn.classList.remove('delete-btn');
							btn.classList.add('btn-secondary');
						}else{
							alert('삭제 요청 실패!')
						}
				  })
				  .catch(error =>{
					console.error(error);
					alert('error');
				  });
			});
   		});
   	
   	}); 
   	