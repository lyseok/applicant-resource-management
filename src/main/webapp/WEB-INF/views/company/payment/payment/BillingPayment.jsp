<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="ko">

    <head>
      <meta charset="utf-8" />
      <!-- SDK 추가 -->
      <script src="https://js.tosspayments.com/v2/standard"></script>
    </head>

    <body>
      <!-- 카드 등록하기 버튼 -->
      <button class="button" style="margin-top: 30px" onclick="requestBillingAuth()">카드 등록하기</button>
      <script>
        // ------  SDK 초기화 ------
        // @docs https://docs.tosspayments.com/sdk/v2/js#토스페이먼츠-초기화
        const clientKey = "test_ck_Gv6LjeKD8a9wXO9o7lLw8wYxAdXy";
        const customerKey = "h5hXSJ-WPK8sZQpXQUJUA";
        const productNo = "${productNo}";
        const tossPayments = TossPayments(clientKey);
        const currentUrl = "http://192.168.34.70/company/payment/product/detail?productNo=" + productNo;
        // 회원 결제
        // @docs https://docs.tosspayments.com/sdk/v2/js#tosspaymentspayment
        const payment = tossPayments.payment({ customerKey });
        const successUrl = window.location.origin + "/company/toss/success?productNo=" + productNo;
        // 비회원 결제
        // const payment = tossPayments.payment({customerKey: TossPayments.ANONYMOUS})
        // ------ '카드 등록하기' 버튼 누르면 결제창 띄우기 ------
        // @docs https://docs.tosspayments.com/sdk/v2/js#paymentrequestpayment
        async function requestBillingAuth() {
          await payment.requestBillingAuth({
            method: "CARD", // 자동결제(빌링)는 카드만 지원합니다
            successUrl, // 현재 페이지로 돌아오게 함
            failUrl: window.location.origin + "/fail", // 요청이 실패하면 리다이렉트되는 URL
            customerEmail: "dhfjdhfj@naver.com",
            customerName: "Kius!d1@2",
          });
        }
      </script>
    </body>

    </html>