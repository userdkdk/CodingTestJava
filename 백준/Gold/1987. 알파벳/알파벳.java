import java.util.*;
import java.io.*;

public class Main {
    static int r,c, max; static char[][] arr; static boolean[] visited;
    static int[] dr = {0,0,1,-1}; static int[] dc = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new char[r][c];
        visited = new boolean[50];
        for (int i=0;i<r;i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j=0;j<c;j++) {
                arr[i][j] = tmp[j];
            }
        }
        visited[arr[0][0]-'A'] = true;
        bfs(0,0,1);
        System.out.println(max);
    }
    static void bfs(int a, int b, int c) {
        max = Math.max(max,c);
        for (int d=0;d<4;d++) {
            int new_r = a + dr[d];
            int new_c = b + dc[d];
            if (!check(new_r,new_c)) continue;
            visited[arr[new_r][new_c]-'A'] = true;
            bfs(new_r,new_c,c+1);
            visited[arr[new_r][new_c]-'A'] = false;
        }
    }
    static boolean check(int a, int b) {
        if (a<0 || a>=r || b<0 || b>=c) return false;
        int now = arr[a][b]-'A';
        if (visited[now]) return false;
        return true;
    }
}
