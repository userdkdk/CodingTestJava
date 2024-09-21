
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int v, e; static int[] p; static boolean[] visit;
	static Node[] nodes; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		nodes = new Node[e];
		for (int i=0;i<e;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			nodes[i] = new Node(a,b,c);
		}
		Arrays.sort(nodes);
		p = new int[v+1];
		Arrays.fill(p, -1);
		
		int n = 0;
		int ans = 0;
		int idx = 0;
		
		while (n<v-1) {
			Node tmp = nodes[idx++];
			if (union(tmp.x,tmp.y)) {
				n+=1;
				ans+=tmp.k;
			}
		}
		System.out.println(ans);
	}
	static int findRoot(int a) {
		if (p[a]<0) return a;
		return p[a] = findRoot(p[a]);
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
	static class Node implements Comparable<Node>{
		int x, y, k;

		public Node(int x, int y, int k) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
		}

		@Override
		public int compareTo(Node o) {
			if (this.k < o.k) return -1;
			else if (this.k == o.k) return 0;
			return 1;
		}
		
	}
}

