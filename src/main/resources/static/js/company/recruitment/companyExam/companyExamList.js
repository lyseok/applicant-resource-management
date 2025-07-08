/**
 * 
 */
document.addEventListener('DOMContentLoaded', () =>{
			 const examL = document.getElementById('examList');
			 const examCount = document.getElementById('examCount')
			 let examToDelete = "";
			axios.get('/ajax/company/companyExam/list')
				 .then(resp =>{
				    const examList = resp.data;
				    console.log(resp.data);
				   
				    examList.forEach((exam, idx) =>{
							const list = `
						         		<div class="exam-card">
								            <div class="exam-name">${idx + 1}. ${exam.comExamName}</div>
											
											<button
											     type="button"
											     class="btn btn-danger btn-sm ms-2 btn-open-delete-modal"
											     data-bs-toggle="modal"
											     data-bs-target="#deleteExamModal"
											     data-exam-id="${exam.comExamNo}"
											   >
											   삭제 
								         </div>`;
							examL.innerHTML += list;
					})
					examCount.textContent = `총 ${examList.length}건의 시험이 등록되어 있습니다.`;
					
			})
			.catch(err => {
				console.log(err);
				document.getElementById('examCount').textContetn = '시험 목록을 불러오는 중 오류가 발생했습니다.'
			})	
			
			
			document.querySelectorAll('.btn-open-delete-modal').forEach(btn => {
			        btn.addEventListener('click', () => {
			          examToDelete = btn.getAttribute('data-exam-id');
					  console.log("체킁 ",examToDelete)
			    });
		   });
			
	
		  document.getElementById('confirmDeleteBtn').addEventListener('click', () => {
			console.log("체킁 ",examToDelete)
		    axios.delete(`/ajax/company/companyExam/delete/${examToDelete}`)
		      .then(() => {
					const result = resp.data
					console.log("체킁", result);
					
				 })
				 .catch(err =>{
					console.error("에러 체킁 " ,err);
				 })
					
				
			})
	
		})