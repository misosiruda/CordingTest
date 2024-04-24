/**
 * 두 좌표 사이의 거리를 구하는 함수
 * @param {Array} pos1 첫번째 좌표
 * @param {Array} pos2 두번째 좌표
 * @returns 거리를 반환
 */
function GetDistance(pos1, pos2)
{
    return (Math.pow(pos1[0] - pos2[0], 2) + Math.pow(pos1[1] - pos2[1], 2));
}


function solution(m, n, startX, startY, balls) 
{
    var answer = [];
    let ballX = 0, ballY = 0;
    let disArr = [];
    for(e of balls)
    {
        ballX = e[0], ballY = e[1];
        disArr = [];
        if(ballX == startX)
        {
            if((m - ballX) > ballX)
            {
                disArr.push(GetDistance(e, [-startX, startY]));
            }
            else
            {
                disArr.push(GetDistance(e, [2*m - startX, startY]));
            }
            if((ballY - startY) > 0)
            {
                disArr.push(GetDistance(e, [startX, -startY]));
            }
            else
            {
                disArr.push(GetDistance(e, [startX, 2*n - startY]));
            }
        }
        else if(ballY == startY)
        {
            if((n - ballY) > ballY)
            {
                disArr.push(GetDistance(e, [startX, -startY]));
            }
            else
            {
                disArr.push(GetDistance(e, [startX, 2*n - startY]));
            }
            if((ballX - startX) > 0)
            {
                disArr.push(GetDistance(e, [-startX, startY]));
            }
            else
            {
                disArr.push(GetDistance(e, [2*m - startX, startY]));
            }
        }
        else
        {
            disArr.push(GetDistance(e, [-startX, startY]));
            disArr.push(GetDistance(e, [2*m - startX, startY]));
            disArr.push(GetDistance(e, [startX, -startY]));
            disArr.push(GetDistance(e, [startX, 2*n - startY]));
        }
        answer.push(Math.min(...disArr));
    }
    return answer;
}