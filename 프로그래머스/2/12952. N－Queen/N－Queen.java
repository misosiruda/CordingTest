import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    int answer;
    int n;

    void DFS(int[] arr) {
        int i = 0;
        for (i = 0; i < n; i++) {
            if (arr[i] == -1) {
                break;
            }
        }
        if (i == n) {
            answer++;
            return;
        }
        Deque<Integer> num = new ArrayDeque<>();
        for (int j = 0; j < n; j++) {
            num.addLast(j);
        }
        next: while (num.size() > 0) {
            int cur = num.pollFirst();
            for (int j = 0; j < i; j++) {
                if (arr[j] == cur) {
                    continue next;
                }
            }
            for (int j = 0; j < i; j++) {
                if (i - j == Math.abs(arr[j] - cur)) {
                    continue next;
                }
            }
            int[] arrTemp = arr.clone();
            arrTemp[i] = cur;
            DFS(arrTemp);
        }
    }

    public int solution(int n) {
        this.n = n;
        answer = 0;
        int[] arr = new int[n];
        Arrays.fill(arr, -1);
        DFS(arr);
        return answer;
    }
}