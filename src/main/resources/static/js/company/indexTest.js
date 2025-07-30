// Chart.js 기본 설정
Chart.defaults.font.family =
  '-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif';
Chart.defaults.color = '#6c757d';
const color1 = '#7F55B1';
const color2 = '#8F87F1';
const color3 = '#C68EFD';
const color4 = '#E9A5F1';
const color5 = '#FED2E2';
const color6 = '#F4F8D3';

// 신규 가입자 추이 차트
const newMembersCtx = document
  .getElementById('newMembersChart')
  .getContext('2d');
new Chart(newMembersCtx, {
  type: 'line',
  data: {
    labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월'],
    datasets: [
      {
        label: '신규 가입자',
        data: [320, 450, 380, 520, 680, 750, 890],
        borderColor: '#9d66ff',
        backgroundColor: 'rgba(191, 0, 255, 0.1)',
        borderWidth: 3,
        fill: true,
        tension: 0.4,
      },
    ],
  },
  options: {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: false,
      },
    },
    scales: {
      y: {
        beginAtZero: true,
        grid: {
          color: '#f1f3f4',
        },
      },
      x: {
        grid: {
          display: false,
        },
      },
    },
  },
});

// 연령대별 분포 도넛 차트
const ageCtx = document.getElementById('ageDistributionChart').getContext('2d');
new Chart(ageCtx, {
  type: 'doughnut',
  data: {
    labels: ['20대', '30대', '40대', '50대 이상'],
    datasets: [
      {
        data: [35, 40, 20, 5],
        backgroundColor: [color1, color2, color3, color4],
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
        labels: {
          padding: 20,
          usePointStyle: true,
        },
      },
    },
  },
});

// 학력별 분포 도넛 차트
const educationCtx = document.getElementById('educationChart').getContext('2d');
new Chart(educationCtx, {
  type: 'doughnut',
  data: {
    labels: ['고졸', '대졸', '석사', '박사'],
    datasets: [
      {
        data: [15, 65, 18, 2],
        backgroundColor: [color1, color2, color3, color4],
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
        labels: {
          padding: 20,
          usePointStyle: true,
        },
      },
    },
  },
});

// 경력 구간별 분포 도넛 차트
const experienceCtx = document
  .getElementById('experienceChart')
  .getContext('2d');
new Chart(experienceCtx, {
  type: 'doughnut',
  data: {
    labels: ['신입', '1-3년', '3-5년', '5년 이상'],
    datasets: [
      {
        data: [25, 35, 25, 15],
        backgroundColor: [color1, color2, color3, color4],
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
        labels: {
          padding: 20,
          usePointStyle: true,
        },
      },
    },
  },
});

// 성별 분포 도넛 차트
const genderCtx = document.getElementById('genderChart').getContext('2d');
new Chart(genderCtx, {
  type: 'doughnut',
  data: {
    labels: ['남성', '여성'],
    datasets: [
      {
        data: [58, 42],
        backgroundColor: [color1, color2],
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
        labels: {
          padding: 20,
          usePointStyle: true,
        },
      },
    },
  },
});

// 보유 기술 스택 TOP 10 막대 차트
const skillsCtx = document.getElementById('skillsChart').getContext('2d');
new Chart(skillsCtx, {
  type: 'bar',
  data: {
    labels: [
      'JavaScript',
      'Python',
      'Java',
      'React',
      'Node.js',
      'SQL',
      'HTML/CSS',
      'Git',
      'AWS',
      'Docker',
    ],
    datasets: [
      {
        label: '보유자 수',
        data: [2340, 1890, 1650, 1420, 1280, 1150, 1050, 980, 850, 720],
        backgroundColor: color1,
        borderRadius: 4,
      },
    ],
  },
  options: {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: false,
      },
    },
    scales: {
      y: {
        beginAtZero: true,
        grid: {
          color: '#f1f3f4',
        },
      },
      x: {
        grid: {
          display: false,
        },
      },
    },
  },
});

// 채용 단계별 현황 도넛 차트
const recruitmentCtx = document
  .getElementById('recruitmentStageChart')
  .getContext('2d');
new Chart(recruitmentCtx, {
  type: 'doughnut',
  data: {
    labels: ['지원', '서류합격', '면접', '최종합격'],
    datasets: [
      {
        data: [1247, 892, 456, 234],
        backgroundColor: [color1, color2, color3, color4],
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
        labels: {
          padding: 20,
          usePointStyle: true,
        },
      },
    },
  },
});

// 업종별 평균 연봉 막대 차트
const salaryCtx = document.getElementById('salaryChart').getContext('2d');
new Chart(salaryCtx, {
  type: 'bar',
  data: {
    labels: ['IT/소프트웨어', '금융', '제조업', '의료', '교육', '서비스업'],
    datasets: [
      {
        label: '평균 연봉 (만원)',
        data: [4500, 4200, 3800, 3600, 3200, 2800],
        backgroundColor: [color1, color2, color3, color4, color5, color6],
        borderRadius: 4,
      },
    ],
  },
  options: {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: false,
      },
    },
    scales: {
      y: {
        beginAtZero: true,
        grid: {
          color: '#f1f3f4',
        },
      },
      x: {
        grid: {
          display: false,
        },
      },
    },
  },
});

