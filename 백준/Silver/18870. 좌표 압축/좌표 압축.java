import java.util.*;
import java.io.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int value =  Integer.parseInt(st.nextToken());
            pq.add(new Node(i, value));
        }
        int count = -1;
        int bef = Integer.MAX_VALUE;
        int[] ans = new int[N];
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.value != bef) count++;
            bef = node.value;
            ans[node.loc] = count;
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<N;i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }
    static class Node implements Comparable<Node> {
        int loc,  value;
        public Node(int loc, int value) {
            this.loc = loc;
            this.value = value;
        }
        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }
}
