import java.io.*;
import java.util.*;

public class Main {
    static int H, W, N; static char[][] map; static int[] loc;
    static int[] dr = {0,0,1,-1}; static int[] dc = {1,-1,0,0};
    static int max = 1_000_000_000;
    static class Node {
        int h, w, visited, dist;
        public Node(int h, int w, int visited, int dist) {
            this.h = h;
            this.w = w;
            this.visited = visited;
            this.dist = dist;
        }
        @Override
        public String toString() {
            return "Node h: "+h+", w: "+w+", visited: "+visited+", dist: "+dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ArrayList<Integer> ans = new ArrayList<>();
        while (H!=0) {
            map = new char[H][W];
            loc = new int[2];
            N = 0;
            for (int i=0;i<H;i++) {
                String line = br.readLine();
                fillMap(i, line);
            }
            // findAns
            ans.add(findAns());

            // map init
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
        }
        for (int num : ans) {
            System.out.println(num);
        }
    }
    static int findAns() {
        int ans = max;
        ArrayDeque<Node> dq = new ArrayDeque<>();
        dq.add(new Node(loc[0],loc[1],0,0));
        int[][][] dists = new int[H][W][1<<N];

        for (int i=0;i<H;i++) {
            for (int j=0;j<W;j++) {
                Arrays.fill(dists[i][j],max);
            }
        }
        dists[loc[0]][loc[1]][0] = 0;
        int maxVisited = (1<<N) -1;

        while (!dq.isEmpty()) {
            Node cur = dq.poll();
            int h = cur.h;
            int w = cur.w;
            int visited = cur.visited;
            int dist = cur.dist;

            if (visited == maxVisited) {
                ans = Math.min(ans, dist);
            }
            for (int d=0;d<4;d++) {
                int nh = h + dr[d];
                int nw = w + dc[d];
                int nv = visited;
                if (!canGo(nh, nw)) continue;
                int tmp = map[nh][nw]-'0';
                if (tmp>=0 && tmp<N) {
                    nv |= (1<<tmp);
                }
                if (dists[nh][nw][nv]<=dist+1) continue;
                dists[nh][nw][nv] = dist+1;
                dq.add(new Node(nh, nw, nv, dist+1));
            }
        }
        return ans==max ? -1:ans;
    }

    static boolean canGo(int r, int c) {
        if (r<0 || r>=H || c<0 || c>=W || map[r][c]=='x') {
            return false;
        }
        return true;
    }
    static void fillMap(int i, String line) {
        char[] tmp = line.toCharArray();
        for (int j=0;j<W;j++) {
            map[i][j] = tmp[j];
            if (map[i][j]=='o') {
                loc[0] = i;
                loc[1] = j;
            }
            if (map[i][j]=='*') {
                map[i][j] = (char) ('0'+N);
                N++;
            }
        }
    }
}
