import java.io.*;
import java.util.*;

public class Main {
    static int N, cl; static int[][] map; static int[][][] check_map;
    static boolean[][] visited;
    static Queue<Node> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        check_map = new int[N][N][2];
        q = new ArrayDeque<>();
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cl = 1;
        visited = new boolean[N][N];
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                if (map[i][j]==1 && !visited[i][j]) {
                    checkMap(i, j);
                }
            }
        }
        int ans = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int d=0;d<4;d++) {
                int new_r = node.r + dr[d];
                int new_c = node.c + dc[d];
                if (!canGo(new_r,new_c, 1)) continue;
                // 같은 섬일떈 더 짧은 거리가 아니면 패스
                if (check_map[new_r][new_c][0] == node.cl &&
                        check_map[new_r][new_c][1] <= node.value+1) continue;
                if (check_map[new_r][new_c][0] > 0 &&
                        check_map[new_r][new_c][0] != node.cl) {
                    ans = Math.min(ans,
                            check_map[node.r][node.c][1] + check_map[new_r][new_c][1]);
                    continue;
                }
                check_map[new_r][new_c][0] = node.cl;
                check_map[new_r][new_c][1] = node.value+1;
                q.offer(new Node(new_r,new_c,node.cl,node.value+1));
            }
        }

        System.out.println(ans);
//        for (int i=0;i<N;i++) {
//            for (int j=0;j<N;j++) {
//                System.out.print(Arrays.toString(check_map[i][j]));
//            }
//            System.out.println();
//        }
    }
    static int[] dr = {1,-1,0,0}; static int[] dc = {0,0,1,-1};
    static void checkMap(int a, int b) {
        Queue<int[]> mq = new ArrayDeque<>();

        mq.offer(new int[]{a,b});
        q.offer(new Node(a,b,cl,0));
        check_map[a][b][0] = cl;
        visited[a][b] = true;
        while (!mq.isEmpty()) {
            int[] cur = mq.poll();
            for (int d=0;d<4;d++) {
                int new_r = cur[0] + dr[d];
                int new_c = cur[1] + dc[d];
                if (!canGo(new_r,new_c, 0) || visited[new_r][new_c]) continue;
                mq.offer(new int[]{new_r,new_c});
                q.offer(new Node(new_r,new_c,cl,0));
                check_map[new_r][new_c][0] = cl;
                visited[new_r][new_c] = true;
            }
        }
        cl++;
    }
    static boolean canGo(int a, int b, int c) {
        if (a<0 || a>=N || b<0 || b>=N || map[a][b]==c) return false;
        return true;
    }
    static class Node {
        int r, c, cl, value;
        public Node(int r, int c, int cl, int value) {
            this.r = r;
            this.c = c;
            this.cl = cl;
            this.value = value;
        }
    }
}
