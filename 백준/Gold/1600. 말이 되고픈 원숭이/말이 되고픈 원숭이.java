import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K; static int[][] map; static int[][][] move;
    static boolean[][][] visited;
    static int[] horseDc = {-2,-1,1,2,-2,-1,1,2};
    static int[] horseDr = {1,2,2,1,-1,-2,-2,-1};
    static int[] dr = {1,-1,0,0}; static int[] dc = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        move = new int[K+1][N][M];
        visited = new boolean[K+1][N][M];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Queue<int[]> q = new ArrayDeque<>();
        // r, c, k, move
        q.add(new int[]{0,0,0,0});
        visited[0][0][0] = true;
        for (int i=0;i<=K;i++) {
            move[i][N-1][M-1] = -1;
        }
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0]==N-1 && cur[1]==M-1) continue;
            if (cur[2]<K) {
                for (int d=0;d<8;d++) {
                    int new_r = cur[0] + horseDr[d];
                    int new_c = cur[1] + horseDc[d];
                    if (!canGo(new_r,new_c, cur[2]+1,cur[3]+1)) continue;
                    move[cur[2]+1][new_r][new_c] = cur[3]+1;
                    q.add(new int[]{new_r,new_c,cur[2]+1,cur[3]+1});
                }
            }
            for (int d=0;d<4;d++) {
                int new_r = cur[0] + dr[d];
                int new_c = cur[1] + dc[d];
                if (!canGo(new_r,new_c, cur[2],cur[3]+1)) continue;
                move[cur[2]][new_r][new_c] = cur[3]+1;
                q.add(new int[]{new_r,new_c,cur[2],cur[3]+1});
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int k=0;k<=K;k++) {
            if (move[k][N-1][M-1]!=-1) ans = Math.min(ans,move[k][N-1][M-1]);
        }
        if (N==1 && M==1) System.out.println(0);
        else if (ans==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
//        for (int k=0;k<=K;k++) {
//            for (int i=0;i<N;i++) {
//                System.out.println(Arrays.toString(move[k][i]));
//            }
//            System.out.println();
//        }
    }
    static boolean canGo(int a, int b, int k, int v) {
        if (a<0 || a>=N || b<0 || b>=M || map[a][b]==1) return false;
        if (visited[k][a][b]) {
            return move[k][a][b] > v;
        }
        visited[k][a][b] = true;
        return true;
    }
}
