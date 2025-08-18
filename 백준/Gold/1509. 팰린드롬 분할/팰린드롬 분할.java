import java.util.*;
import java.io.*;

public class Main {
    static int N; static char[] arr; static int[] dp; static boolean[][] isPal;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        arr = br.readLine().toCharArray();
        N = arr.length;
        isPal = new boolean[N][N];

        for (int i=0;i<N;i++) {
            isPal[i][i] = true;
        }
        for (int len=2;len<=N;len++) {
            for (int i=0;i<N-len+1;i++) {
                int j = i+len-1;
                if (arr[i]==arr[j]) {
                    if (len==2) isPal[i][j] = true;
                    else isPal[i][j] = isPal[i+1][j-1];
                }
            }
        }

        dp = new int[N+1];
        int max = 987654321;
        Arrays.fill(dp,max);
        dp[N] = 0;
        for (int i=N-1;i>=0;i--) {
            for (int j=i;j<N;j++) {
                if (isPal[i][j]) {
                    dp[i] = Math.min(dp[i],dp[j+1]+1);
                }
            }
        }
        System.out.println(dp[0]);
    }
}
