import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K; static ArrayList<Node>[] adj; static boolean[] visited;
    static int max = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        int[] stas = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<K;i++) {
            stas[i] = Integer.parseInt(st.nextToken());
            visited[stas[i]] = true;
        }
        adj = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adj[i] = new ArrayList<>();
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            adj[a].add(new Node(b,val));
            adj[b].add(new Node(a,val));
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(node -> node.value)
        );
        for (int k=0;k<K;k++) {
            pq.addAll(adj[stas[k]]);
        }
        int count = K;
        int ans = 0;
        while (count<N) {
            Node node = pq.poll();
            int to = node.to;
            int val = node.value;
            if (visited[to]) continue;
            ans += val;
            visited[to] = true;
            pq.addAll(adj[to]);
            count++;
        }
        System.out.println(ans);
    }
    static class Node {
        int to, value;
        public Node(int t, int v) {
            this.to = t;
            this.value = v;
        }
    }
}
