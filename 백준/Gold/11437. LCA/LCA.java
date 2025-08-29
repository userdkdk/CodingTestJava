import java.util.*;
import java.io.*;

public class Main {
    static int N, M, log; static ArrayList<Integer>[] adj; static int[][] p;
    static int[] depth;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adj[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        log = 1;
        while ((1<<log)<=N) log++;
        p = new int[log][N+1];
        depth = new int[N+1];
        Arrays.fill(depth,-1);
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        depth[1] = 0;
        p[0][1] = 0;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i : adj[now]) {
                if (depth[i]!=-1) continue;
                depth[i] = depth[now]+1;
                p[0][i] = now;
                q.add(i);
            }
        }
        for (int k=1;k<log;k++) {
            for (int v=1;v<=N;v++) {
                int mid = p[k-1][v];
                p[k][v] = (mid==0) ? 0 : p[k-1][mid];
            }
        }

        M = Integer.parseInt(br.readLine());
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (depth[a]>=depth[b]) {
                int t = a;
                a = b;
                b = t;
            }

            int dif = depth[b] - depth[a];
            int k=0;
            while (dif>0) {
                if (dif%2==1) {
                    b = p[k][b];
                }
                dif/=2;
                k++;
            }
            System.out.println(getLCA(a,b));
        }

    }
    static int getLCA(int a, int b) {
        if (a==b) return a;
        for (int kk=log-1;kk>=0;kk--) {
            if (p[kk][a]!=p[kk][b]) {
                a = p[kk][a];
                b = p[kk][b];
            }
        }
        return p[0][a];
    }
}
