const interviewListEl = document.querySelector('#interviewList');
const listSizeEl = document.querySelector('#list-size');
const editInterviewConfirmBtnEl = document.querySelector(
  '#edit-interview-confirm-btn'
);

interviewListEl.addEventListener('click', (event) => {
  if (
    event.target
      .closest('li.PostList_postItem')
      .classList.contains('saveInterview')
  ) {
    const postItem = event.target.closest('li.PostList_postItem');
    if (postItem) {
      let selectedInterviewNo = postItem.getAttribute('data-inte-no');
      location.href =
        '/company/interview/detail?interviewNo=' + selectedInterviewNo;
    }
  }
});

const getInterviewList = async () => {
  const { status, data } = await axios.get('/ajax/company/interview');

  if (status == 200) {
    let html = ``;

    listSizeEl.innerHTML = data.length;
    data.forEach((inte) => {
      console.log(inte);
      const reProcess = inte.recruitProcess;
      const reNotice = reProcess.recruitmentNotice;

      html += /* html */ `
				<li class="PostList_postItem saveInterview cursor-pointer" data-inte-no='${inte.interviewNo}'>
					<div class="border-bottom p-4">
						<div class="PostList_post">
							<div class="ListItem_post">
								<div class="ListItem_post_item">
									<h3 class="h4 fw-bold mb-0">${reNotice.recruitmentTitle}</h3>
									<!-- <h3 class="h5">${reNotice.recContent}</h3> -->
								</div>
							</div>
							<div class="ListItem_info">
								<div class="ListItem_profile">
									<span class="bg-violet08 px-3 text-white fw-500 fs-14">면접일시</span>
									<span class="text-muted">${inte.interviewDate}</span>
								</div>
								<div class="ListItem_profile_postInfoList">
									<div class="d-flex gap-2">
										<div class="ListItem_profile_postInfoItem">
											<span>단계</span><strong class="text-violet80">${reProcess.recruitProcessStep}</strong>
										</div>
										<div class="ListItem_profile_divider"></div>
										<div class="ListItem_profile_postInfoItem">
											<span>채용인원</span><strong class="text-violet80">${reNotice.recPositionNumber}</strong>
										</div>
										<div class="ListItem_profile_divider"></div>
										<div class="ListItem_profile_postInfoItem">
											<span>면접인원</span><strong class="text-violet80">${reProcess.applicantRecordList.length}</strong>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</li>
			`;
    });
    interviewListEl.innerHTML = html;
  }
};

getInterviewList();
