import java.io.*;
import java.util.*;

public class Main {
    static int N; static int[][] locs; static int[] moves; static int ans;
    static int[][] blue_ways = {{},{10,13,16,19,25,30,35,40},
            {20,22,24,25,30,35,40}, {30,28,27,26,25,30,35,40}};
    static boolean[] isArrived;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = 10;
        locs = new int[4][2];
        moves = new int[N];
        ans = 0;
        isArrived = new boolean[4];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            moves[i] = Integer.parseInt(st.nextToken());
        }
        moving(0,0);
        System.out.println(ans);
    }
    static void moving(int m, int points) {
        ans = Math.max(ans,points);
        if (m==10) {
            return;
        }
        for (int i=0;i<4;i++) {
            if (isArrived[i]) continue;
            int now_loc = locs[i][0];
            int now_blue = locs[i][1];
            int next_loc = now_loc + moves[m];
            int next_blue = now_blue;
            if (now_blue==0) {
                if (next_loc>20) {
                    isArrived[i] = true;
                    moving(m+1,points);
                    isArrived[i] = false;
                    continue;
                }
                if (next_loc%5==0 && next_loc<20) {
                    next_blue = next_loc/5;
                    next_loc = 0;
                }
            } else {
                if (next_loc>=blue_ways[now_blue].length) {
                    isArrived[i] = true;
                    moving(m+1,points);
                    isArrived[i] = false;
                    continue;
                }
            }
            if (!canGo(i,next_loc,next_blue)) continue;
            int point = next_blue==0 ? 2*next_loc : blue_ways[next_blue][next_loc];
            locs[i][0] = next_loc;
            locs[i][1] = next_blue;
            moving(m+1,points + point);
            locs[i][0] = now_loc;
            locs[i][1] = now_blue;
        }
    }
    static boolean canGo(int who, int nl, int nb) {
        for (int i=0;i<4;i++) {
            if (i==who || isArrived[i]) continue;
            if (compPoint(locs[i][0],locs[i][1])==compPoint(nl,nb)) return false;
        }
        return true;
    }
    static int compPoint(int loc, int blue) {
        int point  = blue==0 ? 2*loc : blue_ways[blue][loc];
        if (blue!=0) {
            if (point==40) return point;
            if (point==10 || point==20) return point;
            if (point==30 && loc==0) return point;
            point*=100;
        }
        return point;
    }
}
