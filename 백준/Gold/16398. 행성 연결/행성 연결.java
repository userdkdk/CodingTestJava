import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main
{
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        visit = new boolean[n];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i=1;i<n;i++) {
            Edge edge = new Edge(i,arr[0][i]);
            pq.add(edge);
        }
        visit[0] = true;
        int num = 1;
        long ans = 0;
        while (num<n) {
            Edge edge = pq.poll();
            if (visit[edge.to]) continue;
            ans += edge.cost;
            visit[edge.to] = true;
            num += 1;
            for (int i=0;i<n;i++) {
                if (visit[i]) continue;
                Edge newEdge = new Edge(i,arr[edge.to][i]);
                pq.add(newEdge);
            }
        }
        System.out.println(ans);

    }
    static class Edge implements Comparable<Edge> {
        int cost;
        int to;

        public Edge(int to, int cost) {
            this.cost = cost;
            this.to = to;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}