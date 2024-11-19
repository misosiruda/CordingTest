import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {

    private class Node {
        int up;
        int down;
        boolean isdel;

        Node(int index) {
            this.up = index - 1;
            this.down = index + 1;
            this.isdel = false;
        }
    }

    private void delete(Node[] list, int index) {
        int tempUp = list[index].up;
        int tempDown = list[index].down;
        if (tempUp > -1) {
            list[tempUp].down = tempDown;
        }
        if (tempDown < list.length) {
            list[tempDown].up = tempUp;
        }
        list[index].isdel = true;
    }

    private void unDo(Node[] list, int index) {
        int up = list[index].up;
        int down = list[index].down;
        while (up > -1 && list[up].isdel) {
            up = list[index].up;
        }
        while (down < list.length && list[down].isdel) {
            down = list[index].down;
        }
        if (up > -1) {
            list[up].down = index;
        }
        list[index].up = up;
        list[index].down = down;
        if (down < list.length) {
            list[down].up = index;
        }
        list[index].isdel = false;
    }

    public String solution(int n, int k, String[] cmd) {
        Node[] list = new Node[n];
        for (int i = 0; i < n; i++) {
            list[i] = new Node(i);
        }
        Deque<Integer> delIndex = new ArrayDeque<>();
        char[] result = new char[n];
        Arrays.fill(result, 'O');

        int indexNow = k;
        for (String c : cmd) {
            if (c.length() < 2) {
                if (c.equals("C")) {
                    int temp = list[indexNow].down;
                    if (temp == n) {
                        temp = list[indexNow].up;
                    }
                    delete(list, indexNow);
                    delIndex.addLast(indexNow);
                    indexNow = temp;
                } else {
                    unDo(list, delIndex.pollLast());
                }
            } else {
                String[] arr = c.split(" ");
                int count = Integer.parseInt(arr[1]);
                if (arr[0].equals("U")) {
                    for (int i = 0; i < count; i++) {
                        indexNow = list[indexNow].up;
                    }
                } else {
                    for (int i = 0; i < count; i++) {
                        indexNow = list[indexNow].down;
                    }
                }
            }
        }
        while (delIndex.size() > 0) {
            int index = delIndex.pollLast();
            result[index] = 'X';
        }

        String answer = new String(result);
        return answer;
    }
}