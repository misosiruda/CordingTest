import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        int[] dx={-1,0,1,0};
        int[] dy={0,1,0,-1};
        int mapsX=maps.length;
        int mapsY=maps[0].length;
        Queue<Point> q=new LinkedList<>();
        int answer=1;
        maps[0][0]=0;
        q.offer(new Point(0,0));
        while(!q.isEmpty()){
            int len=q.size();
            for(int i=0;i<len;++i){
                Point tmp=q.poll();
                if(tmp.x==(mapsX-1)&&tmp.y==(mapsY-1)) return answer;
                for(int j=0;j<4;++j){
                    int nx=tmp.x+dx[j];
                    int ny=tmp.y+dy[j];
                    if(nx>=0&&nx<=mapsX-1&&ny>=0&&ny<=mapsY-1&&maps[nx][ny]==1){
                        maps[nx][ny]=0;
                        q.offer(new Point(nx,ny));
                    }
                }
            }
            answer++;
        }
        return -1;
    }
    public class Point{
        int x;
        int y;
        Point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
}