document.addEventListener('DOMContentLoaded', () => {
  document.querySelectorAll('.user-card').forEach(card => {
    card.addEventListener('click', () => {
      const userEmail = card.dataset.userEmail;
      if (userEmail) {
        console.log("보낼 이메일:", userEmail);
      } else {
        console.warn("userEmail이 비어있음!");
      }
    });
  });
});