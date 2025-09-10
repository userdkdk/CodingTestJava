import java.util.*;
import java.io.*;

public class Main {
    static int N, Q, K; static int[] treeP, P, counts; static int[] Sis;
    static Map<Integer,Integer> map; static ArrayList<Integer>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        treeP = new int[N+1];
        Arrays.fill(treeP,-1);
        adj = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adj[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        makeTree(1,0);

        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int q=0;q<Q;q++) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            map = new HashMap<>();
            Sis = new int[K];
            counts = new int[K];
            Arrays.fill(counts,1);
            for (int i=0;i<K;i++) {
                Sis[i] = Integer.parseInt(st.nextToken());
                map.put(Sis[i],i);
            }
            P = new int[K];
            Arrays.fill(P,-1);
            for (int i=0;i<K;i++) {
                int p = getP(Sis[i]);
                if (Sis[i]==p) continue;
                counts[map.get(p)]++;
            }
            int ans = 0;
            for (int i=0;i<K;i++) {
                if (P[i]==-1) {
                    int c = counts[i];
                    ans += c*(c-1)/2;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);

    }
    static int getP(int a) {
        int p = treeP[a];
        if (p==-1 || map.get(p)==null) return a;
        int a_loc = map.get(a);
        P[a_loc] = p;
        return getP(p);
    }
    static void makeTree(int now, int p) {
        for (int next: adj[now]) {
            if (next==p) continue;
            treeP[next] = now;
            makeTree(next,now);
        }
    }
}
