import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        Deque<Integer> stackAll = new ArrayDeque<>();
        Deque<Integer> stackRecent = new ArrayDeque<>();
        for (int i = 0; i < 3; i++) {
            stackRecent.add(0);
        }
        stackAll.add(0);
        for (Integer num : ingredient) {
            stackRecent.addLast(num);
            if (stackRecent.size() == 4) {
                boolean isOK = true;
                if (stackRecent.peekFirst() != 1) {
                    isOK = false;
                }
                stackRecent.addLast(stackRecent.pollFirst());
                if (stackRecent.peekFirst() != 2) {
                    isOK = false;
                }
                stackRecent.addLast(stackRecent.pollFirst());
                if (stackRecent.peekFirst() != 3) {
                    isOK = false;
                }
                stackRecent.addLast(stackRecent.pollFirst());
                if (stackRecent.peekFirst() != 1) {
                    isOK = false;
                }
                stackRecent.addLast(stackRecent.pollFirst());
                if (isOK) {
                    stackRecent.clear();
                    for (int i = 0; i < 3; i++) {
                        stackRecent.addFirst(stackAll.pollLast());
                    }
                    answer++;
                } else {
                    stackAll.addLast(stackRecent.pollFirst());
                }
            }
        }
        return answer;
    }
}