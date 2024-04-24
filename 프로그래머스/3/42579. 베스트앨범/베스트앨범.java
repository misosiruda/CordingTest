import java.util.*;

class Solution {

    class Music {

        private String genre;
        public int totalPlays;
        private int[][] playsArr = new int[2][2];

        public Music(String genre, int play, int index) {
            this.genre = genre;
            totalPlays = play;
            playsArr[0] = new int[] { play, index };
            playsArr[1] = null;
        }

        public boolean Contain(String name) {
            return this.genre.equals(name) ? true : false;
        }

        public void addPlay(int play, int index) {
            totalPlays += play;
            if (play > playsArr[0][0]) {
                playsArr[1] = playsArr[0].clone();
                playsArr[0] = new int[] { play, index };
            } else if (playsArr[1] == null) {
                playsArr[1] = new int[] { play, index };
            } else if (play > playsArr[1][0]) {
                playsArr[1] = new int[] { play, index };
            } else {
                return;
            }
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Music> musicList = new ArrayList<>();
        for (int i = 0; i < plays.length; i++) {
            boolean isContain = false;
            int indexGenre = 0;
            for (int j = 0; j < musicList.size(); j++) {
                if (musicList.get(j).Contain(genres[i])) {
                    isContain = true;
                    indexGenre = j;
                    break;
                }
            }
            if (isContain) {
                musicList.get(indexGenre).addPlay(plays[i], i);
            } else {
                musicList.add(new Music(genres[i], plays[i], i));
            }
        }
        Collections.sort(
            musicList,
            new Comparator<Music>() {
                @Override
                public int compare(Music o1, Music o2) {
                    return o2.totalPlays - o1.totalPlays;
                }
            }
        );
        ArrayList<Integer> answerList = new ArrayList<>();
        for (Music m : musicList) {
            answerList.add(m.playsArr[0][1]);
            if (m.playsArr[1] != null) {
                answerList.add(m.playsArr[1][1]);
            }
        }
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}