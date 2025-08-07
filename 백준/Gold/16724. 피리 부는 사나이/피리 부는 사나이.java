import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class Main {
    static int N, M; static char[][] arr; static boolean[][] visited;
    static Map<Character,Integer> map;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        visited = new boolean[N][M];
        map = new HashMap<>();
        map.put('D',0);
        map.put('R',1);
        map.put('U',2);
        map.put('L',3);

        for (int i=0;i<N;i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j=0;j<M;j++) {
                arr[i][j] = tmp[j];
            }
        }
        ans = 0;
        for (int i=0;i<N;i++) {
            for (int j=0;j<M;j++) {
                if (visited[i][j]) continue;
                bfs(i,j);
//                for (int k=0;k<N;k++) {
//                    System.out.println(Arrays.toString(visited[k]));
//                }
//                System.out.println();
                ans++;
            }
        }
        System.out.println(ans);
    }
    static int[] dr = {1,0,-1,0}; static int[] dc = {0,1,0,-1};
    static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r,c});
        visited[r][c] = true;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int dir = map.get(arr[now[0]][now[1]]);
            int next_r = now[0] + dr[dir];
            int next_c = now[1] + dc[dir];
            if (goCheck(next_r,next_c)) {
                q.add(new int[]{next_r,next_c});
                visited[next_r][next_c] = true;
            }
            for (int d=0;d<4;d++) {
                next_r = now[0] + dr[d];
                next_c = now[1] + dc[d];
                if (!goCheck(next_r,next_c)) continue;
                int tmp = map.get(arr[next_r][next_c]);
//                System.out.println(tmp+", "+d);
                if (tmp==d || (tmp+d)%2!=0) continue;
                q.add(new int[]{next_r,next_c});
                visited[next_r][next_c] = true;
            }
        }
    }
    static boolean goCheck(int a, int b) {
        if (a<0 || a>=N || b<0 || b>=M || visited[a][b]) return false;
        return true;
    }
}
