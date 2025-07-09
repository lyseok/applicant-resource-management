document.addEventListener('DOMContentLoaded', () => {
  document.querySelectorAll('.user-card').forEach(card => {
    card.addEventListener('click', (e) => {
      const userId = card.dataset.userId;
      if (userId) {
        location.href = '/talentpool/detail/' + userId;
      }
    });
  });
});