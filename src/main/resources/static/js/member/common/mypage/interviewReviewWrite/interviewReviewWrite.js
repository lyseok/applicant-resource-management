/**
 * 
 */

 document.addEventListener('DOMContentLoaded', () => {
	const interviewNo = new URLSearchParams(window.location.search).get('intr_interview_no');
	console.log("체킁", interviewNo);

	
	
	
	axios.get(`/ajax/member/interview/review/${interviewNo}`)
		 .then(resp => {
			const interviewInfo = resp.data;
			console.log(interviewInfo);


			document.getElementById('companyName').textContent = interviewInfo.co
		 })
 });