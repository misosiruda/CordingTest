import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    private Deque<Deque<Integer>> body = new ArrayDeque<>();
    private Deque<Integer> left = new ArrayDeque<>();
    private Deque<Integer> right = new ArrayDeque<>();

    private void ShiftRow() {
        int temp = left.pollLast();
        left.addFirst(temp);
        temp = right.pollLast();
        right.addFirst(temp);
        Deque<Integer> tempArr = body.pollLast();
        body.addFirst(tempArr);
    }

    private void Rotate() {
        int temp;
        Deque<Integer> tempArr;
        temp = left.pollFirst();
        tempArr = body.pollFirst();
        tempArr.addFirst(temp);
        temp = tempArr.pollLast();
        body.addFirst(tempArr);
        right.addFirst(temp);
        temp = right.pollLast();
        tempArr = body.pollLast();
        tempArr.addLast(temp);
        temp = tempArr.pollFirst();
        body.addLast(tempArr);
        left.addLast(temp);
    }

    public int[][] solution(int[][] rc, String[] operations) {
        for (int y = 0; y < rc.length; y++) {
            Deque<Integer> temp = new ArrayDeque<>();
            for (int x = 0; x < rc[0].length; x++) {
                if (x == 0) {
                    left.addLast(rc[y][x]);
                } else if (x == rc[0].length - 1) {
                    right.addLast(rc[y][x]);
                } else {
                    temp.addLast(rc[y][x]);
                }
            }
            body.addLast(temp);
        }
        for (String order : operations) {
            if (order.equals("Rotate")) {
                Rotate();
            } else if (order.equals("ShiftRow")) {
                ShiftRow();
            }
        }
        int[][] answer = new int[rc.length][rc[0].length];
        for (int i = 0; i < rc.length; i++) {
            Deque<Integer> temp = body.pollFirst();
            for (int j = 0; j < rc[0].length; j++) {
                if (j == 0) {
                    answer[i][j] = left.pollFirst();
                } else if (j == rc[0].length - 1) {
                    answer[i][j] = right.pollFirst();
                } else {
                    answer[i][j] = temp.pollFirst();
                }
            }
        }
        return answer;
    }
}