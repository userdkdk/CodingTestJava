import java.util.*;
import java.io.*;

public class Main {
    static int N; static ArrayList<Integer>[] adj; static int[] weight;
    static int[][] dp; static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        weight = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) weight[i] = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adj[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        dp = new int[2][N+1];
        visited = new boolean[N+1];
        getDp(1);
        System.out.println(Math.max(dp[1][1],dp[0][1]));
    }
    static void getDp(int loc) {
        dp[1][loc] = weight[loc];
        dp[0][loc] = 0;
        visited[loc] = true;
        for (int i:adj[loc]) {
            if (visited[i]) continue;
            getDp(i);
            dp[1][loc] += dp[0][i];
            dp[0][loc] += Math.max(dp[0][i],dp[1][i]);
        }
    }
}
