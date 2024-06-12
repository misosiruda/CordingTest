function HourToMinute(str)
{
    let s = '';
    let m = 0;
    for(let i=0;i<str.length;i++)
    {
        if(str[i] == ':')
        {
            m += Number(s) * 60;
            s = '';
            continue;
        }
        s += str[i];
    }
    m += Number(s);
    return m;
}

function strTimeArrToNumberMinuteArr(book_time)
{
    let arr = []
    let enter = 0, exit = 0;
    for(e of book_time)
    {
        enter = HourToMinute(e[0]);
        exit = HourToMinute(e[1]) + 10;
        arr.push([enter, exit]);
    }
    return arr;
}

function solution(book_time) 
{
    var answer = 0;
    let book = [];
    book = strTimeArrToNumberMinuteArr(book_time);
    book = book.sort((a,b) => a[0] - b[0]);
    for(let i=0;i<book.length;i++)
    {
        answer++;
        for(let j=0;j<i;j++)
        {
            if(book[i][0] >= book[j][1])
            {
                answer--;
                book[j][1] = 1440;
                break;
            }
        }
    }
    return answer;
}