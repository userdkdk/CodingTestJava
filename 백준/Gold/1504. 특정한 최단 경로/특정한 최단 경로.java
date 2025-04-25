import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main
{
    static List<List<Node>> graph; static int n, m, v1, v2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b,cost));
            graph.get(b).add(new Node(a,cost));
        }
        st= new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int stov1 = dijk(1,v1);
        int v1tov2 = dijk(v1,v2);
        int v2toe = dijk(v2,n);
        int stov2 = dijk(1,v2);
        int v2tov1 = dijk(v2,v1);
        int v1toe = dijk(v1,n);
        int ans = -1;
        if (stov1 != Integer.MAX_VALUE && v1tov2 != Integer.MAX_VALUE && v2toe != Integer.MAX_VALUE) {
            ans = stov1 + v1tov2 + v2toe;
        }
        if (stov2 != Integer.MAX_VALUE && v2tov1 != Integer.MAX_VALUE && v1toe != Integer.MAX_VALUE) {
            ans = Math.min(ans, stov2 + v2tov1 + v1toe);
        }
        System.out.println(ans);

    }

    static int dijk(int start, int end) {
        int[] dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.cost > dist[node.idx]) continue;

            for (Node next : graph.get(node.idx)) {
                if (dist[next.idx] > dist[node.idx] + next.cost) {
                    dist[next.idx] = dist[node.idx] + next.cost;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }
        return dist[end];
    }
    static class Node implements Comparable<Node> {
        int idx, cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
}