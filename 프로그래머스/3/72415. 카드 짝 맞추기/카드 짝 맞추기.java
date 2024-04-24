import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Permutation {

    private ArrayList<Integer[]> allArr;

    public Permutation(Integer[] arr) {
        allArr = new ArrayList<>();
        permutation(arr, 0, arr.length);
    }

    public ArrayList<Integer[]> getPer() {
        return allArr;
    }

    private void permutation(Integer[] arr, int depth, int r) {
        if (depth == r) { //depth와 r이 같아지면 allArr 에 추가
            allArr.add(arr.clone());
            return;
        }
        for (int i = depth; i < arr.length; i++) {
            this.Swap(arr, depth, i);
            this.permutation(arr, depth + 1, r); //재귀함수 들어갈때 depth+1
            this.Swap(arr, depth, i); //복구
        }
    }

    private void Swap(Integer[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}

class TwoWay {

    private ArrayList<Integer[]> cardinfo;
    private ArrayList<Integer[]> ways;

    public TwoWay(ArrayList<Integer[]> cardinfo) {
        this.cardinfo = cardinfo;
        ways = new ArrayList<>();
    }

    public void chooseWay(int i, Integer[] cardlist, Integer[] thisWay) {
        if (i == cardlist.length) {
            ways.add(thisWay);
            return;
        }
        int idx1 = -1;
        int idx2 = -1;
        for (int j = 0; j < cardinfo.size(); j++) {
            if (cardinfo.get(j)[2] == cardlist[i]) {
                if (idx1 == -1) {
                    idx1 = j;
                } else {
                    idx2 = j;
                }
            }
        }
        Integer[] arr = thisWay.clone();
        arr[2 * i] = idx1;
        arr[2 * i + 1] = idx2;
        chooseWay(i + 1, cardlist, arr);
        arr = thisWay.clone();
        arr[2 * i] = idx2;
        arr[2 * i + 1] = idx1;
        chooseWay(i + 1, cardlist, arr);
    }

    public ArrayList<Integer[]> getWays() {
        return ways;
    }
}

class Solution {

    private int wayToCard(int[][] board, Integer[] pos, Integer[] target) {
        int[] dy = { -1, 1, 0, 0 };
        int[] dx = { 0, 0, -1, 1 };

        ArrayList<int[]> queue = new ArrayList<>();
        queue.add(new int[] { pos[0], pos[1], 0 });
        while (queue.size() > 0) {
            int[] qNow = queue.get(0);
            queue.remove(0);
            int y = qNow[0];
            int x = qNow[1];
            int count = qNow[2];

            if (y == target[0] && x == target[1]) {
                board[y][x] = 0;
                return ++count;
            }

            for (int i = 0; i < 4; i++) {
                int ny = y;
                int nx = x;
                while (0 <= ny + dy[i] && ny + dy[i] < board.length && 0 <= nx + dx[i] && nx + dx[i] < board[0].length) {
                    ny += dy[i];
                    nx += dx[i];
                    if (board[ny][nx] != 0) break;
                }
                if (ny == y && nx == x) continue;
                queue.add(new int[] { y + dy[i], x + dx[i], count + 1 });
                queue.add(new int[] { ny, nx, count + 1 });
            }
        }
        return 0;
    }

    private ArrayList<Integer[]> bestWay(ArrayList<Integer[]> cardinfo, ArrayList<Integer[]> cardlist) {
        TwoWay tw = new TwoWay(cardinfo);
        for (int j = 0; j < cardlist.size(); j++) {
            Integer[] thisWay = new Integer[cardinfo.size()];
            tw.chooseWay(0, cardlist.get(j), thisWay);
        }
        return tw.getWays();
    }

    public int solution(int[][] board, int r, int c) {
        ArrayList<Integer[]> cardinfo = new ArrayList<>(); //{y좌표, x좌표, 카드숫자}
        Integer[] cardnums;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                int card = board[y][x];
                if (card != 0) {
                    cardinfo.add(new Integer[] { y, x, card });
                }
            }
        }
        cardnums = new Integer[cardinfo.size() / 2];
        Arrays.fill(cardnums, 0);
        for (Integer[] info : cardinfo) {
            int card = info[2];
            for (int i = 0; i < cardnums.length; i++) {
                int check = cardnums[i];
                if (check == card) break; else if (check == 0) {
                    cardnums[i] = card;
                    break;
                }
            }
        }

        Permutation p = new Permutation(cardnums);
        ArrayList<Integer[]> cardlist = p.getPer();

        ArrayList<Integer[]> ways = bestWay(cardinfo, cardlist);

        int min = 999999999;
        for (Integer[] way : ways) {
            Integer[] pos = new Integer[] { r, c };
            int answer = 0;
            Integer[] target = new Integer[2];
            int[][] map = new int[board.length][];
            for (int i = 0; i < board.length; i++) {
                map[i] = board[i].clone();
            }
            for (int i = 0; i < way.length; i++) {
                target = new Integer[] { cardinfo.get(way[i])[0], cardinfo.get(way[i])[1] };
                answer += wayToCard(map, pos, target);
                pos = target.clone();
            }
            min = min > answer ? answer : min;
        }

        return min;
    }
}
