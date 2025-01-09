import java.io.*;
import java.sql.DriverManager;
import java.util.*;

public class Main {
    static int[] dr = {1, 0, -1, 0}; static int[] dc = {0, 1, 0, -1};
    static char[][] arr; static boolean[][][][] visit;
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m] ;
        visit = new boolean[n][m][n][m];
        Node node = new Node();
        for (int i=0;i<n;i++) {
            char[] charArr = br.readLine().toCharArray();
            for (int j=0;j<m;j++) {
                if (charArr[j]=='B') {
                    node.br = i;
                    node.bc = j;
                    arr[i][j] = '.';
                } else if (charArr[j]=='R') {
                    node.rr = i;
                    node.rc = j;
                    arr[i][j] = '.';
                } else {
                    arr[i][j] = charArr[j];
                }
            }
        }
        node.t = 0;
        visit[node.rr][node.rc][node.br][node.bc] = true;
        Queue<Node> q= new LinkedList<>();
        q.add(node);
        int ans = -1;
        while (!q.isEmpty()) {
            Node now = q.poll();
            // 조건 만족시 탈출
            if (arr[now.br][now.bc]=='O' || now.t>10) {
                continue;
            }
            if (arr[now.rr][now.rc]=='O') {
                if (arr[now.br][now.bc]=='O') {
                    continue;
                }
                ans = now.t;
                break;
            }
            for (int d=0;d<4;d++) {
                // 각 요소 이동시키기
                int[] red = moving(now.rr,now.rc, now.br, now.bc, d);
                int[] blue = moving(now.br,now.bc, red[0], red[1], d);
                red = moving(red[0],red[1], blue[0], blue[1], d);
                // q에 담기
                Node new_node = new Node(red[0],red[1],blue[0],blue[1],now.t+1);
                if (visit[red[0]][red[1]][blue[0]][blue[1]]) continue;
                visit[red[0]][red[1]][blue[0]][blue[1]] = true;
                q.add(new_node);
            }
        }
        System.out.println(ans);

    }
    static int[] moving(int r, int c, int or, int oc, int d) {
        int new_r = r;
        int new_c = c;
        if (arr[or][oc]=='O') {
            or = -1;
            oc = -1;
        }
        while (arr[new_r][new_c]!='O') {
            new_r = r + dr[d];
            new_c = c + dc[d];
            if (new_r<0 || new_r>=n || new_c<0 || new_c>=m) break;
            if (arr[new_r][new_c]!='.') {
                if (arr[new_r][new_c]!='O') break;
            }
            if (new_r==or && new_c == oc) break;
            r += dr[d];
            c += dc[d];
        }
        return new int[]{r,c};
    }

    static class Node {
        int rr, rc, br, bc, t;

        public Node() {}

        public Node(int rr, int rc, int br, int bc, int t) {
            this.rr = rr;
            this.rc = rc;
            this.br = br;
            this.bc = bc;
            this.t = t;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "rr=" + rr +
                    ", rc=" + rc +
                    ", br=" + br +
                    ", bc=" + bc +
                    ", t=" + t +
                    '}';
        }
    }
}