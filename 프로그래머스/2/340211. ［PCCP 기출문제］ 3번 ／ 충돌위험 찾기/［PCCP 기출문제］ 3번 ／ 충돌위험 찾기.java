
import java.util.Arrays;
import java.util.HashSet;

class Solution {

    private class Position {
        private int[] pos;
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

    private int[][] points;
    private HashSet<Position> visited;

    private class Robot {

        private Position pos;
        private int[] goalNow;
        private int[] routes;
        private int routeIndex = 0;
        private boolean isGoal = false;

        public boolean isGoal() {
            return isGoal;
        }

        public Robot(int[] routes) {
            this.routes = routes;
            this.pos = new Position(points[this.routes[0] - 1].clone());
            this.goalNow = points[this.routes[routeIndex++] - 1].clone();
        }

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
        boolean isOver = true;
        do {
            isOver = true;
            visited = new HashSet<>();
            for (Robot robot : robots) {
                if (robot.isGoal()) {
                    continue;
                }
                isOver = false;
                robot.move();
            }
            for (Position pos : visited) {
                if (pos.isCrashed) {
                    answer++;
                }
            }
        } while (!isOver);
        return answer;
    }
}