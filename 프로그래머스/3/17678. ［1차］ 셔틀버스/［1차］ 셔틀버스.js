/**
 * "HH:MM" 형식을 int MM 으로 바꿔주는 함수
 * @param {Array[String]} timetable "HH:MM" 형식으로 저장된 배열
 * @returns Array[Int] 몇분인지 담아서 반환 한다.
 */
function HourToMinute(timetable) {
    return timetable.map(hour => {
        let st = '';
        let minute = 0;
        for (let e of hour) {
            if (e == ':') {
                minute += Number(st) * 60;
                st = '';
            }
            else {
                st += e;
            }
        }
        minute += Number(st);
        return minute;
    });
}
/**
 * Int 분 으로 적힌 시간을 "HH:MM" 형식으로 바꿔서 반환하는 함수
 * @param {Number} time 분으로 된 시간
 * @returns "HH:MM"형식의 시간
 */
function MinuteToHour(time) {
    let hour = Math.floor(time / 60);
    let minute = time % 60;
    if (hour < 10) {
        hour = "0" + hour;
    }
    if (minute < 10) {
        minute = "0" + minute;
    }
    return hour + ":" + minute;
}

function solution(n, t, m, timetable) {
    let minuteTable = [];
    minuteTable = HourToMinute(timetable).sort((a, b) => a - b);
    let startTime = 540;
    let arriveTime = 0;
    let crewEndI = -1;
    let lastCrewCount = 0;
    for (let i = 0; i < n; i++) {
        lastCrewCount = 0;
        arriveTime = startTime + (i * t);
        for (let j = 0; j < m; j++) {
            crewEndI++;
            if (crewEndI === minuteTable.length) {
                crewEndI--;
                break;
            }
            if (minuteTable[crewEndI] > arriveTime) {
                crewEndI--;
                break;
            }
            lastCrewCount++;
        }
    }

    if (lastCrewCount == m) {
        return MinuteToHour(minuteTable[crewEndI] - 1);
    }
    else {
        return MinuteToHour(arriveTime);
    }
}