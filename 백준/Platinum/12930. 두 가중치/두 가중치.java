import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int to, w1, w2;
        Edge(int to, int w1, int w2) {
            this.to = to;
            this.w1 = w1;
            this.w2 = w2;
        }
    }

    static class State {
        int v, sum1, sum2, cost;
        State(int v, int sum1, int sum2) {
            this.v = v;
            this.sum1 = sum1;
            this.sum2 = sum2;
            this.cost = sum1 * sum2;
        }
    }

    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] w1 = new int[N][N];
        int[][] w2 = new int[N][N];

        // 첫 번째 행렬 (w1)
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                char c = line[j];
                if (c == '.') continue;
                w1[i][j] = c - '0';
            }
        }

        // 두 번째 행렬 (w2)
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                char c = line[j];
                if (c == '.') continue;
                w2[i][j] = c - '0';
            }
        }

        // 인접 리스트 (방향 그래프)
        List<Edge>[] adj = new ArrayList[N];
        for (int i = 0; i < N; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (w1[i][j] == 0) continue; // 간선 없음
                adj[i].add(new Edge(j, w1[i][j], w2[i][j]));
            }
        }

        int ans = dijkstra(adj, N, 0, 1);
        System.out.println(ans);
    }

    static int dijkstra(List<Edge>[] adj, int N, int start, int end) {
        int[] shortW1 = new int[N];
        int[] shortW2 = new int[N];
        Arrays.fill(shortW1, INF);
        Arrays.fill(shortW2, INF);

        PriorityQueue<State> pq =
                new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));

        shortW1[start] = 0;
        shortW2[start] = 0;
        pq.add(new State(start, 0, 0));

        int answer = INF;

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            // 이미 현재까지 구한 답보다 크면 더 볼 필요 없음
            if (cur.cost >= answer) continue;

            int v = cur.v;
            int s1 = cur.sum1;
            int s2 = cur.sum2;

            // 이 정점에서 이미 더 좋은 (w1, w2) 상태가 있으면 패스
            if (s1 > shortW1[v] && s2 > shortW2[v]) continue;

            if (v == end) {
                answer = Math.min(answer, cur.cost);
                continue;
            }

            for (Edge e : adj[v]) {
                int nv  = e.to;
                int ns1 = s1 + e.w1;
                int ns2 = s2 + e.w2;
                int ncost = ns1 * ns2;

                if (ncost >= answer) continue;

                // 하나라도 더 작으면 앞으로 더 좋은 답이 될 가능성이 있으니 채택
                if (ns1 < shortW1[nv] || ns2 < shortW2[nv]) {
                    if (ns1 < shortW1[nv]) shortW1[nv] = ns1;
                    if (ns2 < shortW2[nv]) shortW2[nv] = ns2;
                    pq.add(new State(nv, ns1, ns2));
                }
            }
        }

        return (answer == INF ? -1 : answer);
    }
}
