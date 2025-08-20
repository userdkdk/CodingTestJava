import java.util.*;
import java.io.*;

public class Main {
    static int N; static int[] arr; static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pq.add(new Node(a,b));
        }
        int[] dp = new int[N+1];
        Node first = pq.poll();
        dp[0] = first.end;
        int loc = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.sta>=dp[loc]) {
                loc++;
                dp[loc] = node.end;
                continue;
            }
            if (node.end<dp[loc]) {
                dp[loc] = node.end;
            }
        }
        System.out.println(loc+1);

    }
    static class Node implements Comparable<Node>{
        int sta, end;

        public Node(int sta, int end) {
            this.sta = sta;
            this.end = end;
        }

        public int compareTo(Node o) {
            if (this.sta == o.sta) return this.end - o.end;
            return this.sta - o.sta;
        }
    }
}
