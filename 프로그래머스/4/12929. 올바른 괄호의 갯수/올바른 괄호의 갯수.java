class Solution {
    public static int DFS(int open, int close, int n) {
    if (open == n) {
      return 1;
    } else if (open - close < 0) {
      return 0;
    }
    int num = 0;
    num += DFS(open + 1, close, n);
    num += DFS(open, close + 1, n);
    return num;
  }

  public static int solution(int n) {
    int answer = 0;
    answer = DFS(0, 0, n);
    return answer;
  }
}