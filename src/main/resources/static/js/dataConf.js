export const loginData = {
  user: {
    username: 'member827',
    password: 'asd123!@#',
  },
  company: {
    username: 'woowahan',
    // username: 'daedongmobility',
    password: 'asd123!@#',
  },
  admin: {
    username: 'testAdmin',
    password: 'java',
  },
};

export const DATA = {
  signup1: {
    '#brNumber': '142-81-14049',
    '#comName': '(주)이노메트리',
  },
  signup2: {
    '#ceoName': '이갑수',
    '#comNum': '01085977748',
    '#comAddr1': '경기 화성시 정남면 정남산단1길 40',
    '#comAddr2': '(주)이노메트리',
    '#industry_code': 'INNO000313', // 서버로 전송할 코드
    '#industry_selected_area': 'IT컨설팅',
    '#comCreateYear': '2008',
    '#mail_email_id': 'robin0329184@gmail.com',
    '#id': 'innometry',
    '#password1': 'asd123!@#',
    '#password2': 'asd123!@#',
  },
  comInfo: {
    '#comInfo': `"(주)대동모빌리티는 대한민국 농기계 최대 브랜드인 (주)대동의 계열사중 한곳으로 1977년 설립이후 꾸준한 성장을 바탕으로 내실을 다져온 기업입니다.
기존의 체인사업을 바탕으로 트랙터용 작업기 및 다목적운반차량 생산시설을 구축하여 시장에 첫발을 내딛은 후, 지속적인 모빌리티 신제품 개발과 앞으로 미래 모빌리티 시장을 선도하여 새로운 기업으로 도약하고자 하는 동시에 향후 성장성이 기대되는 기업으로 주목받고 있습니다.

기업비전
대한민국 No.1을 넘어 세계로!
행복한 사람들이 만들어 가는 기업"`,
    '#comUrl': 'http://www.daedongmobility.co.kr',
    '#comMem': '413',
    '#comCapital': '4500000',
    '#comMainBiz': '롤러체인,농업기자재,농업기계 제조,도소매',
    '#insuranceYn': 'Y',
    '#comType': 'COMT-001',
    '#comSize': 'SIZE-004',
  },
};

export const formValues = {
  '#recruitmentTitle': 'SCM팀 Business Analyst 모집',
  '#yearCode': 'YEXP-Y0',
  '[name="recruitmentSalary"]': '3500',
  '#upperJobCode': '2',
  '#jobSearchInput': '데이터분석가',
  '#hiddenJobCode': '82',
  '#eduCode': 'EDUC-007',
  '#positionSelect': 'RANK-001',
  '[name="recruitmentChargerTel"]': '010-8679-8797',
  '[name="recruitmentFinishDate"]': '2025-10-10',
  '[name="recPositionNumber"]': '1',
  '[name="recruitmentDesk"]': '홈페이지 지원',
  '#skillInput': ['Spring', 'Oracle', 'Javascript'],
  '#cityCode': 'CICO101000',
  '#districtCode': '101180',
};

export const examData = {
  comExamName: 'DataBase 시험',
  questionList: [
    {
      comExamContents:
        '다음 중 데이터베이스 트랜잭션의 특성(ACID)에 해당하지 않는 것은?',
      optionList: [
        { content: '원자성(Atomicity)', correct: 'N' },
        { content: '일관성(Consistency)', correct: 'N' },
        { content: '독립성(Independence)', correct: 'Y' },
        { content: '지속성(Durability)', correct: 'N' },
      ],
    },
    {
      comExamContents:
        '데이터베이스에서 인덱스를 사용하는 주된 목적은 무엇인가?',
      optionList: [
        { content: '데이터 무결성을 유지하기 위해', correct: 'N' },
        { content: '데이터 검색 속도를 향상시키기 위해', correct: 'Y' },
        { content: '데이터 중복을 방지하기 위해', correct: 'N' },
        { content: '데이터베이스 크기를 줄이기 위해', correct: 'N' },
      ],
    },
  ],
};