// 월별 지원자 추이 및 합격률 복합 차트
const applicantTrendCtx = document
  .getElementById('applicantTrendChart')
  .getContext('2d');
new Chart(applicantTrendCtx, {
  type: 'bar',
  data: {
    labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월'],
    datasets: [
      {
        label: '지원자 수',
        data: [180, 220, 190, 250, 320, 380, 420],
        backgroundColor: color1,
        yAxisID: 'y',
      },
      {
        label: '합격률 (%)',
        data: [18, 22, 25, 20, 19, 23, 21],
        type: 'line',
        borderColor: color2,
        backgroundColor: 'rgba(40, 167, 69, 0.1)',
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
      y: {
        type: 'linear',
        display: true,
        position: 'left',
        beginAtZero: true,
        grid: {
          color: '#f1f3f4',
        },
      },
      y1: {
        type: 'linear',
        display: true,
        position: 'right',
        beginAtZero: true,
        grid: {
          drawOnChartArea: false,
        },
      },
      x: {
        grid: {
          display: false,
        },
      },
    },
  },
});

// 달력 기능
class Calendar {
  constructor() {
    this.currentDate = new Date();
    this.today = new Date();
    this.init();
  }

  init() {
    this.render();
    this.bindEvents();
  }

  bindEvents() {
    document.getElementById('prevMonth').addEventListener('click', () => {
      this.currentDate.setMonth(this.currentDate.getMonth() - 1);
      this.render();
    });

    document.getElementById('nextMonth').addEventListener('click', () => {
      this.currentDate.setMonth(this.currentDate.getMonth() + 1);
      this.render();
    });

    document.getElementById('todayBtn').addEventListener('click', () => {
      this.currentDate = new Date();
      this.render();
    });
  }

  render() {
    const year = this.currentDate.getFullYear();
    const month = this.currentDate.getMonth();

    // 제목 업데이트
    document.getElementById('calendarTitle').textContent = `${year}년 ${
      month + 1
    }월`;

    // 달력 그리드 생성
    const grid = document.getElementById('calendarGrid');
    grid.innerHTML = '';

    // 요일 헤더
    const weekdays = ['일', '월', '화', '수', '목', '금', '토'];
    weekdays.forEach((day) => {
      const cell = document.createElement('div');
      cell.className = 'calendar-header-cell';
      cell.textContent = day;
      grid.appendChild(cell);
    });

    // 첫 번째 날과 마지막 날
    const firstDay = new Date(year, month, 1);
    const lastDay = new Date(year, month + 1, 0);
    const startDate = new Date(firstDay);
    startDate.setDate(startDate.getDate() - firstDay.getDay());

    // 달력 셀 생성
    for (let i = 0; i < 42; i++) {
      const cellDate = new Date(startDate);
      cellDate.setDate(startDate.getDate() + i);

      const cell = document.createElement('div');
      cell.className = 'calendar-cell';

      if (cellDate.getMonth() !== month) {
        cell.classList.add('other-month');
      }

      if (this.isSameDay(cellDate, this.today)) {
        cell.classList.add('today');
      }

      const dateDiv = document.createElement('div');
      dateDiv.className = 'calendar-date';
      dateDiv.textContent = cellDate.getDate();
      cell.appendChild(dateDiv);

      grid.appendChild(cell);
    }
  }

  isSameDay(date1, date2) {
    return (
      date1.getDate() === date2.getDate() &&
      date1.getMonth() === date2.getMonth() &&
      date1.getFullYear() === date2.getFullYear()
    );
  }
}

// 달력 초기화
document.addEventListener('DOMContentLoaded', () => {
  new Calendar();
});

// 차트 컨트롤 버튼 이벤트
document.querySelectorAll('.chart-controls .btn').forEach((btn) => {
  btn.addEventListener('click', function () {
    // 같은 그룹의 다른 버튼들에서 active 클래스 제거
    this.parentElement
      .querySelectorAll('.btn')
      .forEach((b) => b.classList.remove('active'));
    // 클릭된 버튼에 active 클래스 추가
    this.classList.add('active');
  });
});

// 뷰 컨트롤 버튼 이벤트
document.querySelectorAll('.view-controls .btn').forEach((btn) => {
  btn.addEventListener('click', function () {
    this.parentElement.querySelectorAll('.btn').forEach((b) => {
      b.classList.remove('active', 'btn-dark');
      b.classList.add('btn-outline-secondary');
    });
    this.classList.remove('btn-outline-secondary');
    this.classList.add('active', 'btn-dark');
  });
});

// 애니메이션 효과
document.addEventListener('DOMContentLoaded', () => {
  // 숫자 카운트업 애니메이션
  const animateNumbers = () => {
    const numbers = document.querySelectorAll('.stat-number, .project-number');
    numbers.forEach((num) => {
      const target = parseInt(num.textContent.replace(/,/g, ''));
      let current = 0;
      const increment = target / 50;
      const timer = setInterval(() => {
        current += increment;
        if (current >= target) {
          current = target;
          clearInterval(timer);
        }
        num.textContent = Math.floor(current).toLocaleString();
      }, 20);
    });
  };

  // 페이지 로드 후 애니메이션 실행
  setTimeout(animateNumbers, 500);
});
