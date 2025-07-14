/**
 * 
 */
document.addEventListener('DOMContentLoaded', () =>{
			 const examL = document.getElementById('examList');
			 const examCount = document.getElementById('examCount')
			 let examNo = "";
			axios.get('/ajax/company/company_exam/list')
				 .then(resp =>{
				    const examList = resp.data;
				    examCount.textContent = `총 ${examList.length}건의 시험이 등록되어 있습니다.`;
				    console.log(resp.data);
				   
				   let html = '';
				    examList.forEach((exam, idx) =>{
							 html = `
						         	 <div class="exam-card d-flex align-items-center mb-3 p-3 border rounded" data-exam-id="${exam.comExamNo}">
								            <div class="exam-name">${idx + 1}. ${exam.comExamName}</div>
											<div class="exam-date">등록일시-${exam.comExamCreateDate}</div>
					
											<button
											     type="button"
											     class="btn btn-danger btn-sm ms-2 btn-open-delete-modal"
											     data-bs-toggle="modal"
											     data-bs-target="#deleteExamModal"
											     data-exam-id= "${exam.comExamNo}"
											   >
											   삭제 
											 </button>
								         </div>`;
							examL.innerHTML += html;
							
							document.querySelectorAll('.btn-open-delete-modal').forEach(btn => {
					        btn.addEventListener('click', event => {
					          examNo = event.currentTarget.dataset.examId;
							  console.log("체킁 ",examNo)
						    	});
					   		});
					   		
					   				
							document.querySelectorAll('.exam-card').forEach( card =>{
								card.addEventListener('click', event =>{
									 if (event.target.closest('.btn-open-delete-modal')) return;
									 const examNo = card.dataset.examId;
									 window.location.href = `/company/company_exam/detail/${examNo}`;
								})
							})
					})
					
					
			})
			.catch(err => {
				console.log(err);
				document.getElementById('examCount').textContent = '시험 목록을 불러오는 중 오류가 발생했습니다.'
			})	
			
			
			
			
	
		  document.getElementById('confirmDeleteBtn').addEventListener('click', () => {
			console.log("체킁 ",examNo)
			if(!examNo){
				return alert('삭제할 시험을 선택해 주세요!');
			}
		    axios.get(`/ajax/company/company_exam/delete/${examNo}`)
		      .then(resp => {
					const result = resp.data
					console.log("체킁", result);
					  const modalEl = document.getElementById('deleteExamModal');
    				  bootstrap.Modal.getOrCreateInstance(modalEl).hide();
					
					alert('시험이 성공적으로 삭제되었습니다.');
					window.location.reload();
					
				 })
				 .catch(err =>{
					console.error("에러 체킁 " ,err);
				 })
			})
		
})
		
		