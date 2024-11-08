class Solution {

    /**
     * 퍼즐 풀기 위해 Puzzle class를 만들어서 필요한 데이터를 관리합니다.
     */
    private class Puzzle {
        /**퍼즐 난도 배열 */
        int[] diffArr;
        /**퍼즐 푸는 시간 배열 */
        int[] timeArr;
        /**각 퍼즐당 추가시간 배열 */
        int[] additionalTimeArr;
        /**현재 숙련도 레벨일 때 걸린 시간 */
        long totalTime;

        /**
         * Puzzle class 초기화
         * @param diffArr 퍼즐 난도 배열
         * @param timeArr 퍼즐 푸는 시간 배열
         */
        public Puzzle(int[] diffArr, int[] timeArr) {
            this.diffArr = diffArr;
            this.timeArr = timeArr;
            additionalTimeArr = new int[diffArr.length];
            additionalTimeArr[0] = 0; // 모든 케이스 에서 첫번째 문제의 난도는 1 이니 추가시간이 안걸린다.
            for (int i = 1; i < diffArr.length; i++) {
                additionalTimeArr[i] = this.timeArr[i - 1] + this.timeArr[i];
            }
        }

        /**
         * 현재 숙련도 별 걸린 시간을 반환 합니다.
         * @param level 현재 숙련도
         * @return 현재 숙련도 일 때 걸린 시간
         */
        public long calculateTime(int level) {
            totalTime = 0;
            for (int i = 0; i < diffArr.length; i++) {
                int gap = diffArr[i] - level;
                if (gap > 0) {
                    totalTime += additionalTimeArr[i] * gap + timeArr[i];
                } else {
                    totalTime += timeArr[i];
                }
            }
            return totalTime;
        }
    }

    public int solution(int[] diffs, int[] times, long limit) {
        int start = 0;
        int end = 1000000;
        int answer = 0;
        Puzzle puzzle = new Puzzle(diffs, times);
        while (start != end) { // answer의 최대는 난도의 최대
            answer = (start + end) / 2;
            long nowTime = puzzle.calculateTime(answer);
            if (nowTime > limit) {
                start = ++answer;
            } else {
                end = answer;
            }
        }
        return answer == 0? 1 : answer;
    }
}