import java.util.ArrayList;
import java.util.HashSet;

class Solution {

    private class Block {
        ArrayList<Integer[]> node;
        int num;

        Block(int num) {
            node = new ArrayList<>();
            this.num = num;
        }
    }

    @SuppressWarnings("unchecked")
    public int solution(int[][] board) {
        int n = board.length;
        ArrayList<Block> blocks = new ArrayList<>();
        HashSet<Integer> blockNum = new HashSet<>();
        HashSet<Integer> blockDone = new HashSet<>();
        int index = -1;
        for (int y = n - 1; y >= 0; y--) {
            int now = 0;
            blockDone = (HashSet<Integer>) blockNum.clone();
            for (int x = 0; x < n; x++) {
                if (board[y][x] != now && !blockNum.contains(board[y][x])) {
                    now = board[y][x];
                    if (now == 0) {
                        continue;
                    }
                    blockNum.add(now);
                    blocks.add(new Block(now));
                    index++;
                    blocks.get(index).node.add(new Integer[] { y, x });
                } else {
                    now = board[y][x];
                    if (now == 0 || blockDone.contains(board[y][x])) {
                        continue;
                    }
                    blocks.get(index).node.add(new Integer[] { y, x });
                }
            }
        }
        boolean isChanged = true;
        int answer = 0;
        while (isChanged) {
            isChanged = false;
            int y, x, num;
            ArrayList<Block> delBlocks = new ArrayList<>();
            for (Block block : blocks) {
                if (block.node.size() < 2) {
                    continue;
                }
                boolean isAble = true;
                num = block.num;
                for (Integer[] pos : block.node) {
                    if (!isAble) {
                        break;
                    }
                    y = pos[0];
                    x = pos[1];
                    for (int i = y - 1; i >= 0; i--) {
                        if (board[i][x] == num) {
                            break;
                        }
                        if (board[i][x] != 0 && board[i][x] != num) {
                            isAble = false;
                            break;
                        }
                    }
                }
                if (isAble) {
                    for (int i = 0; i < board.length; i++) {
                        for (int j = 0; j < board.length; j++) {
                            if (board[i][j] == num) {
                                board[i][j] = 0;
                            }
                        }
                    }
                    answer++;
                    isChanged = true;
                    delBlocks.add(block);
                }
            }
            for (Block block : delBlocks) {
                blocks.remove(block);
            }
        }
        return answer;
    }
}
