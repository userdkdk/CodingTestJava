import java.util.*;
import java.io.*;

public class Main {
    static int N, count; static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        count = 0;
        getPrime();
        int ans = 0;
        int left = 0;
        int right = 0;
        int sum = 0;
//        System.out.println(count);
//        System.out.println(Arrays.toString(dp));
        while (right<=count) {
//            System.out.println(sum);
            if (sum<N) {
                sum += dp[right++];
            } else if (sum==N) {
                ans++;
                sum += dp[right++];
            } else {
                sum -= dp[left++];
            }
        }
        System.out.println(ans);
    }
    static void getPrime() {
        for (int i=2;i<=N;i++) {
            boolean flag = false;
            int max = (int) Math.sqrt(i);
            for (int j=2;j<=max;j++) {
                if (i%j==0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                dp[count++] = i;
            }
        }
    }
}
