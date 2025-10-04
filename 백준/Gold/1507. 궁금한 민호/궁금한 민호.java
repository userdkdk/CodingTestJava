import java.io.*;
import java.util.*;

public class Main {
    static int N; static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[][] visited = new boolean[N][N];
        boolean flag = false;
        for (int k=0;k<N;k++) {
            for (int i=0;i<N-1;i++) {
                for(int j=i+1;j<N;j++) {
                    if (i==k || j==k) continue;
                    if (arr[i][j]>arr[i][k] + arr[k][j]) {
                        flag = true;
                        break;
                    }
                    if (arr[i][j] == arr[i][k] + arr[k][j]) {
                        visited[i][j] = true;
                    }
                }
            }
        }
        int ans = 0;

        for (int i=0;i<N-1;i++) {
            for (int j=i+1;j<N;j++) {
                if (!visited[i][j]) ans += arr[i][j];
            }
        }
        if (flag) System.out.println(-1);
        else System.out.println(ans);
    }
}
