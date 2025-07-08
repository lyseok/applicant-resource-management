const interviewListEl = document.querySelector("#interviewList");

const getInterviewList = async () => {
	const {status, data} = await axios.get("/ajax/company/interview");
	
	if(status == 200){
		let html = ``;

		data.forEach(inte => {
			const reProcess = inte.recruitProcess;
			const reNotice = reProcess.recruitmentNotice;

			html += /* html */ `
				<li class="PostList_postItem">
					<div class="PostList_link ">
						<div class="PostList_post">
							<div class="ListItem_post">
								<div class="ListItem_post_item">
									<h3 class="h4 fw-bold">${reNotice.recruitmentTitle}</h3>
									<h3 class="h5">${reNotice.recContent}</h3>
								</div>
							</div>
							<div class="ListItem_info">
								<div class="ListItem_profile">
									<span class="fw-bold">면접일시</span>
									<span>${inte.interviewDate}</span>
								</div>
								<div class="ListItem_profile_postInfoList">
									<div class="d-flex gap-2">
										<div class="ListItem_profile_postInfoItem">
											<span>단계</span><strong>${reProcess.recruitProcessStep}</strong>
										</div>
										<div class="ListItem_profile_divider"></div>
										<div class="ListItem_profile_postInfoItem">
											<span>채용인원</span><strong>${reNotice.recPositionNumber}</strong>
										</div>
										<div class="ListItem_profile_divider"></div>
										<div class="ListItem_profile_postInfoItem">
											<span>면접인원</span><strong>${reProcess.applicantRecordList.length}</strong>
										</div>
									</div>
								</div>
							</div>
							<div class="d-flex gap-2 justify-content-end w-100 mt-3">
								<div class='btn btn_gray_line'>수정</div>
								<div class='btn btn_red_line'>삭제</div>
							</div>
						</div>
					</div>
				</li>
			`;
		});
		interviewListEl.innerHTML = html;
	}
	
}


getInterviewList();