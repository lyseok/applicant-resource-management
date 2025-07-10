function calculateAnnuaILeave(){
    const startDate = new Date(document.getElementById('startDate').value);
    const workMonths = parseInt(document.getElementById('workMonths').value);
    const attendanceRate = parseInt(document.getElementById('attendanceRate').value);

    let annualLeaveDays = 0;

    if(workMonths < 12){
        annualLeaveDays = workMonths;
        if(attendanceRate >=80){
            annualLeaveDays = Math.min(11, annualLeaveDays);    // 출근율 80% 이상, 최대 11일
        }else {
            annualLeaveDays = 0;    // 출근율 80% 미만
        }
    }
    else {
        const fullYears = Math.floor(workMonths / 12);
        annualLeaveDays = 15 + Math.floor((fullYears -1)/2 );
        annualLeaveDays = Math.min(25, annualLeaveDays);    // 최대 25일

        // 1년 미만의근무기간동안 사용한 연차 공재
        const remainingMonths = workMonths % 12;
        if(remainingMonths > 0 && attendanceRate >= 80) {
            annualLeaveDays -=remainingMonths;
        }
    }
    document.getElementById('result').innerText = `총연차 휴가일수  : ${annualLeaveDays} 일 `;
}