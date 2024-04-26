import copy

def solution(m, n, board):
    board = [list(row) for row in board]
    score = 0

    while True:
        board, erased = erase_block(m, n, board)
        #print(erased)
        if not erased:  # 지워지는게 없으면 종료
            return score
        score += erased
        board = erase_empty(m, n, board)
    return score

def erase_block(m, n, board):
    tmp = copy.deepcopy(board)
    check = [[0] * n for _ in range(m)]

    # (r, c)
    # search_range = [[(0, -1), (-1, -1), (-1, 0)],
    #                 [(0, 1), (-1, 1), (-1, 0)],
    #                 [(0, -1), (1, -1), (1, 0)],
    #                 [(0, 1), (1, 1), (1, 0)]]

    for r in range(m):
        for c in range(n):
            if board[r][c] == ' ':
                continue

            if 'A'<=board[r][c]<='Z':
                if 0 <= r - 1 < m and 0 <= c - 1 < n and len(
                        set([board[r][c], board[r][c - 1], board[r - 1][c - 1], board[r - 1][c]])) == 1:
                    check[r][c] = 1;
                    tmp[r][c] = ' '
                    check[r][c-1] = 1;
                    tmp[r][c-1] = ' '
                    check[r - 1][c - 1] = 1;
                    tmp[r - 1][c - 1] = ' '
                    check[r - 1][c] = 1;
                    tmp[r - 1][c] = ' '

                elif 0 <= r - 1 < m and 0 <= c + 1 < n and len(
                        set([board[r][c], board[r][c + 1], board[r - 1][c + 1], board[r - 1][c]])) == 1:
                    check[r][c] = 1;
                    tmp[r][c] = ' '
                    check[r][c + 1] = 1;
                    tmp[r][c + 1] = ' '
                    check[r - 1][c + 1] = 1;
                    tmp[r - 1][c + 1] = ' '
                    check[r - 1][c] = 1;
                    tmp[r - 1][c] = ' '

                elif 0 <= r + 1 < m and 0 <= c - 1 < n and len(
                        set([board[r][c], board[r][c - 1], board[r + 1][c - 1], board[r + 1][c]])) == 1:
                    check[r][c] = 1;
                    tmp[r][c] = ' '
                    check[r][c - 1] = 1;
                    tmp[r][c - 1] = ' '
                    check[r + 1][c - 1] = 1;
                    tmp[r + 1][c - 1] = ' '
                    check[r + 1][c] = 1;
                    tmp[r + 1][c] = ' '

                elif 0 <= r + 1 < m and 0 <= c + 1 < n and len(
                        set([board[r][c], board[r][c + 1], board[r + 1][c + 1], board[r + 1][c]])) == 1:
                    check[r][c] = 1;
                    tmp[r][c] = ' '
                    check[r][c + 1] = 1;
                    tmp[r][c + 1] = ' '
                    check[r + 1][c + 1] = 1;
                    tmp[r + 1][c + 1] = ' '
                    check[r + 1][c] = 1;
                    tmp[r + 1][c] = ' '

            else:
                continue

    return tmp, sum([sum(row) for row in check])


def erase_empty(m, n, board):
    tmp = copy.deepcopy(board)
    for c in range(n):
        emp = []; new_column = []
        column = [board[r][c] for r in range(m)]
        while column:
            element = column.pop(0)
            if element == ' ':
                emp.append(element)
            else:
                new_column.append(element)
        new_column = emp + new_column
        for r in range(m):
            tmp[r][c] = new_column[r]
    return tmp