import java.io.*;
import java.util.*;

public class Main {
    static int W, H; static char[][] map; static boolean[][] visited;
    static boolean[] hasKey; static ArrayDeque<int[]> starts;
    static ArrayList<int[]> canStarts;
    static int[] dr = {1,-1,0,0}; static int[] dc = {0,0,1,-1};
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc=1;tc<=T;tc++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            visited = new boolean[H][W];
            starts = new ArrayDeque<>();
            canStarts = new ArrayList<>();
            hasKey = new boolean[26];
            ans = 0;

            for (int i=0;i<H;i++) {
                map[i] = br.readLine().toCharArray();
            }
            char[] keys = br.readLine().toCharArray();

            // add key
            for (char key : keys) {
                if (key=='0') break;
                addKey(key);
            }
            // add can start
            for (int i=0;i<H;i++) {
                addCanStart(i,0);
                addCanStart(i,W-1);
            }
            for (int j=1;j<W-1;j++) {
                addCanStart(0,j);
                addCanStart(H-1,j);
            }
            addStart();
            bfs();
            System.out.println(ans);
        }
    }
    static void bfs() {
        while (!starts.isEmpty()) {
            int[] cur = starts.poll();
            int r = cur[0];
            int c = cur[1];

            for (int d=0;d<4;d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!canGo(nr, nc)) continue;
                visited[nr][nc] = true;
                checkKey(nr,nc);
                starts.add(new int[]{nr,nc});
            }
            addStart();
        }
    }
    static void checkKey(int r, int c) {
        int check = map[r][c]-'a';
        if (check>=0 && check<=25) {
            addKey(map[r][c]);
            return;
        }
        if (map[r][c]=='$') {
            ans++;
        }
    }

    static boolean canGo(int r, int c) {
        if (r<0 || r>=H || c<0 || c>=W ||
                visited[r][c] || map[r][c]=='*') {
            return false;
        }
        int check = map[r][c]-'A';
        if (check>=0 && check<=25 && !hasKey[check]) {
            addCanStart(r,c);
            return false;
        }
        return true;
    }

    static void addKey(char key) {
        hasKey[key-'a'] = true;
    }
    static void addCanStart(int i, int j) {
        if (map[i][j]=='*') return;
        canStarts.add(new int[]{i,j});
    }
    static void addStart() {
        Iterator<int[]> it = canStarts.iterator();
        while (it.hasNext()) {
            int[] cur = it.next();
            char now = map[cur[0]][cur[1]];
            if ((now-'a'>=0 && now-'a'<=25) || now=='$') {
                checkKey(cur[0],cur[1]);
                starts.add(cur);
                visited[cur[0]][cur[1]] = true;
                it.remove();
            } else if (now=='.' || hasKey[now-'A']) {
                starts.add(cur);
                visited[cur[0]][cur[1]] = true;
                it.remove();
            }
        }
    }
}
