import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
for (int x = 0; x < photo.length; x++) {
int tempAnswer = 0;
for (int y = 0; y < photo[x].length; y++) {
int yearningIdx = Arrays.asList(name).indexOf(photo[x][y]);
tempAnswer += yearningIdx >= 0 ? yearning[yearningIdx] : 0;
}
// tempAnswer에 result와 같은 정수가 나오는거까지 확인
answer[x] += tempAnswer;
}
return answer;
    }
}