function HourToSecond(h, m, s)
{
    return (h*3600 + m*60 + s);
}

function HowManyAlarm(sec)
{
    let h_alarm = parseInt(sec * 719 / 43200);
    let m_alarm = parseInt(sec * 59 / 3600);
    let panalty = 0;
    if(sec >= 43200) panalty = 2;
    else panalty = 1;
    return h_alarm + m_alarm - panalty;
}

function isOClock(sec)
{
    return (sec * 719 % 43200 == 0 || sec * 59 % 3600 == 0);
}

function solution(h1, m1, s1, h2, m2, s2) 
{
    let t1 = HourToSecond(h1, m1, s1), t2 = HourToSecond(h2, m2, s2);
    let a1 = HowManyAlarm(t1), a2 = HowManyAlarm(t2);
    let oC = 0;
    if(isOClock(t1)) oC = 1;
    return a2 - a1 + oC;
}