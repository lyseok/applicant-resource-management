<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
  <meta charset="UTF-8">
  <title>프로젝트 공고 상세</title>
  <style>
    .dot {
      width: 6px;
      height: 6px;
      background: #bbb;
      border-radius: 50%;
      display: inline-block;
      margin-right: 12px;
    }
    .badge-tag {
	    background: var(--violet10);
	    padding: 5px 13px;
	    border-radius: 15px;
	    border: 1px solid var(--violet30);
	    font-weight: 500;
	    font-size: 12px;
	    color: var(--violet70);
		}


    .list-dot li {
      display: flex;
      align-items: center;
      gap: 10px;
    }

    .list-dot .dot {
      margin: 0;
    }
    
    .profile img{
    	max-width:32px;
    	max-height:32px;
    	object-fit:cover;    	
    	border-radius:50%;
    }
  </style>
  <script defer src="/js/member/project/noticeManagement/noticeDetail.js"></script>
</head>

<body style="background:#fafafc;">

  <div class="card-body px-5 pt-4">

  </div>

  <div class="d-flex justify-content-end gap-2 mb-4 px-5">
    <a href="/mypage/notice_management" class="btn btn_gray_line">목록</a>
    <button id="btnEditNotice" class="btn btn_violet px-5 py-2">수정</button>
  </div>

</body>