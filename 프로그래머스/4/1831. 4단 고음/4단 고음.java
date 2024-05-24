import java.util.*;

class Solution {

    private int getCountWithDFS(int n, int star, int plus) {
        int count = 0;
        if (n == 1 && plus % 2 == 0 && plus / 2 == star) {
            return 1;
        }
        for (int i = 0; i < n; i++) {
            if ((n - i) % 3 == 0) {
                int allPlus = plus + i;
                int needStart = (int) (Math.ceil((double) allPlus / 2)) - star;
                if (n - i < Math.pow(3, needStart)) {
                    return count;
                }
                if ((star + 1) * 2 > allPlus) {
                    continue;
                }
                count += getCountWithDFS((n - i) / 3, star + 1, allPlus);
            }
        }
        return count;
    }

    public int solution(int n) {
        return getCountWithDFS(n, 0, 0);
    }
}
