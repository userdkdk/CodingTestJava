import java.io.*;
import java.util.*;

public class Main {
    static int N, M, MIN, VC, EMPTY_COUNT; static int[][] map; static ArrayList<Node> starts;
    static int INF = 987654321; static boolean[][] visited;
    static int[] virus_loc;
    static int[] dr = {1,-1,0,0}; static int[] dc = {0,0,1,-1};
    static class Node {
        int r, c;
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
        @Override
        public String toString() {
            return "Node r: "+r+", c: "+c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MIN = INF;
        map = new int[N][N];
        virus_loc = new int[M];
        EMPTY_COUNT = 0;
        starts = new ArrayList<>();

        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                int cur = Integer.parseInt(st.nextToken());
                map[i][j] = cur;
                if (map[i][j]==2) {
                    starts.add(new Node(i,j));
                }
                if (map[i][j]==0) {
                    EMPTY_COUNT++;
                }
            }
        }
        VC = starts.size();
        comb(0,0);
        if (MIN==INF) {
            MIN = -1;
        }
        System.out.println(MIN);
    }
    static void comb(int depth, int idx) {
        if (depth==M) {
            MIN = Math.min(MIN, countTime());
            return;
        }
        for (int i=idx;i<VC;i++) {
            virus_loc[depth] = i;
            comb(depth+1,i+1);
        }
    }
    static int countTime() {
        // init map
        visited = new boolean[N][N];
        initMap();

        ArrayDeque<Node> dq = new ArrayDeque<>();
        for (int i=0;i<M;i++) {
            Node cur = starts.get(virus_loc[i]);
            dq.add(cur);
            visited[cur.r][cur.c] = true;
        }
        int time = 0;
        int amountLeft = EMPTY_COUNT;
        if (amountLeft==0) {
            return time;
        }
        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int i=0;i<size;i++) {
                Node node = dq.poll();
                int r = node.r;
                int c = node.c;
                for (int d=0;d<4;d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (!checkGo(nr,nc)) continue;
                    visited[nr][nc] = true;
                    if (map[nr][nc]==0) {
                        amountLeft--;
                    }
                    dq.add(new Node(nr,nc));
                }
            }
            time++;
            // check visited all
            if (amountLeft==0) {
                return time;
            }
        }

        return INF;
    }
    static boolean checkGo(int r, int c) {
        if (r<0 || r>=N || c<0 || c>=N || visited[r][c]) {
            return false;
        }
        return true;
    }
    static void initMap() {
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                if (map[i][j]==1) {
                    visited[i][j] = true;
                }
            }
        }
    }
}
