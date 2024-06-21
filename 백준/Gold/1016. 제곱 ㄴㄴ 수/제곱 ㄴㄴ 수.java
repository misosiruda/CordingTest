import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static class Sol {
        private int answer;

        public int getAnswer() {
            return answer;
        }

        public Sol() {
            this.answer = 0;
        }

        public void checkAnswer(long min, long max) {
            answer = (int) (max - min) + 1;
            boolean[] isAnswers = new boolean[(int) (max - min) + 1];
            for (long i = 2; i * i <= max; i++) {
                long pow = i * i;
                long tmp = min % pow != 0 ? min / pow + 1 : min / pow;
                for (long j = tmp; pow * j <= max; j++) {
                    int idx = (int) (pow * j - min);
                    if (!isAnswers[idx]) {
                        isAnswers[idx] = true;
                        answer--;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bf.readLine().split(" ");
        long min = Long.parseLong(strings[0]);
        long max = Long.parseLong(strings[1]);
        Sol sol = new Sol();
        sol.checkAnswer(min, max);
        System.out.println(sol.getAnswer());
    }
}
