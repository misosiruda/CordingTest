import java.util.*;

class Solution {

    /**
     * 과목 명이 담긴 hashMap 과목에 중복이 없다는 점을 이용했다.
     */
    private HashMap<Integer, String> subjectMap;

    /**
     * HH:MM 포맷인 String 을 분으로 바꿔 반환하는 함수
     * 
     * @param hour HH:MM 포멧의 String
     * @return int MM
     */
    private int getHourToMinute(String hour) {
        String[] hourArr = hour.split(":");
        return Integer.parseInt(hourArr[0]) * 60 + Integer.parseInt(hourArr[1]);
    }

    /**
     * Plans 배열을 다루기 쉽게 가공하는 함수
     * 
     * @param plans 입력으로 받은 plans 배열
     * @return 가공된 replans 배열 모두 int 다
     */
    private int[][] reFactoryPlans(String[][] plans) {
        Arrays.sort(plans, (a, b) -> a[1].compareTo(b[1]));
        int[][] rePlans = new int[plans.length][3];
        for (int i = 0; i < plans.length; i++) {
            rePlans[i][0] = i;
            rePlans[i][1] = getHourToMinute(plans[i][1]);
            rePlans[i][2] = Integer.parseInt(plans[i][2]);
            subjectMap.put(i, plans[i][0]);
        }
        return rePlans;
    }

    /**
     * 순서대로 plans 를 읽어 조건에 맞게 처리해 끝마친 과목명을 String[]에 담아 반환 하는 함수
     * 
     * @param plans {과목 id, 시작 분, 길이} 가 담긴 배열
     * @return 끝마친 순서대로 정렬된 과목명이 담긴 배열
     */
    private String[] findTask(int[][] plans) {
        String[] result = new String[plans.length];
        Deque<Integer[]> taskQ = new ArrayDeque<>();
        int time = 0;
        int index = 0;
        for (int[] now : plans) {
            int id = now[0];
            int start = now[1];
            int duration = now[2];
            while (!taskQ.isEmpty() && time + taskQ.peekFirst()[1] <= start) {
                time += taskQ.peekFirst()[1];
                result[index++] = subjectMap.get(taskQ.pollFirst()[0]);
            }
            if (taskQ.isEmpty()) {
                taskQ.addLast(new Integer[] { id, duration });
                time = start;
            } else if (time + taskQ.peekFirst()[1] > start) {
                taskQ.peekFirst()[1] -= (start - time);
                taskQ.addFirst(new Integer[] { id, duration });
                time = start;
            }
        }
        while (!taskQ.isEmpty()) {
            time += taskQ.peekFirst()[1];
            result[index++] = subjectMap.get(taskQ.pollFirst()[0]);
        }
        return result;
    }

    public String[] solution(String[][] plans) {
        subjectMap = new HashMap<>();
        int[][] rePlans = reFactoryPlans(plans);
        return findTask(rePlans);
    }
}