<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
<script src="https://js.tosspayments.com/v1"></script>
<script src="/js/company/payment/TossPayment.js"></script>
     <title>유료 구독 상품</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 40px;
        }
        .container {
            display: flex;
            justify-content: center;
            align-items: flex-start;
            gap: 40px;
            flex-wrap: nowrap;
        }
        .card {
            background-color: #ffffff;
            border-radius: 16px;
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
            padding: 30px 25px;
            width: 320px;
            transition: transform 0.3s, box-shadow 0.3s;
            cursor: pointer;
        }
        .card:hover {
            transform: scale(1.05);
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
        }
        .title {
            font-size: 1.8em;
            font-weight: bold;
            color: #2c3e50;
            text-align: center;
            margin-bottom: 20px;
        }
        ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }
        li {
            font-size: 1em;
            padding: 10px 0;
            color: #555;
            border-bottom: 1px solid #eee;
        }
        .badge {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            padding: 3px 10px;
            font-size: 0.75em;
            border-radius: 12px;
            margin-left: 8px;
        }
        .badge.platinum {
            background-color: #2196F3;
        }
    </style>
</head>
<body>

<div class="container">
    <!-- Global 등급 카드 -->
    <div class="card" onclick="requestPayment(this)" data-order-name="Global 등급" >
        <div class="title" id="Global">Global 등급 <span class="badge">Top</span></div>
        <ul>
        	<li id="orderName">Global 등급</li>
        	<li id="amount">10</li>
            <li>화상회의 가능</li>
            <li>채용공고 맨위에 노출</li>
            <li>공고 검색 시 맨위에 노출</li>
            <li>회원 등급 표시 기능 (필터 조건 포함)</li>
            <li>이직 제안 무제한</li>
        </ul>
    </div>

    <!-- Platinum 등급 카드 -->
    <div class="card" onclick="requestPayment()">
        <div class="title" id="Platinum">Platinum 등급 <span class="badge platinum">Premium</span></div>
        <ul>
       	   <li id="orderName">Platinum</li>
        	<li id="amount">5000000</li>
            <li>화상회의 가능</li>
            <li>채용공고 두 번째 노출</li>
            <li>공고 검색 시 두 번째 노출</li>
            <li>이직 제안 횟수 제한 (예: n회)</li>
        </ul>
    </div>
</div>

</body>
</html>