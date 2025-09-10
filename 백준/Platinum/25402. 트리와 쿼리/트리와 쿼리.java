import java.util.*;
import java.io.*;

public class Main {
    static int N, Q, K; static int[] treeP, P, counts; static int[] Sis;
    static boolean[] containS; static ArrayList<Integer>[] adj;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        treeP = new int[N+1];
        adj = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adj[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        visited = new boolean[N+1];
        visited[1] = true;
        makeTree(1);

        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        containS = new boolean[N+1];

        counts = new int[N+1];
        Arrays.fill(counts,1);
        P = new int[N+1];
        for (int i=1;i<=N;i++) P[i] = i;
        for (int q=0;q<Q;q++) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            Sis = new int[K];
            for (int i=0;i<K;i++) {
                Sis[i] = Integer.parseInt(st.nextToken());
                containS[Sis[i]] = true;
            }
            for (int i=0;i<K;i++) {
                int a = Sis[i];
                if (containS[treeP[a]]) {
                    Union(a,treeP[a]);
                }
            }
            long ans = 0;
            for (int i=0;i<K;i++) {
                if (P[Sis[i]]==Sis[i]) {
                    int c = counts[Sis[i]];
                    ans += (long) c*(c-1)/2;
                }
                counts[Sis[i]] = 1;
                containS[Sis[i]] = false;
                P[Sis[i]] = Sis[i];
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);

    }
    static int getP(int a) {
        if (P[a]!=a) {
            P[a] = getP(P[a]);
        }
        return P[a];
    }
    static void Union(int a, int b) {
        int pa = getP(a);
        int pb = getP(b);
        if (pa==pb) return;
        counts[pb] += counts[pa];
        P[pa] = pb;
    }
    static void makeTree(int now) {

        for (int next: adj[now]) {
            if (visited[next]) continue;
            treeP[next] = now;
            visited[next] = true;
            makeTree(next);
        }
    }
}
