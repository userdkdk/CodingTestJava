import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main
{
    static int[] p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        p = new int[n+1];
        for (int i=0; i<=n; i++) {
            p[i] = -1;
        }
        int num = 0;
        int minCost = 0;
        int maxCost = 0;
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());
        minCost += 1-cost;
        maxCost += cost;

        PriorityQueue<Node> pqMin = new PriorityQueue<>();
        PriorityQueue<Node> pqMax = new PriorityQueue<>();
        for (int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            pqMin.add(new Node(a,b,1-cost));
            pqMax.add(new Node(a,b,cost));
        }
        while (num<n-1) {
            Node minCur = pqMin.poll();
            if (!union(minCur.x, minCur.y)) continue;
            num++;
            minCost += minCur.cost;
        }
        num = 0;
        for (int i=0; i<=n; i++) {
            p[i] = -1;
        }
        while (num<n-1) {
            Node maxCur = pqMax.poll();
            if (!union(maxCur.x, maxCur.y)) continue;
            num++;
            maxCost += maxCur.cost;
        }
        maxCost = n-maxCost;
        long ans = (long) Math.pow(maxCost,2) - (long) Math.pow(minCost,2);
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
        Node(int x, int y, int cost) {
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