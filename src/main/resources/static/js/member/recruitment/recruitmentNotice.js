/**
 * 
 */
function parseDateFromDB(dateStr) {
   if (!dateStr || typeof dateStr !== 'string') return new Date(NaN);
   
   const parts = dateStr.split('/');
   if (parts.length !== 3) return new Date(NaN);

   const [yy, mm, dd] = parts.map(Number);
   if (isNaN(yy) || isNaN(mm) || isNaN(dd)) return new Date(NaN);

   const fullYear = yy < 50 ? 2000 + yy : 1900 + yy;
   return new Date(fullYear, mm - 1, dd);
 }

 document.addEventListener('DOMContentLoaded', () => {
   const applyBtn = document.getElementById('applyBtn');
   if (!applyBtn) return;

   const finishDateStr = applyBtn.dataset.finish; // "25/07/30"
   const finishDate = parseDateFromDB(finishDateStr);

   const counterEl = document.getElementById('dDayCounter');
   if (!counterEl) return;

   if (isNaN(finishDate.getTime())) {
     counterEl.textContent = '날짜 오류';
     return;
   }

   function updateCountdown() {
     const now = new Date();
     const diff = finishDate - now;

     if (diff <= 0) {
       counterEl.textContent = '마감';
       clearInterval(timer);
       return;
     }

     const d = Math.floor(diff / (1000 * 60 * 60 * 24));
     const h = Math.floor((diff / (1000 * 60 * 60)) % 24);
     const m = Math.floor((diff / (1000 * 60)) % 60);
     const s = Math.floor((diff / 1000) % 60);

     counterEl.textContent =
       `D-${d} ${h.toString().padStart(2, '0')}:${m.toString().padStart(2, '0')}:${s.toString().padStart(2, '0')}`;
   }

   updateCountdown();
   const timer = setInterval(updateCountdown, 1000);
 });