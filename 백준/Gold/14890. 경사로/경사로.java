import java.awt.print.Pageable;
import java.io.*;
import java.util.*;

public class Main {
    static int N, L; static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                int cur = Integer.parseInt(st.nextToken());
                map[i][j] = cur;
            }
        }
        int ans = 0;
        for (int i=0;i<N;i++) {
            int[] cur = getColumn(i);
            if (checkMove(cur)) {
                ans++;
            }
            cur = getRow(i);
            if (checkMove(cur)) {
                ans++;
            }
        }
        System.out.println(ans);
    }
    static boolean checkMove(int[] cur) {
        boolean[] line = new boolean[N];
        for (int i=0;i<N-1;i++) {
            // same
            if (cur[i]==cur[i+1]) {
                continue;
            }
            if (Math.abs(cur[i]-cur[i+1])>1) {
                return false;
            }
            // up
            if (cur[i]<cur[i+1]) {
                if (!checkLine(i,cur,line,-1)) {
                    return false;
                }
            } else {
                // down
                if (!checkLine(i+1,cur,line,1)) {
                    return false;
                }
            }
        }
        return true;
    }
    static boolean checkLine(int loc, int[] cur, boolean[] line, int pos) {
        for (int i=0;i<L;i++) {
            int now = loc + i*pos;
            if (now<0 || now>=N || cur[loc]!=cur[now] || line[now]) {
                return false;
            }
            line[now] = true;
        }
        return true;
    }
    static int[] getColumn(int loc) {
        int[] res = new int[N];
        for (int i=0;i<N;i++) {
            res[i] = map[i][loc];
        }
        return res;
    }
    static int[] getRow(int loc) {
        return Arrays.copyOfRange(map[loc],0,N);
    }
}
