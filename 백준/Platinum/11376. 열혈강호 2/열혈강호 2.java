import java.util.*;
import java.io.*;

public class Main {
    static int N, M, vid; static int[] Task, P; static ArrayList<Integer>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = new int[M+1];
        Task = new int[M+1];
        adj = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adj[i] = new ArrayList<>();
        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for (int j=0;j<k;j++) {
                int t = Integer.parseInt(st.nextToken());
                adj[i].add(t);
            }
        }
        int ans = 0;
        vid = 0;
        for (int i=1;i<=N;i++) {
            for (int j=0;j<2;j++) {
                vid++;
                if (dfs(i)) ans++;
            }
        }

//        System.out.println(Arrays.toString(Task));
        System.out.println(ans);
    }
    static boolean dfs(int a) {
        for (int v : adj[a]) {
            if (P[v]==vid) continue;
            P[v] = vid;
            if (Task[v]==0 || dfs(Task[v])) {
                Task[v] = a;
                return true;
            }
        }
        return false;
    }
}
