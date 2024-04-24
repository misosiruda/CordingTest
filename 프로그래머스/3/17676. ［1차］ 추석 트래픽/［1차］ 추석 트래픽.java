import java.util.ArrayList;

class Solution {
    public static int solution(String[] lines) {
    ArrayList<Double[]> time = new ArrayList<Double[]>();
    for (String str : lines) {
      String[] s = str.split(" ");
      String[] timeStr = s[1].split(":");
      Double timeNow = 0.0;
      Double calcTime =
        Math.round((Double.parseDouble(s[2].replace("s", ""))) * 1000) /
        1000.0 -
        0.001;
      timeNow += Double.parseDouble(timeStr[0]) * 3600;
      timeNow += Double.parseDouble(timeStr[1]) * 60;
      timeNow += Double.parseDouble(timeStr[2]) + 0.001;
      Double[] timeset = new Double[] {
        (Math.round((timeNow - calcTime) * 1000) / 1000.0),
        timeNow,
      };
      time.add(timeset);
    }
    Double start = 0.0, end = 0.0;
    int count = 0, maxcnt = 0;
    for (int i = 0; i < time.size(); i++) {
      // System.out.println(time.get(i)[0] + ", " + time.get(i)[1]);
      start = time.get(i)[1];
      end = start + 0.999;
      count = 1;
      for (int j = i + 1; j < time.size(); j++) {
        Double num = time.get(j)[0];
        if (num <= end) count++;
      }
      if (count > maxcnt) maxcnt = count;
    }
    return maxcnt;
  }
}