import java.util.*;
import java.io.*;

public class Main {
    static int N, M, log; static int[][] p;
    static long[][] costs; static int[] lev;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        ArrayList<int[]>[] list = new ArrayList[N+1];
        for (int i=1;i<=N;i++) list[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int sta = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[sta].add(new int[]{end,c});
            list[end].add(new int[]{sta,c});
        }

        log = 0;
        while (1<<log<=N) log++;
        p = new int[log][N+1];
        costs = new long[log][N+1];
        lev = new int[N+1];
        Arrays.fill(lev,-1);
        lev[1] = 0;

        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.addFirst(1);
        while (!dq.isEmpty()) {
            int now = dq.pollFirst();
            for (int[] next : list[now]) {
                if (lev[next[0]]!=-1) continue;
                lev[next[0]] = lev[now]+1;
                p[0][next[0]] = now;
                costs[0][next[0]] = next[1];
                dq.addLast(next[0]);
            }
        }

        for (int i=1;i<log;i++) {
            for (int v=1;v<=N;v++) {
                int mid = p[i-1][v];
                if (mid==0) continue;
                p[i][v] = p[i-1][mid];
                if (costs[i-1][mid]==0) continue;
                costs[i][v] = costs[i-1][v] + costs[i-1][mid];
            }
        }

        sb = new StringBuilder();
        M = Integer.parseInt(br.readLine());
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            switch (p) {
                case 1:
                    long cost = getCost(a,b).cost;
                    sb.append(cost).append("\n");
                    break;
                case 2:
                    int k = Integer.parseInt(st.nextToken())-1;
                    int mid = getCost(a,b).loc;
                    getK(b,a,mid,k);
                    break;
            }
        }
        System.out.println(sb);
    }
    static Node getCost(int a, int b) {
        if (lev[a]>=lev[b]) {
            int t = a;
            a = b;
            b = t;
        }
        long cost = 0;
        int dif = lev[b] - lev[a];

        for (int k=0;dif>0;k++) {
            if (dif%2==1) {
                cost += costs[k][b];
                b = p[k][b];
            }
            dif/=2;
        }

        if (a==b) return new Node(cost,a);

        for (int i=log-1;i>=0;i--) {
            if (p[i][a]==p[i][b]) continue;
            cost += costs[i][a] + costs[i][b];
            a = p[i][a];
            b = p[i][b];
        }
        cost += costs[0][a] + costs[0][b];
        return new Node(cost, p[0][a]);
    }
    static void getK(int a, int b, int mid, int k) {
        int dif = 0;
        if (lev[b]-lev[mid]>=k) {
            dif = k;
            for (int num=0;dif>0;num++) {
                if (dif%2==1) {
                    b = p[num][b];
                }
                dif/=2;
            }
            sb.append(b).append("\n");
        } else {
            k -= (lev[b]-lev[mid]);
            dif = lev[a] - lev[mid] - k;
            for (int num=0;dif>0;num++) {
                if (dif%2==1) {
                    a = p[num][a];
                }
                dif/=2;
            }
            sb.append(a).append("\n");
        }
    }
    static class Node {
        long cost;
        int loc;
        public Node(long cost, int loc) {
            this.cost = cost;
            this.loc = loc;
        }
    }
}
