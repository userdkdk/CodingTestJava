import java.util.*;
import java.io.*;

public class Main {
    static int n, m; static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n+2][n+2];

        for (int i=1;i<=n;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=n;j++) {
                int tmp = Integer.parseInt(st.nextToken());
                arr[i][j] = arr[i-1][j] + arr[i][j-1] + tmp - arr[i-1][j-1];
            }
        }
        for (int tc=0;tc<m;tc++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int tmp = arr[x2][y2] - arr[x1-1][y2] - arr[x2][y1-1] + arr[x1-1][y1-1];
            System.out.println(tmp);

        }
    }
}
