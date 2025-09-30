import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K; static ArrayList<Node>[] adj; static long[] distance;
    static long max = 900000000000L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adj[i] = new ArrayList<>();
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int sta = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
            adj[end].add(new Node(sta,len));
        }
        distance = new long[N+1];
        Arrays.fill(distance,max);
        Queue<Integer> q = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<K;i++) {
            int k = Integer.parseInt(st.nextToken());
            distance[k] = 0;
            q.add(k);
        }
        while (!q.isEmpty()) {
            int now = q.poll();
            for (Node node : adj[now]) {
                if (distance[now]+node.value>=distance[node.to]) continue;
                distance[node.to] = distance[now]+node.value;
                q.add(node.to);
            }
        }
        int loc = -1;
        long dis = -max;
        for (int i=1;i<=N;i++) {
            if (dis<distance[i]) {
                loc = i;
                dis = distance[i];
            }
        }
        System.out.println(loc);
        System.out.println(dis);
    }
    static class Node {
        int to, value;
        public Node(int t, int v) {
            this.to = t;
            this.value = v;
        }
    }
}
