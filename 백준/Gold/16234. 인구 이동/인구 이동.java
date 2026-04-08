import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R; static int[][] map;
    static int[] dr = {1,-1,0,0}; static int[] dc = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean flag = true;
        boolean[][] visited;
        int t = -1;
        while (flag) {
            visited = new boolean[N][N];
            flag = false;
            for (int i=0;i<N;i++) {
                for (int j=0;j<N;j++) {
                    if (visited[i][j]) continue;
                    if (moving(i,j,visited)) flag = true;
                }
            }
//            printMap();
            t++;
        }
        System.out.println(t);
    }
    static void printMap() {
        for (int i=0;i<N;i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }
    static boolean moving(int r, int c, boolean[][] visited) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[r][c] = true;
        ArrayList<int[]> locs = new ArrayList<>();
        int sum = map[r][c];
        q.add(new int[]{r,c});
        locs.add(new int[]{r,c});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d=0;d<4;d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if (!canGo(nr,nc,visited, map[cur[0]][cur[1]])) continue;
                int[] next = new int[]{nr,nc};
                locs.add(next);
                q.add(next);
                visited[nr][nc] = true;
                sum += map[nr][nc];
            }
        }
        if (locs.size()==1) return false;
        int vc = sum/locs.size();
        for (int[] cur : locs) {
            map[cur[0]][cur[1]] = vc;
        }
        return true;
    }
    static boolean canGo(int r, int c, boolean[][] visited, int value) {
        if (r<0 || r>=N || c<0 || c>=N ||
            visited[r][c]) return false;
        int v = Math.abs(value-map[r][c]);
        return v>=L && v<=R;
    }
}
