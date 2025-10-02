import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K; static int[][] map; static boolean[][][] visited;
    static int[] dr = {1,-1,0,0}; static int[] dc = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][K+1];
        for (int i=0;i<N;i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j=0;j<M;j++) {
                map[i][j] = tmp[j]-'0';
            }
        }
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0,K,1,true));
        visited[0][0][K] = true;
        int ans = -1;
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.r==N-1 && node.c==M-1) {
                ans = node.value;
                break;
            }
            for (int d=0;d<4;d++) {
                int r = node.r + dr[d];
                int c = node.c + dc[d];
                // 4방향 체크
                if (!canGo(r,c)) continue;
                // 벽있는 경우
                if (map[r][c]==1 && node.k>0 && !visited[r][c][node.k-1]) {
                    if (node.day) {
                        // 낮인 경우
                        visited[r][c][node.k-1] = true;
                        q.add(new Node(r,c,node.k-1,node.value+1,!node.day));
                    } else {
                        // 밤인 경우
                        q.add(new Node(node.r,node.c,node.k,node.value+1,!node.day));
                    }
                }

                if (map[r][c]==0 && !visited[r][c][node.k]) {
                    visited[r][c][node.k] = true;
                    q.add(new Node(r,c,node.k,node.value+1,!node.day));
                }

            }
        }
        System.out.println(ans);
    }
    static boolean canGo(int a, int b) {
        if (a<0 || a>=N || b<0 || b>=M) return false;
        return true;
    }
    static class Node {
        int r, c, k, value;
        boolean day;
        public Node(int r, int c, int k, int value, boolean day) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.value = value;
            this.day = day;
        }
    }
}
