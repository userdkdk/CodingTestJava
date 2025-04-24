import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main
{
    static boolean[] visit; static int[] p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        visit = new boolean[n+1];
        p = new int[n+1];
        for (int i=0; i<=n;i++) {
            p[i] = -1;
        }

        int m = Integer.parseInt(st.nextToken());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Node(x,y,cost));
        }
        int num = 0;
        int ans = 0;
        while (num<n-2) {
            Node tmp = pq.poll();
            int x = tmp.x;
            int y = tmp.y;
            if (!union(x,y)) continue;
            ans += tmp.cost;
            num++;
        }
        System.out.println(ans);
    }
    static int findRoot(int a) {
        if (p[a]<0) return a;
        return findRoot(p[a]);
    }

    static boolean union(int a, int b) {
        int rootA = findRoot(a);
        int rootB = findRoot(b);
        if (rootA==rootB) return false;
        if (p[rootA]<p[rootB]) {
            p[rootA] += p[rootB];
            p[rootB] = rootA;
        } else {
            p[rootB] += p[rootA];
            p[rootA] = rootB;
        }
        return true;
    }

    static class Node implements Comparable<Node> {
        int x, y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
}