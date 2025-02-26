import java.io.*;
import java.util.*;

public class Main {
    static int n; static int[][] arr; static int[][] ans_arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int tc=1;
        while (n!=0) {
            arr = new int[n][n];
            ans_arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    ans_arr[i][j] = Integer.MAX_VALUE;
                }
            }
            ans_arr[0][0] = arr[0][0];
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{0, 0});
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                for (int d=0;d<4;d++) {
                    int x = cur[0] + dr[d];
                    int y = cur[1] + dc[d];
                    if (!goCheck(x, y)) continue;
                    int now = ans_arr[cur[0]][cur[1]] + arr[x][y];
                    if (now >= ans_arr[x][y]) continue;
                    ans_arr[x][y] = now;
                    q.add(new int[]{x, y});
                }
            }
            System.out.println("Problem "+(tc++)+": "+ans_arr[n-1][n-1]);
            n = Integer.parseInt(br.readLine());
        }
    }
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    static boolean goCheck(int a, int b) {
        if (a<0 || a>=n || b<0 || b>=n) return false;
        return true;
    }
}
