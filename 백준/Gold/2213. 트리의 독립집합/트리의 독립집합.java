import java.util.*;
import java.io.*;

public class Main {
    static int N; static ArrayList<Integer>[] nodes; static int[][] dp;
    static int[] weight; static boolean[] visited; static ArrayList<Integer> ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        nodes = new ArrayList[N+1];
        weight = new int[N+1];
        // 0이 자기자신 포함, 1이 포함하지 않음
        dp = new int[2][N+1];
        visited = new boolean[N+1];
        for (int i=1;i<=N;i++) nodes[i] = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) weight[i] = Integer.parseInt(st.nextToken());
        for (int i=1;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nodes[a].add(b);
            nodes[b].add(a);
        }
        dfs(1,0);
        boolean takeRoot = dp[1][1] > dp[0][1];
        ans = new ArrayList<>();
        findComp(1,0,false);
        Collections.sort(ans);
        StringBuilder sb = new StringBuilder();
        if (takeRoot) {
            sb.append(dp[1][1]).append("\n");
        } else {
            sb.append(dp[0][1]).append("\n");
        }
        for (int i:ans) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);

    }
    static void dfs(int loc, int p) {
        visited[loc] = true;
        dp[1][loc] = weight[loc];
        dp[0][loc] = 0;
        for (int i : nodes[loc]) {
            if (i==p) continue;
            dfs(i,loc);
            dp[0][loc] += Math.max(dp[0][i],dp[1][i]);
            dp[1][loc] += dp[0][i];
        }
    }
    static void findComp(int now, int p, boolean takeP) {
        boolean takeNow = false;
        if (takeP) takeNow = false;
        else {
            if (dp[1][now]>dp[0][now]) takeNow = true;
        }
        if (takeNow) ans.add(now);
        for (int i:nodes[now]) {
            if (i==p) continue;
            findComp(i,now,takeNow);
        }
    }
}
