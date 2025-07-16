<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
  <meta charset="UTF-8">
  <title>프로젝트 공고 상세</title>
  <style>
    .dot {
      width: 8px;
      height: 8px;
      background: #bbb;
      border-radius: 50%;
      display: inline-block;
      margin-right: 12px;
    }

    .section-title {
      font-size: 1.17rem;
      font-weight: 600;
      margin-bottom: 1rem;
    }

    .badge-recruit {
      background: #22c55e;
    }

    .shadow-card {
      box-shadow: 0 4px 32px 0 rgba(0, 0, 0, 0.09);
    }

    .list-dot li {
      display: flex;
      align-items: center;
      gap: 10px;
    }

    .list-dot .dot {
      margin: 0;
    }

    .btn-purple {
      background: #9333ea;
      color: #fff;
    }

    .btn-purple:hover {
      background: #7c2dd6;
      color: #fff;
    }

    .text-purple {
      color: #ae8be1 !important;
    }

    .bg-purple {
      background: #ae8be1 !important;
    }

    .selected-card {
      border: 2px solid #ae8be1 !important;
      background: #f6f2ff !important;
    }

    .resume-card {
      cursor: pointer;
      border: 1.5px solid #eee;
    }

    .resume-card:hover {
      border-color: #ae8be1 !important;
    }
  </style>
  <script defer src="/js/member/project/noticeManagement/noticeDetail.js"></script>
</head>

<body style="background:#fafafc;">

  <div class="card-body px-5 py-4">

  </div>

  <div class="d-flex justify-content-center gap-3 my-4">
    <a href="/mypage/notice_management" class="btn btn-outline-secondary px-5 py-2">목록</a>
    <button id="btnEditNotice" class="btn btn-purple px-5 py-2">수정</button>
  </div>

</body>