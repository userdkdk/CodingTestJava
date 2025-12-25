import java.io.*;
import java.util.*;

public class Main {
    static int N, M; static int[] bites, costs, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bites = new int[N];
        costs = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            bites[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i=0;i<N;i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            max += costs[i];
        }
        dp = new int[max+1];
        for (int i=0;i<N;i++) {
            for (int k=max;k>=costs[i];k--) {
                dp[k] = Math.max(dp[k],dp[k-costs[i]]+bites[i]);
            }
        }
        int ans = 0;
        for (int i=0;i<=max;i++) {
            if (dp[i]>=M) {
                ans = i;
                break;
            }
        }
        System.out.println(ans);

    }
}
