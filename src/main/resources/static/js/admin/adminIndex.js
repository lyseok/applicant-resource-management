// Chart.js 기본 설정
Chart.defaults.font.family =
  '-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif';
Chart.defaults.color = '#6c757d';

// 공통 색상 팔레트
const colorPalette = {
  primary: '#7F55B1',
  secondary: '#8F87F1',
  success: '#C68EFD',
  info: '#E9A5F1',
  warning: '#FED2E2',
  danger: '#F4F8D3',
  gradient: ['#7F55B1', '#8F87F1', '#C68EFD', '#E9A5F1', '#FED2E2', '#F4F8D3'],
};

// 구직자 통계 차트들
function initJobseekerCharts() {
  // 신규 가입자 데이터 객체
  const jobseekerData = {
    weekly: {
      labels: ['1일', '2일', '3일', '4일', '5일', '6일', '7일'],
      data: [45, 52, 38, 67, 89, 76, 94],
    },
    monthly: {
      labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월'],
      data: [320, 450, 380, 520, 610, 700, 850],
    },
  };
  // 신규 가입자 추이 차트 생성
  const newJobseekerCtx = document
    .getElementById('newJobseekerChart')
    .getContext('2d');
  const newJobseekerChart = new Chart(newJobseekerCtx, {
    type: 'line',
    data: {
      labels: jobseekerData.weekly.labels,
      datasets: [
        {
          label: '신규 가입자',
          data: jobseekerData.weekly.data,
          borderColor: colorPalette.primary,
          backgroundColor: `${colorPalette.primary}20`,
          borderWidth: 3,
          fill: true,
          tension: 0.4,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: { legend: { display: false } },
      scales: {
        y: { beginAtZero: true, grid: { color: '#f1f3f4' } },
        x: { grid: { display: false } },
      },
    },
  });
  // 차트 컨트롤 버튼 이벤트 리스너
  document
    .getElementById('jobseekerWeeklyBtn')
    .addEventListener('click', () => {
      newJobseekerChart.data.labels = jobseekerData.weekly.labels;
      newJobseekerChart.data.datasets[0].data = jobseekerData.weekly.data;
      newJobseekerChart.update();
    });
  document
    .getElementById('jobseekerMonthlyBtn')
    .addEventListener('click', () => {
      newJobseekerChart.data.labels = jobseekerData.monthly.labels;
      newJobseekerChart.data.datasets[0].data = jobseekerData.monthly.data;
      newJobseekerChart.update();
    });

  // 회원 활동 현황
  const memberActivityCtx = document
    .getElementById('memberActivityChart')
    .getContext('2d');
  new Chart(memberActivityCtx, {
    type: 'doughnut',
    data: {
      labels: ['활성 사용자', '비활성 사용자', '휴면 계정'],
      datasets: [
        {
          data: [68, 22, 10],
          backgroundColor: [
            colorPalette.success,
            colorPalette.warning,
            colorPalette.danger,
          ],
          borderWidth: 0,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: 'bottom',
          labels: { padding: 20, usePointStyle: true },
        },
      },
    },
  });

  // 연령대별 분포
  const ageDistCtx = document.getElementById('ageDistChart').getContext('2d');
  new Chart(ageDistCtx, {
    type: 'doughnut',
    data: {
      labels: ['20대', '30대', '40대', '50대+'],
      datasets: [
        {
          data: [35, 40, 20, 5],
          backgroundColor: colorPalette.gradient.slice(0, 4),
          borderWidth: 0,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: 'bottom',
          labels: { padding: 15, usePointStyle: true },
        },
      },
    },
  });

  // 성별 분포
  const genderDistCtx = document
    .getElementById('genderDistChart')
    .getContext('2d');
  new Chart(genderDistCtx, {
    type: 'doughnut',
    data: {
      labels: ['남성', '여성'],
      datasets: [
        {
          data: [58, 42],
          backgroundColor: [colorPalette.primary, colorPalette.secondary],
          borderWidth: 0,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: 'bottom',
          labels: { padding: 15, usePointStyle: true },
        },
      },
    },
  });

  // 학력 분포
  const educationDistCtx = document
    .getElementById('educationDistChart')
    .getContext('2d');
  new Chart(educationDistCtx, {
    type: 'doughnut',
    data: {
      labels: ['고졸', '대졸', '석사', '박사'],
      datasets: [
        {
          data: [15, 65, 18, 2],
          backgroundColor: [
            colorPalette.info,
            colorPalette.primary,
            colorPalette.warning,
            colorPalette.danger,
          ],
          borderWidth: 0,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: 'bottom',
          labels: { padding: 15, usePointStyle: true },
        },
      },
    },
  });

  // 직무 선호도
  const jobPreferenceCtx = document
    .getElementById('jobPreferenceChart')
    .getContext('2d');
  new Chart(jobPreferenceCtx, {
    type: 'doughnut',
    data: {
      labels: ['개발', '디자인', '기획', '마케팅', '기타'],
      datasets: [
        {
          data: [45, 20, 15, 12, 8],
          backgroundColor: colorPalette.gradient.slice(0, 5),
          borderWidth: 0,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: 'bottom',
          labels: { padding: 15, usePointStyle: true },
        },
      },
    },
  });

  // 지원 행동 분석
  const applicationBehaviorCtx = document
    .getElementById('applicationBehaviorChart')
    .getContext('2d');
  new Chart(applicationBehaviorCtx, {
    type: 'bar',
    data: {
      labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월'],
      datasets: [
        {
          label: '지원 수',
          data: [1200, 1450, 1380, 1620, 1890, 2100, 2340],
          backgroundColor: colorPalette.primary,
          borderRadius: 4,
          yAxisID: 'y',
        },
        {
          label: '클릭률 (%)',
          data: [23, 25, 22, 28, 31, 29, 33],
          type: 'line',
          borderColor: colorPalette.success,
          backgroundColor: `${colorPalette.success}20`,
          borderWidth: 3,
          fill: false,
          yAxisID: 'y1',
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      scales: {
        y: { type: 'linear', position: 'left', beginAtZero: true },
        y1: {
          type: 'linear',
          position: 'right',
          beginAtZero: true,
          grid: { drawOnChartArea: false },
        },
        x: { grid: { display: false } },
      },
    },
  });
}

// 기업 통계 차트들
function initCompanyCharts() {
  // 신규 기업 가입 추이
  const newCompanyCtx = document
    .getElementById('newCompanyChart')
    .getContext('2d');
  new Chart(newCompanyCtx, {
    type: 'line',
    data: {
      labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월'],
      datasets: [
        {
          label: '신규 기업',
          data: [12, 18, 15, 22, 28, 35, 42],
          borderColor: colorPalette.secondary,
          backgroundColor: `${colorPalette.secondary}20`,
          borderWidth: 3,
          fill: true,
          tension: 0.4,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: { legend: { display: false } },
      scales: {
        y: { beginAtZero: true, grid: { color: '#f1f3f4' } },
        x: { grid: { display: false } },
      },
    },
  });

  // 기업 규모별 분포
  const companySizeCtx = document
    .getElementById('companySizeChart')
    .getContext('2d');
  new Chart(companySizeCtx, {
    type: 'doughnut',
    data: {
      labels: ['대기업', '중견기업', '중소기업', '스타트업'],
      datasets: [
        {
          data: [15, 25, 45, 15],
          backgroundColor: [
            colorPalette.primary,
            colorPalette.info,
            colorPalette.success,
            colorPalette.warning,
          ],
          borderWidth: 0,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: 'bottom',
          labels: { padding: 15, usePointStyle: true },
        },
      },
    },
  });

  // 유료/무료 회원 비율
  const membershipTypeCtx = document
    .getElementById('membershipTypeChart')
    .getContext('2d');
  new Chart(membershipTypeCtx, {
    type: 'doughnut',
    data: {
      labels: ['유료 회원', '무료 회원'],
      datasets: [
        {
          data: [35, 65],
          backgroundColor: [colorPalette.success, colorPalette.info],
          borderWidth: 0,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: 'bottom',
          labels: { padding: 15, usePointStyle: true },
        },
      },
    },
  });

  // 채용 공고 현황
  const jobPostingStatusCtx = document
    .getElementById('jobPostingStatusChart')
    .getContext('2d');
  new Chart(jobPostingStatusCtx, {
    type: 'bar',
    data: {
      labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월'],
      datasets: [
        {
          label: '진행중',
          data: [180, 220, 190, 250, 320, 380, 420],
          backgroundColor: colorPalette.primary,
          borderRadius: 4,
        },
        {
          label: '마감',
          data: [120, 150, 140, 180, 200, 230, 260],
          backgroundColor: colorPalette.info,
          borderRadius: 4,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      scales: {
        y: { beginAtZero: true, grid: { color: '#f1f3f4' } },
        x: { grid: { display: false } },
      },
    },
  });

  // 기업별 채용 성공률
  const recruitmentSuccessCtx = document
    .getElementById('recruitmentSuccessChart')
    .getContext('2d');
  new Chart(recruitmentSuccessCtx, {
    type: 'bar',
    data: {
      labels: ['삼성', 'LG', '네이버', '카카오', '쿠팡', '배민'],
      datasets: [
        {
          label: '성공률 (%)',
          data: [85, 78, 92, 88, 76, 82],
          backgroundColor: colorPalette.gradient,
          borderRadius: 4,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: { legend: { display: false } },
      scales: {
        y: { beginAtZero: true, max: 100, grid: { color: '#f1f3f4' } },
        x: { grid: { display: false } },
      },
    },
  });

  // 인재풀 활용 현황
  const talentPoolUsageCtx = document
    .getElementById('talentPoolUsageChart')
    .getContext('2d');
  new Chart(talentPoolUsageCtx, {
    type: 'line',
    data: {
      labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월'],
      datasets: [
        {
          label: '조회수',
          data: [1200, 1450, 1380, 1620, 1890, 2100, 2340],
          borderColor: colorPalette.primary,
          backgroundColor: `${colorPalette.primary}20`,
          borderWidth: 3,
          fill: true,
          tension: 0.4,
          yAxisID: 'y',
        },
        {
          label: '스카우트 제안',
          data: [45, 52, 48, 67, 78, 85, 92],
          borderColor: colorPalette.success,
          backgroundColor: `${colorPalette.success}20`,
          borderWidth: 3,
          fill: false,
          tension: 0.4,
          yAxisID: 'y1',
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      scales: {
        y: { type: 'linear', position: 'left', beginAtZero: true },
        y1: {
          type: 'linear',
          position: 'right',
          beginAtZero: true,
          grid: { drawOnChartArea: false },
        },
        x: { grid: { display: false } },
      },
    },
  });
}

// 인재풀 통계 차트들
function initTalentPoolCharts() {
  // 인재풀 등록 추이
  const talentPoolTrendCtx = document
    .getElementById('talentPoolTrendChart')
    .getContext('2d');
  new Chart(talentPoolTrendCtx, {
    type: 'line',
    data: {
      labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월'],
      datasets: [
        {
          label: '인재풀 등록',
          data: [320, 450, 380, 520, 680, 750, 890],
          borderColor: colorPalette.info,
          backgroundColor: `${colorPalette.info}20`,
          borderWidth: 3,
          fill: true,
          tension: 0.4,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: { legend: { display: false } },
      scales: {
        y: { beginAtZero: true, grid: { color: '#f1f3f4' } },
        x: { grid: { display: false } },
      },
    },
  });

  // 관심 프로필 카테고리
  const interestProfileCtx = document
    .getElementById('interestProfileChart')
    .getContext('2d');
  new Chart(interestProfileCtx, {
    type: 'doughnut',
    data: {
      labels: ['개발자', '디자이너', '기획자', '마케터', '기타'],
      datasets: [
        {
          data: [42, 23, 18, 12, 5],
          backgroundColor: colorPalette.gradient.slice(0, 5),
          borderWidth: 0,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: 'bottom',
          labels: { padding: 15, usePointStyle: true },
        },
      },
    },
  });

  // 프로젝트 참여 현황
  const projectParticipationCtx = document
    .getElementById('projectParticipationChart')
    .getContext('2d');
  new Chart(projectParticipationCtx, {
    type: 'bar',
    data: {
      labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월'],
      datasets: [
        {
          label: '참여 인원',
          data: [45, 52, 48, 67, 78, 85, 92],
          backgroundColor: colorPalette.warning,
          borderRadius: 4,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: { legend: { display: false } },
      scales: {
        y: { beginAtZero: true, grid: { color: '#f1f3f4' } },
        x: { grid: { display: false } },
      },
    },
  });

  // 프로젝트 진행 상태
  const projectStatusCtx = document
    .getElementById('projectStatusChart')
    .getContext('2d');
  new Chart(projectStatusCtx, {
    type: 'doughnut',
    data: {
      labels: ['진행중', '완료', '지연'],
      datasets: [
        {
          data: [45, 40, 15],
          backgroundColor: [
            colorPalette.primary,
            colorPalette.success,
            colorPalette.danger,
          ],
          borderWidth: 0,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: 'bottom',
          labels: { padding: 15, usePointStyle: true },
        },
      },
    },
  });
}

// 사업 인사이트 차트들
function initBusinessCharts() {
  // 매출 추이
  const revenueCtx = document.getElementById('revenueChart').getContext('2d');
  new Chart(revenueCtx, {
    type: 'line',
    data: {
      labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월'],
      datasets: [
        {
          label: '매출 (백만원)',
          data: [32, 38, 35, 42, 48, 52, 58],
          borderColor: colorPalette.success,
          backgroundColor: `${colorPalette.success}20`,
          borderWidth: 3,
          fill: true,
          tension: 0.4,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: { legend: { display: false } },
      scales: {
        y: { beginAtZero: true, grid: { color: '#f1f3f4' } },
        x: { grid: { display: false } },
      },
    },
  });

  // 서비스별 매출
  const serviceRevenueCtx = document
    .getElementById('serviceRevenueChart')
    .getContext('2d');
  new Chart(serviceRevenueCtx, {
    type: 'doughnut',
    data: {
      labels: ['채용공고', '인재풀 열람', '프리미엄 서비스', '광고'],
      datasets: [
        {
          data: [45, 25, 20, 10],
          backgroundColor: [
            colorPalette.primary,
            colorPalette.info,
            colorPalette.warning,
            colorPalette.danger,
          ],
          borderWidth: 0,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: 'bottom',
          labels: { padding: 15, usePointStyle: true },
        },
      },
    },
  });

  // 채용 리드타임
  const recruitmentLeadTimeCtx = document
    .getElementById('recruitmentLeadTimeChart')
    .getContext('2d');
  new Chart(recruitmentLeadTimeCtx, {
    type: 'bar',
    data: {
      labels: ['IT', '금융', '제조', '서비스', '의료', '교육'],
      datasets: [
        {
          label: '평균 일수',
          data: [25, 32, 28, 35, 30, 38],
          backgroundColor: colorPalette.gradient,
          borderRadius: 4,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: { legend: { display: false } },
      scales: {
        y: { beginAtZero: true, grid: { color: '#f1f3f4' } },
        x: { grid: { display: false } },
      },
    },
  });

  // 회원 이탈 분석
  const churnAnalysisCtx = document
    .getElementById('churnAnalysisChart')
    .getContext('2d');
  new Chart(churnAnalysisCtx, {
    type: 'line',
    data: {
      labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월'],
      datasets: [
        {
          label: '이탈률 (%)',
          data: [8.5, 7.2, 6.8, 5.9, 6.3, 5.7, 5.2],
          borderColor: colorPalette.danger,
          backgroundColor: `${colorPalette.danger}20`,
          borderWidth: 3,
          fill: true,
          tension: 0.4,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: { legend: { display: false } },
      scales: {
        y: { beginAtZero: true, max: 10, grid: { color: '#f1f3f4' } },
        x: { grid: { display: false } },
      },
    },
  });

  // 인기 기술 스택 트렌드
  const techTrendCtx = document
    .getElementById('techTrendChart')
    .getContext('2d');
  new Chart(techTrendCtx, {
    type: 'bar',
    data: {
      labels: [
        'JavaScript',
        'Python',
        'React',
        'Node.js',
        'Java',
        'TypeScript',
        'Vue.js',
        'AWS',
      ],
      datasets: [
        {
          label: '언급 횟수',
          data: [2340, 1890, 1650, 1420, 1280, 1150, 980, 850],
          backgroundColor: colorPalette.primary,
          borderRadius: 4,
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: { legend: { display: false } },
      scales: {
        y: { beginAtZero: true, grid: { color: '#f1f3f4' } },
        x: { grid: { display: false } },
      },
    },
  });
}

// 차트 초기화
document.addEventListener('DOMContentLoaded', function () {
  // 초기 차트 로드
  initJobseekerCharts();

  // 탭 변경 시 차트 초기화
  const tabButtons = document.querySelectorAll(
    '#adminTabs button[data-bs-toggle="pill"]'
  );
  tabButtons.forEach((button) => {
    button.addEventListener('shown.bs.tab', function (event) {
      const targetTab = event.target.getAttribute('data-bs-target');

      setTimeout(() => {
        switch (targetTab) {
          case '#jobseeker':
            initJobseekerCharts();
            break;
          case '#company':
            initCompanyCharts();
            break;
          case '#talent':
            initTalentPoolCharts();
            break;
          case '#business':
            initBusinessCharts();
            break;
        }
      }, 100);
    });
  });
});

// 차트 컨트롤 버튼 이벤트
document.addEventListener('click', function (e) {
  if (e.target.matches('.chart-controls .btn')) {
    const parent = e.target.parentElement;
    parent
      .querySelectorAll('.btn')
      .forEach((btn) => btn.classList.remove('active'));
    e.target.classList.add('active');
  }
});

// 키 메트릭 애니메이션
function animateMetrics() {
  const metrics = document.querySelectorAll('.metric-content h3');
  metrics.forEach((metric) => {
    const target = parseInt(metric.textContent.replace(/[^\d]/g, ''));
    let current = 0;
    const increment = target / 50;
    const timer = setInterval(() => {
      current += increment;
      if (current >= target) {
        current = target;
        clearInterval(timer);
      }

      const formattedValue = Math.floor(current).toLocaleString();
      if (metric.textContent.includes('₩')) {
        metric.textContent = `₩${formattedValue}M`;
      } else {
        metric.textContent = formattedValue;
      }
    }, 20);
  });
}

// 페이지 로드 후 애니메이션 실행
setTimeout(animateMetrics, 500);

// 실시간 데이터 업데이트 시뮬레이션
setInterval(() => {
  const alerts = document.querySelector('.alerts-list');
  if (alerts && Math.random() > 0.7) {
    // 새로운 알림 추가 (실제 구현에서는 서버에서 데이터를 받아옴)
    console.log('새로운 실시간 알림이 있습니다.');
  }
}, 30000); // 30초마다 체크
