import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 1;
        int n = cards.length;
        int maxIndex = n / 3;
        boolean[] visited = new boolean[n];

        boolean isChecked = true;
        next: while (maxIndex <= n && isChecked) {
            maxIndex += 2;
            isChecked = false;
            if (maxIndex > n) {
                break;
            }
            for (int i = 0; i < maxIndex; i++) {
                for (int j = i + 1; j < maxIndex; j++) {
                    if (cards[i] + cards[j] == n + 1 && !visited[i] && !visited[j]) {
                        if (i >= n / 3) {
                            if (coin > 0) {
                                if (j >= n / 3) {
                                    if (coin > 1) {
                                        coin--;
                                    } else {
                                        continue;
                                    }
                                }
                                coin--;
                            } else {
                                continue;
                            }
                        } else if (j >= n / 3) {
                            if (coin > 0) {
                                coin--;
                            } else {
                                continue;
                            }
                        }
                        visited[i] = true;
                        visited[j] = true;
                        isChecked = true;
                        continue next;
                    }
                }
            }
        }

        answer = (maxIndex - n / 3) / 2;
        return answer;
    }
}