export const templateData = {
  title: '백엔드 개발자 입사제안(Spring, Oracle)',
  content: `안녕하세요.
우아한형제들 인사담당자입니다.

귀하의 우수한 경력과 역량을 검토한 결과, 당사에서 진행 중인 백엔드 개발자 포지션과 매우 적합하다고 판단하여 입사 제안을 드리고자 합니다.

[모집 분야 및 주요 업무]
	•	직무: 백엔드 개발자 (Spring, Oracle 기반)
	•	주요 업무:
· 대규모 배달 플랫폼 백엔드 시스템 설계 및 개발
· 데이터베이스 설계·최적화 및 성능 개선
· 프론트엔드 및 타 부서와의 협업을 통한 서비스 고도화

[근무 환경 및 지원]
	•	업계 최고 수준의 보상 및 성과 인센티브 제공
	•	최신 기술 도입 및 대규모 프로젝트 참여 기회
	•	체계적인 교육 프로그램과 개인 성장 지원 제도

귀하께서 당사와 함께하게 된다면, 배달의민족을 비롯한 당사의 다양한 서비스 혁신에 큰 시너지를 발휘할 수 있을 것이라 확신합니다.

관심이 있으시다면, 본 메일에 회신 주시면 채용 절차 및 조건에 대해 상세히 안내드리겠습니다.
귀하와의 긍정적인 만남을 기대합니다.

감사합니다.

우아한형제들 인사담당자 드림
이메일: recruit@woowahan.com
연락처: 02-1234-5678`,
};

export const projectData = {
  prjEmpTitle:
    '[사이드 프로젝트] 예약 가능한 배달 플랫폼 개발팀 백엔드·프론트엔드 모집',
  tags: ['spring', 'react', 'oracle'],
  roles: [
    {
      jobCode: 84,
      jobCodeName: '백엔드/서버개발',
      rcrtPsncnt: 1,
      name: '백엔드/서버개발',
    },
    {
      jobCode: 92,
      jobCodeName: '프론트엔드',
      rcrtPsncnt: 1,
      name: '프론트엔드',
    },
    { jobCode: 1502, jobCodeName: '웹디자인', rcrtPsncnt: 1, name: '웹디자인' },
  ],
  prjTopic: '배달 웹 어플리케이션 플랫폼',
  prjStartPlanDate: '2025-08-05', // 날짜 형식: YYYY-MM-DD
  prjEndPlanDate: '2025-08-15',
  prjAnncContent: `💼 [사이드 프로젝트 팀원 모집]

안녕하세요!
실사용을 목표로 한 배달 예약 서비스를 개발할 팀원을 모집합니다.
사용자가 원하는 시간에 음식을 예약 배달할 수 있도록 스프링(Spring)과 오라클(Oracle) 기반 백엔드, React 프론트엔드를 활용한 웹 서비스를 계획하고 있습니다.
자세한 기능 기획 및 시나리오는 팀 빌딩 후 공유드리겠습니다 :)

⸻

🔍 모집 포지션
	•	백엔드 1명
	•	프론트엔드 1명
	•	디자이너 1명

현업 경험자, 신입 개발자 모두 환영합니다!
특히 배달, 예약 서비스 경험이 있으신 분이라면 더 좋습니다.

⸻

💡 이런 분이면 좋아요!
	•	실제 서비스 런칭을 목표로 프로젝트를 경험해보고 싶은 분
	•	예약, 배달, 실시간 처리 시스템 등 트래픽 고려 프로젝트에 관심 있는 분
	•	주 1회 이상 협업 가능하신 분 (평일 저녁 + 주말 위주)
	•	잠실 인근 격주 오프라인 미팅 가능자 (협의 가능)
	•	프로젝트 기간은 약 6개월로 예상됩니다.

⸻

🛠 기술 스택 (협의 가능)
	•	백엔드: Spring (Java)
	•	데이터베이스: Oracle
	•	프론트엔드: React
	•	디자인: Figma`,
};

