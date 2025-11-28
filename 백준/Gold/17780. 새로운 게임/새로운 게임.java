import java.io.*;
import java.util.*;

public class Main {
    static int N, K; static int[][] map; static int[] xr, xc, dir;
    static List<Integer>[][] map_list;
    static int[] dr = {0,0,0,-1,1}; static int[] dc = {0,1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        xr = new int[K];
        xc = new int[K];
        dir = new int[K];
        map_list = new ArrayList[N][N];
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                map_list[i][j] = new ArrayList<>();
            }
        }
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            xr[i] = Integer.parseInt(st.nextToken())-1;
            xc[i] = Integer.parseInt(st.nextToken())-1;
            dir[i] = Integer.parseInt(st.nextToken());
            map_list[xr[i]][xc[i]].add(i);
        }
        int ans = -1;
        out:
        for (int t=1;t<=1000;t++) {
            for (int k=0;k<K;k++) {
                if(moving(k)) {
                    ans = t;
                    break out;
                }
            }
        }
        System.out.println(ans);
    }
    private static boolean moving(int k) {
        int r = xr[k];
        int c = xc[k];
        int d = dir[k];
        int nr = r + dr[d];
        int nc = c + dc[d];

        // check bottom
        int index = map_list[r][c].indexOf(k);
        if (index!=0) return false;

        // check blue or out
        if (!canGo(nr,nc)) {
            dir[k] = changeDir(d);
            d = dir[k];
            nr = r + dr[d];
            nc = c + dc[d];
            if (!canGo(nr,nc)) {
                return false;
            }
        }

        // if red then reverse
        if (map[nr][nc]==1) {
            Collections.reverse(map_list[r][c]);
        }

        // merge block
        for (int i : map_list[r][c]) {
            map_list[nr][nc].add(i);
            xr[i] = nr;
            xc[i] = nc;
        }
        map_list[r][c] = new ArrayList<>();

        return map_list[nr][nc].size()>=4;
    }

    private static boolean canGo(int r, int c) {
        if (r<0 || r>=N || c<0 || c>=N || map[r][c]==2) {
            return false;
        }
        return true;
    }
    private static int changeDir(int d) {
        if (d%2==1) {
            return d+1;
        }
        return d-1;
    }
}
