import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {

    private boolean isInside(int r, int c, Integer[] pos) {
        return pos[0] >= 0 && pos[0] < r && pos[1] >= 0 && pos[1] < c;
    }

    private boolean isValid(Integer[] pos, String[][] park) {
        return park[pos[0]][pos[1]].equals("-1");
    }

    private Integer[] getRightPos(Integer[] pos) {
        return new Integer[] { pos[0], pos[1] + 1 };
    }

    private Integer[] getBottomPos(Integer[] pos) {
        return new Integer[] { pos[0] + 1, pos[1] };
    }

    private Integer[] getEdgePos(Integer[] pos) {
        return new Integer[] { pos[0] + 1, pos[1] + 1 };
    }

    private boolean setNextRightList(int r, int c, Deque<Integer[]> rightList, String[][] park) {
        for (int i = 0; i < rightList.size(); i++) {
            Integer[] right = rightList.pollFirst();
            Integer[] rightNext = getRightPos(right);
            if (isInside(r, c, rightNext) && isValid(rightNext, park)) {
                rightList.addLast(rightNext);
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean setNextBottomList(int r, int c, Deque<Integer[]> bottomList, String[][] park) {
        for (int i = 0; i < bottomList.size(); i++) {
            Integer[] bottom = bottomList.pollFirst();
            Integer[] bottomNext = getBottomPos(bottom);
            if (isInside(r, c, bottomNext) && isValid(bottomNext, park)) {
                bottomList.addLast(bottomNext);
            } else {
                return false;
            }
        }
        return true;
    }

    private int getCount(int r, int c, Integer[] pos, String[][] park) {
        if (!isValid(pos, park)) {
            return 0;
        }
        Deque<Integer[]> rightList = new ArrayDeque<>();
        Deque<Integer[]> bottomList = new ArrayDeque<>();
        Deque<Integer[]> edgeList = new ArrayDeque<>();
        edgeList.add(pos);
        int cnt = 1;
        while (cnt <= r && cnt <= c) {
            if (!setNextRightList(r, c, rightList, park))
                return cnt;
            if (!setNextBottomList(r, c, bottomList, park))
                return cnt;
            Integer[] edge = edgeList.pollFirst();
            Integer[][] edgeNext = { getRightPos(edge), getBottomPos(edge), getEdgePos(edge) };
            for (Integer[] nPos : edgeNext) {
                if (!isInside(r, c, nPos) || !isValid(nPos, park))
                    return cnt;
            }
            rightList.add(edgeNext[0]);
            bottomList.add(edgeNext[1]);
            edgeList.add(edgeNext[2]);
            cnt++;
        }
        return --cnt;
    }

    public int solution(int[] mats, String[][] park) {
        int r = park.length;
        int c = park[0].length;

        int cnt = 0;
        for (int y = 0; y < r; y++) {
            for (int x = 0; x < c; x++) {
                Integer[] pos = { y, x };
                cnt = Math.max(cnt, getCount(r, c, pos, park));
            }
        }
        int answer = -1;
        Arrays.sort(mats);
        for (int mat : mats) {
            if (cnt < mat) {
                return answer;
            }
            answer = mat;
        }
        return cnt >= answer ? answer : -1;
    }
}