import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class Main {
    static int N; static int[][] arr; static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        dp = new int[N][N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i=0;i<N-1;i++) {
            dp[1][i] = arr[i][0]*arr[i][1]*arr[i+1][1];
        }
        for (int i=2;i<N;i++) {
            for (int j=0;j<N-i;j++) {
                int tmp = Integer.MAX_VALUE;
                for (int k=0;k<i;k++) {
                    tmp = Math.min(tmp,
                            dp[k][j]+dp[i-k-1][j+k+1]+arr[j][0]*arr[j+k+1][0]*arr[i+j][1]);
                }
                dp[i][j] = tmp;
            }
        }

//        for (int i=0;i<N;i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
        System.out.println(dp[N-1][0]);
    }
}
