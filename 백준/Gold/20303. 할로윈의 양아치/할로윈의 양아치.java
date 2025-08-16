import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K; static int[] p, dp, candies;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        p = new int[N+1];
        candies = new int[N+1];

        Arrays.fill(p,-1);
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            candies[i] = Integer.parseInt(st.nextToken());
        }
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Union(a,b);
        }

        int[][] candyCounts = new int[N+1][2];
        for (int i=1;i<=N;i++) {
            candyCounts[getRoot(i)][0] += candies[i];
            candyCounts[getRoot(i)][1]++;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i=1;i<=N;i++) {
            if (candyCounts[i][0]!=0) {
                Node node = new Node(candyCounts[i][1],candyCounts[i][0]);
                pq.add(node);
            }
        }
        ArrayList<Integer>[] list = new ArrayList[N+1];
        for (int i=0;i<N+1;i++) {
            list[i] = new ArrayList<>();
        }

        int range = -1;
        int bef = -2;
        int countMax = 1;
        int[] candyArr = new int[N+1];
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (bef!=node.count) {
                range++;
                bef = node.count;
                candyArr[range] = node.count;
                countMax = 1;
            }
            list[range].add(node.value);
            countMax++;
        }

        dp = new int[K+1];
        int[][] visited = new int[K+1][range+1];
        for (int i=1;i<=K;i++) {
            int tmp_max = 0;
            int tmp_loc = 0;
            for (int j=0;j<=range;j++) {
                if (i-candyArr[j]<0) break;
                int loc = visited[i-candyArr[j]][j];
                if (list[j].size()<=loc) continue;
                if (tmp_max<dp[i-candyArr[j]]+list[j].get(loc)) {
                    tmp_max = dp[i-candyArr[j]]+list[j].get(loc);
                    tmp_loc = j;
                }
            }
            if (tmp_max!=0) {
                for (int j=0;j<=range;j++) {
                    visited[i][j] = visited[i-candyArr[tmp_loc]][j];
                }
                visited[i][tmp_loc] = visited[i-candyArr[tmp_loc]][tmp_loc]+1;
            }
            dp[i] = tmp_max;
        }

        int ans = 0;
        for (int i=1;i<K;i++) {
            ans = Math.max(ans,dp[i]);
        }
//        System.out.println(Arrays.toString(candyArr));
//        for (int i=0;i<N+1;i++) {
//            System.out.println(list[i]);
//        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(ans);


    }
    static class Node implements Comparable<Node>{
        int count, value;
        public Node() {}
        public Node(int count, int value) {
            this.count = count;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            if (this.count == o.count) {
                return o.value - this.value;
            }
            return this.count - o.count;
        }

        @Override
        public String toString() {
            return "NOde: "+ count + ", value: "+ value+" ";
        }
    }
    static int getRoot(int a) {
        if (p[a] == -1) {
            return a;
        }
        return p[a] = getRoot(p[a]);
    }
    static boolean Union(int a, int b) {
        int rootA = getRoot(a);
        int rootB = getRoot(b);

        if (rootA == rootB) return false;
        if (rootA < rootB) {
            p[rootB] = rootA;
            p[b] = rootA;
        } else {
            p[rootA] = rootB;
            p[a] = rootB;
        }
        return true;
    }
}
