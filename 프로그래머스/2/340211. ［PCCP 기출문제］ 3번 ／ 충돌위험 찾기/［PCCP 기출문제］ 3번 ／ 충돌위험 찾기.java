import java.util.Arrays;
import java.util.HashSet;

class Solution {

    /**좌표값들을 저장한 전역 변수 */
    private int[][] points;
    /**같은 좌표에 있는지 확인 하기 위한 HashSet */
    private HashSet<Position> visited;

    /**좌표값이 같은지 비교하려면 equals 와 hashCode 를 override 해야하기 때문에 int[] pos 를 씌워주는 class */
    private class Position {
        /**좌표값 */
        private int[] pos;
        /**우리가 찾아야 할건 충돌한 지점의 갯수기 때문에 hashSet 안에 지점들이 충돌 지점인지 파악해야한다 */
        private boolean isCrashed;

        public Position(int[] pos) {
            this.pos = pos;
            this.isCrashed = false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Position that = (Position) o;
            return Arrays.equals(pos, that.pos);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(pos);
        }
    }

    /**로봇의 정보들을 담을 클래스 */
    private class Robot {

        /**좌표값 */
        private Position pos;
        /**현재 이동하려는 좌표 */
        private int[] goalNow;
        /**전체 이동하는 포인트의 index 값을 담은 변수 */
        private int[] routes;
        /**현재 이동 목표 index */
        private int routeIndex = 0;
        /**최종 목표에 도달 했는가 확인 하는 변수 */
        private boolean isGoal = false;

        /**
         * 현재 최종 목표에 도달 했는가 확인 하는 함수
         * @return isGoal 반환
         */
        public boolean isGoal() {
            return isGoal;
        }

        /**
         * 로봇 클래스 생성자
         * pos 를 로봇의 루트 중 첫번째로 초기화
         * goalNow routeIndex 0부터 하나씩 늘리면서 쓸 예정
         * @param routes 이 로봇의 이동 경로 입력
         */
        public Robot(int[] routes) {
            this.routes = routes;
            this.pos = new Position(points[this.routes[0] - 1].clone());
            this.goalNow = points[this.routes[routeIndex++] - 1].clone();
        }

        /**
         * 로봇의 이동을 구현한 함수
         * visited hashSet 을 이용하여 충돌 체크도 함
         */
        public void move() {
            if (pos.pos[0] < goalNow[0]) {
                pos.pos[0]++;
            } else if (pos.pos[0] > goalNow[0]) {
                pos.pos[0]--;
            } else if (pos.pos[1] < goalNow[1]) {
                pos.pos[1]++;
            } else if (pos.pos[1] > goalNow[1]) {
                pos.pos[1]--;
            }
            if (pos.pos[0] == goalNow[0] && pos.pos[1] == goalNow[1]) {
                if (routeIndex == routes.length) {
                    isGoal = true;
                } else {
                    goalNow = points[this.routes[routeIndex++] - 1].clone();
                }
            }
            if (visited.contains(pos)) {
                visited.forEach(p -> p.isCrashed = p.equals(pos) || p.isCrashed);
            } else {
                visited.add(new Position(pos.pos));
            }
        }
    }

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        this.points = points;
        Robot[] robots = new Robot[routes.length];
        for (int i = 0; i < routes.length; i++) {
            robots[i] = new Robot(routes[i]);
        }
        /**전체 로봇이 최종 목표에 도달 했는지 확인할 용도의 boolean 변수 */
        boolean isOver = true;
        do { // 전체 로봇이 이동할 때 까지 반복
            isOver = true;
            visited = new HashSet<>();
            for (Robot robot : robots) { //로봇 갯수만큼 반복
                if (robot.isGoal()) {
                    continue;
                } // continue 문을 이용하여 로봇이 도착하면 isOver가 false가 안됨
                isOver = false;
                robot.move();
            }
            for (Position pos : visited) { // 충돌은 중복이 있을 수 있기때문에 따로 세어 주어야 한다.
                if (pos.isCrashed) {
                    answer++;
                }
            }
        } while (!isOver);
        return answer;
    }
}