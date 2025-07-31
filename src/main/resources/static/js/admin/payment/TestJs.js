function goToDetail(card) {
        const productNo = card.dataset.productNo;
        console.log("productNo",productNo);
        const orderName = card.dataset.productName;
        // 상세 페이지로 이동 (GET 방식, productNo만 넘김)
        window.location.href = "/admin/payment/product/detail?productNo=" + encodeURIComponent(productNo);
    }