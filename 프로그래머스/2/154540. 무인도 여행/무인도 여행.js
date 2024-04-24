let map = [];
// 이어진 무인도 를 탐색할 큐
//   [탐색할 좌표]
// 이어진 무인도가 순환노드일 수 있으니 visited 갱신은 탐색할 좌표를 정할때 갱신한다.
let queue = [];
let visited = [];
let foods = [];// 큐에다가 식량값을 더해주면 다음 노드가 복수일때 겹치는 값이 나온다. 고로 따로 계산
let fNum = -1;// 이어진 무인도끼리 더해주기 위한 index 값 while(큐) 밖에서 방문하지 않은 무인도를 찾았을때 ++ 해준다.

/**
 * visited 배열을 초기화 즉 X 인 곳을 방문한 곳 으로 바꿔주고 map 과 크기가 똑같은 2차원 배열로 만드는 함수
 */
function UpdateVisited()
{
    for(let i=0;i<map.length;i++)
    {
        visited.push([]);
        for(let j=0;j<map[0].length;j++)
        {
            if(map[i][j] == 'X') visited[i].push(false);
            else visited[i].push(true);
        }
    }
}

/**
 * 입력받은 좌표값이 맵 범위를 벗어나는지 확인 하는 함수
 * @param {Array} pos 입력받은 좌표값
 * @returns 벗어나면 false 들어가면 true
 */
function CheckOutOfRange(pos)
{
    if(pos[0] < 0 || map.length <= pos[0]) return false;
    else if(pos[1] < 0 || map[0].length <= pos[1]) return false;
    else return true;
}

/**
 * 다음 이동 할 좌표를 구해 배열로 정리해 보내주는 함수
 * @param {Array} pos 현재 위치 좌표
 * @returns 다음 위치 배열 [상, 하, 좌, 우] 이중 범위를 벗어나는 좌표는 삭제 된 상태
 */
function NextDic(pos)
{
    let dics = [];
    let dicU = [], dicD = [], dicL = [], dicR = [];
    dicU = [pos[0] + 1, pos[1]];
    dicD = [pos[0] - 1, pos[1]];
    dicL = [pos[0], pos[1] - 1];
    dicR = [pos[0], pos[1] + 1];
    if(CheckOutOfRange(dicU)) dics.push(dicU);
    if(CheckOutOfRange(dicD)) dics.push(dicD);
    if(CheckOutOfRange(dicL)) dics.push(dicL);
    if(CheckOutOfRange(dicR)) dics.push(dicR);
    return dics;
}

function solution(maps) 
{
    map = maps;
    UpdateVisited();
    let qN = [];
    let nextDics = [];
    for(let i=0;i<map.length;i++)
    {
        for(let j=0;j<map[0].length;j++)
        {
            if(visited[i][j])
            {
                visited[i][j] = false;
                queue.push([i, j]);
                fNum++;
                foods.push(0);
                while(queue.length > 0)
                {
                    qN = queue.shift();
                    foods[fNum] += Number(map[qN[0]][qN[1]]);
                    nextDics = NextDic(qN);
                    for(e of nextDics)
                    {
                        if(visited[e[0]][e[1]])
                        {
                            visited[e[0]][e[1]] = false;
                            queue.push(e);
                        }
                    }
                }
            }
        }
    }
    if(fNum == -1) return [-1];
    return foods.sort((a, b) => a - b);
}