import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] up_raw = Arrays.stream(("-100 "+br.readLine()).split(" "))
                .mapToInt(Integer::parseInt).filter(i -> i!=0).toArray();
        int[] down_raw = Arrays.stream(("-100 "+br.readLine()).split(" "))
                .mapToInt(Integer::parseInt).filter(i->i!=0).toArray();
        up_raw[0] = 0;
        down_raw[0] = 0;

        int up_len = up_raw.length-1;
        int down_len = down_raw.length-1;
        int[][][] dp = new int[up_len+1][down_len+1][N+1];

        for (int i=0;i<=up_len;i++) {
            for (int j=0;j<=down_len;j++) {
                Arrays.fill(dp[i][j],-INF);
            }
        }
        dp[0][0][0] = 0;
        for (int k=1;k<=N;k++) {
            for (int i=0;i<=Math.min(k,up_len);i++) {
                for (int j=0;j<=Math.min(k,down_len);j++) {
                    int num = dp[i][j][k-1];
                    if (i>0 && dp[i-1][j][k-1]!=-INF) {
                        num = Math.max(num,dp[i-1][j][k-1]);
                    }
                    if (j>0 && dp[i][j-1][k-1]!=-INF) {
                        num = Math.max(num,dp[i][j-1][k-1]);
                    }
                    if (i>0 && j>0 && dp[i-1][j-1][k-1]!=-INF) {
                        num = Math.max(num,dp[i-1][j-1][k-1]+up_raw[i]*down_raw[j]);
                    }
                    dp[i][j][k] = num;
                }
            }
        }
//        for (int k=0;k<=N;k++) {
//            for (int i=0;i<=up_len;i++) {
//                for (int j=0;j<=down_len;j++) {
//                    System.out.print(dp[i][j][k]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }
        System.out.println(dp[up_len][down_len][N]);
    }
}