export const interviewData = {
  '#companyName': '(주)시몬스',
  '#jobNameInput': '가구디자인',
  '#jobCodeHidden': '1502',
  '#interviewDate': '2025-07-20',
  '[name="evaluation"]': '1', // 긍정적
  '[name="interviewLevel"]': '2', // 중
  '[name="interviewType"]': 'N', // 대면
  '#interviewContent': `서류 합격 후 1차 실무면접, 2차 임원면접을 진행했습니다. 
실무진은 기술적인 부분과 협업 경험을 많이 물어보았습니다.`,
  '[name="interviewPassYn"]': 'Y', // 합격
  '#interviewQuestion': `Q1. 자기소개 해주세요.
Q2. 최근에 해결한 문제 상황에 대해 설명해주세요.`,
  '#tip': `면접 분위기는 비교적 편안했고, 포트폴리오 기반 질문이 많았습니다.`,
};

export const portfolioData = [
  {
    porName: '띹라이더 프로젝트',
    porUrl: 'https://github.com/lyseok/applicant-resource-managemente',
    porStartDate: '2025-07-01',
    porEndDate: '2025-08-01',
    porInformation: `사용자가 원하는 시간에 음식을 예약 배달할 수 있는 웹 어플리케이션입니다.`,
  },
];

export const awardData = [
  {
    awardName: '2025 대한민국 스마트서비스 개발 공보전 - 우수상',
    awardDate: '2025-08-03',
    hosting: '과학기술정보통신부/한국정보화진흥원(NIA)',
  },
];

export const introductionData = [
  {
    question: '프로젝트 진행 중 어려웠던 점과 이를 해결하기 위한 노력',
    content: `저는 대학 시절 팀 프로젝트로 웹 애플리케이션을 개발할 때 예상치 못한 기술적 문제와 팀 내 커뮤니케이션의 한계로 큰 어려움을 겪은 경험이 있습니다. 초기 기획 단계에서 요구사항이 명확히 정리되지 않아 개발 도중 빈번한 수정 요청이 발생했고, 이는 일정 지연으로 이어졌습니다.

이 문제를 해결하기 위해 칸반보드를 활용하여 작업 현황을 가시화하고, 매일 짧은 스탠드업 미팅을 통해 이슈를 공유하도록 제안했습니다. 또한, 기술적으로 해결이 어려운 문제는 외부 자료를 찾아보고, 오픈소스 커뮤니티에 질문을 올리며 해결책을 모색했습니다.

이러한 노력을 통해 프로젝트 일정을 다시 정상 궤도로 돌릴 수 있었고, 결과적으로 초기 목표보다 1주일 빠른 시점에 프로젝트를 완료할 수 있었습니다. 이 경험은 문제 해결 능력뿐 아니라 팀원과의 협업 능력을 한층 성장시키는 계기가 되었습니다.`,
  },
];

export const defaultQuestions = [
  '문제를 분석하고 해결 과정을 논리적으로 설명하며 실무에 적용할 수 있는가?',
  '주어진 요구사항에 맞는 아키텍처를 설계하고 기술을 타당하게 선택할 수 있는가?',
  '협업 과정에서의 경험을 바탕으로 갈등을 해결하고 원활한 의사소통이 가능한가?',
  '새로운 기술 학습과 자기개발에 대해 주도적이고 지속적인 노력을 기울였는가?',
  '사용자 경험 개선을 위해 서비스의 본질을 이해하고 구체적인 개선 아이디어를 제안할 수 있는가?',
  '면접 중 성실하고 적극적인 태도를 보이며 비언어적 커뮤니케이션이 적절한가?',
];

export const videoInterviewData = {
  roomTitle: '2025 우아한 형제들 백엔드 개발자 모집 면접',
  maxJoinCount: 4,
};
