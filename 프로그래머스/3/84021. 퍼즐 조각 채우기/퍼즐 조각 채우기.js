/**
 * 입력받은 좌표값이 맵 범위를 벗어나는지 확인 하는 함수
 * @param {Array} pos 입력받은 좌표값
 * @returns 벗어나면 false 들어가면 true
 */
function CheckOutOfRange(pos, map)
{
    if (pos[0] < 0 || map.length <= pos[0]) return false;
    else if (pos[1] < 0 || map[0].length <= pos[1]) return false;
    else return true;
}

/**
 * 현재 위치에 다음 방향을 더해서 반환 하는 함수
 * @param {Array} pos 현재 위치 배열
 * @param {Number} d 다음 이동할 방향 0~3
 * @returns 다음 위치 배열
 */
function NextPos(pos, d)
{
    let posN = [];
    posN[0] = pos[0];
    posN[1] = pos[1];
    if (d == 0)      // + [1,0]
    {
        posN[0] += 1;
    }
    else if (d == 1) // + [0, 1]
    {
        posN[1] += 1;
    }
    else if (d == 2) // + [-1, 0]
    {
        posN[0] += -1;
    }
    else            // + [0, -1]
    {
        posN[1] += -1;
    }
    return posN;
}

function SeperatePuzzle(map)
{
    let queue = [];
    let piece = [];
    let pos = [];
    let Npos = [];
    let pieceArr = [];
    for (let i = 0; i < map.length; i++)
    {
        for (let j = 0; j < map[0].length; j++)
        {
            if (map[i][j] === 1)
            {
                queue = [];
                piece = [];
                pos = [];
                Npos = [];
                map[i][j] = 0;
                queue.push([i, j]);
                minX = map.length, minY = map[0].length;
                while (queue.length > 0)
                {
                    pos = queue.shift();
                    piece.push(pos);
                    for (let r = 0; r < 4; r++)
                    {
                        Npos = NextPos(pos, r);
                        if (CheckOutOfRange(Npos, map) && map[Npos[0]][Npos[1]] === 1)
                        {
                            map[Npos[0]][Npos[1]] = 0;
                            queue.push(Npos);
                        }
                    }
                }
                pieceArr.push(moveBlock(piece));
            }
        }
    }
    return pieceArr;
}
function moveBlock(block)
{
    let minX = Math.min(...block.map(v => v[0]));
    let minY = Math.min(...block.map(v => v[1]));

    return block.map(v => [v[0] - minX, v[1] - minY]).sort();
}

function rotate(block)
{
    let max = Math.max(...block.map(v => Math.max(v[0], v[1])));
    let rotateBlock = block.map(v => [max - v[1], v[0]]);

    return moveBlock(rotateBlock);
}

function ComparePuzzle(blanks, puzzle)
{
    for (bl of blanks)
    {
        if (JSON.stringify(bl) === JSON.stringify(puzzle))
        {
            bl.splice(0);
            return puzzle.length;
        }
        for (let i = 0; i < 3; i++)
        {
            puzzle = rotate(puzzle);
            if (JSON.stringify(bl) === JSON.stringify(puzzle))
            {
                bl.splice(0);
                return puzzle.length;
            }
        }
    }
    return 0;
}

function solution(game_board, table)
{
    var answer = 0;
    let puzzlePieceArr = SeperatePuzzle(table);
    puzzlePieceArr.map(e => e.sort());
    puzzlePieceArr.sort((a, b) => b.length - a.length);
    console.log(puzzlePieceArr);
    game_board = game_board.map(colon =>
    {
        return colon.map(e =>
        {
            if (e === 0) return 1;
            else return 0;
        });
    });
    let blankPieceArr = SeperatePuzzle(game_board);
    blankPieceArr.map(e => e.sort());
    blankPieceArr.sort((a, b) => b.length - a.length);
    console.log(blankPieceArr);
    puzzlePieceArr.map(puzzle =>
    {
        answer += ComparePuzzle(blankPieceArr, puzzle);
    });


    return answer;
}