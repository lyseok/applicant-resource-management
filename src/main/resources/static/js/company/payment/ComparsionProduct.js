function goToPaymentPage() {
  const form = document.getElementById("changeForm");
  const oldPaymentNo = form.oldPaymentNo.value;
  const newProductNo = form.newProductNo.value;
  const billingKey = form.billingKey.value;

  const url = `/company/toss/change/buyproduct?productNo=${newProductNo}&oldPaymentNo=${oldPaymentNo}&billingKey=${billingKey}`;
  window.location.href = url;
  }