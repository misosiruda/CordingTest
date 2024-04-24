import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {

        ArrayList<Integer> indexes = new ArrayList<>();
        int extidx = -1;
        if (ext.equals("code")) {
            extidx = 0;
        } else if (ext.equals("date")) {
            extidx = 1;
        } else if (ext.equals("maximum")) {
            extidx = 2;
        } else {
            extidx = 3;
        }

        for (int i = 0; i < data.length; i++) {
            if (data[i][extidx] < val_ext) {
                indexes.add(i);
            }
        }
        int[][] answer = new int[indexes.size()][];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = data[indexes.get(i)];
        }
        if (sort_by.equals("code")) {
            Arrays.sort(answer, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[0] - b[0];
                }
            });
        } else if (sort_by.equals("date")) {
            Arrays.sort(answer, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[1] - b[1];
                }
            });
        } else if (sort_by.equals("maximum")) {
            Arrays.sort(answer, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[2] - b[2];
                }
            });
        } else {
            Arrays.sort(answer, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[3] - b[3];
                }
            });
        }

        return answer;
    }
}