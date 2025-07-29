import java.util.*;
import java.io.*;

public class Main {
    static int N, C, max; static int[] dp; static Node[] nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        nodes = new Node[N];
        dp = new int[1500];
        Arrays.fill(dp,Integer.MAX_VALUE);
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int person = Integer.parseInt(st.nextToken());
            dp[person] = Math.min(dp[person],cost);
            nodes[i] = new Node(cost,person);
            max = Math.max(max,person);
        }
        Arrays.sort(nodes);
        for (int i=1;i<=C+max;i++) {
            for (int j=0;j<N;j++) {
                Node node = nodes[j];
                if (i-node.person<=0 || dp[i-node.person]==Integer.MAX_VALUE) continue;
                dp[i] = Math.min(dp[i],dp[i-node.person] + node.cost);

            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i=C;i<=C+max;i++) {
            ans = Math.min(ans,dp[i]);
        }
        System.out.println(ans);
    }
    static class Node implements Comparable<Node>{
        int cost, person;
        double percent;
        public Node() {}

        public Node(int cost, int person) {
            this.cost = cost;
            this.person = person;
            this.percent = (double) cost/person;
        }

        @Override
        public int compareTo(Node o) {
            if (this.percent==o.percent) {
                return this.person-o.person;
            }
            if (this.percent > o.percent) {
                return 1;
            }
            return -1;
        }
        @Override
        public String toString() {
            return "Node: "+this.cost+", "+this.person+", "+this.percent+" ";
        }
    }
}
