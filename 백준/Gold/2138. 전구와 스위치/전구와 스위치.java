import java.util.*;
import java.io.*;

public class Main {
    static int N, ans; static char[] sta, end; static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        sta = br.readLine().toCharArray();
        end = br.readLine().toCharArray();
        ans = Integer.MAX_VALUE;
        dp = new int[N+1];
        dp[1] = 0;
        dp[2] = sta[0]==end[0] ? dp[1]:dp[1]+1;
        getDP();

        dp[1] = 1;
        dp[2] = sta[0]==end[0] ? dp[1]+1:dp[1];
        getDP();
        if (ans==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);


    }
    static void getDP() {
        for (int i=3;i<=N;i++) {
            if (sta[i-2]==end[i-2]) {
                dp[i] = (dp[i-1]-dp[i-3])%2==0 ? dp[i-1] : dp[i-1]+1;
            }
            else {
                dp[i] = (dp[i-1]-dp[i-3])%2==0 ? dp[i-1]+1 : dp[i-1];
            }
        }
        if ((dp[N]-dp[N-2])%2==0 && sta[N-1]!=end[N-1]) dp[N] = Integer.MAX_VALUE;
        if ((dp[N]-dp[N-2])%2!=0 && sta[N-1]==end[N-1]) dp[N] = Integer.MAX_VALUE;
        ans = Math.min(ans,dp[N]);
    }
}
