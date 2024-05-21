import java.util.*;

class Solution {

    /**
     * 상담 유형 별로 {시작 시간, 종료 시간} 값을 가진 List 생성 하는 함수
     * @param k 유형의 가짓 수
     * @param reqs 전체 상담 요청 값들이 담긴 배열
     * @return timeTable List 를 반환
     */
    private List<List<Integer[]>> timeTable(int k, int[][] reqs) {
        List<List<Integer[]>> result = new ArrayList<>();
        for (int idx = 0; idx <= k; idx++) {
            result.add(new ArrayList<>());
        }
        for (int[] req : reqs) {
            int start = req[0];
            int end = start + req[1];
            int type = req[2];
            result.get(type).add(new Integer[] { start, end });
        }
        return result;
    }

    /**
     * 지금 입력 받은 유형의 상담원 수에 따라 발생하는 기다림 시간을 계산 하는 함수
     * @param gapTable 현재 유형의 기다림 시간 배열
     * @param timeTable 현재 유형의 요청 시간들이 담긴 List
     */
    private void calcTimeGap(int[] gapTable, List<Integer[]> timeTable) {
        for (int i = 0; i < gapTable.length; i++) {
            int maxPpl = i + 1;
            int count = 0;
            int gapTotal = 0;
            PriorityQueue<Integer> endQ = new PriorityQueue<>();
            for (Integer[] time : timeTable) {
                int nowStart = time[0];
                int nowEnd = time[1];
                while (!endQ.isEmpty() && nowStart >= endQ.peek()) {
                    endQ.poll();
                    count--;
                }
                if (endQ.isEmpty() || nowStart < endQ.peek()) {
                    count++;
                }
                if (count > maxPpl) {
                    int gap = endQ.poll() - nowStart;
                    nowEnd += gap;
                    gapTotal += gap;
                    count--;
                }
                endQ.add(nowEnd);
            }
            gapTable[i] = gapTotal;
        }
    }

    /**
     * DFS 알고리즘을 이용해 기다림 시간이 최소값이 되는 조합을 찾아 기다림 시간을 계산 및 반환 하는 함수
     * @param maxPpl 현재 유형에서 최대로 배정 할 수 있는 상담사의 수
     * @param gapTable 전체 유형의 상담사 배정 인원 수 별 기다림 시간 배열
     * @param type 현재 유형
     * @return 전체 조합중 기다림 시간의 최소값
     */
    private int getMinTimeGap(int maxPpl, int[][] gapTable, int type) {
        int minTotal = Integer.MAX_VALUE;
        for (int i = 0; i <= maxPpl; i++) {
            int value = gapTable[type][i];
            if (type < gapTable.length - 1) {
                value += getMinTimeGap(maxPpl - i, gapTable, type + 1);
            }
            minTotal = Math.min(minTotal, value);
        }
        return minTotal;
    }

    public int solution(int k, int n, int[][] reqs) {
        int answer = 0;
        List<List<Integer[]>> timeTable = timeTable(k, reqs);
        int[][] gapTable = new int[k + 1][n - k + 1];
        for (int i = 1; i <= k; i++) {
            calcTimeGap(gapTable[i], timeTable.get(i));
        }
        answer = getMinTimeGap(n - k, gapTable, 1);
        return answer;
    }
}