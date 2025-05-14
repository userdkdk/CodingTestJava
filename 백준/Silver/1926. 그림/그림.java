import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

class Main
{
    static int[][] arr; static int n, m;
    static int[] dr = {1,-1,0,0}; static int[] dc = {0,0,1,-1};
    static boolean[][] visited; static int max_ans, tmp_ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[n][m];
        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = 0;
        max_ans = 0;
        tmp_ans = 0;
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (arr[i][j]==1 && !visited[i][j]) {
                    visited[i][j] = true;
                    tmp_ans = 1;
                    bfs(i, j);
                    max_ans = Math.max(tmp_ans, max_ans);
                    ans++;
                }
            }
        }
        System.out.println(ans);
        System.out.println(max_ans);
    }
    static void bfs(int a, int b) {
        for (int d=0;d<4;d++) {
            int new_a = a+dr[d];
            int new_b = b+dc[d];
            if (!goCheck(new_a, new_b)) continue;
            visited[new_a][new_b] = true;
            tmp_ans++;
            bfs(new_a, new_b);
        }
    }
    static boolean goCheck(int a, int b) {
        if (a<0 || a>=n || b<0 || b>=m || visited[a][b]
        || arr[a][b]==0) return false;
        return true;
    }
}