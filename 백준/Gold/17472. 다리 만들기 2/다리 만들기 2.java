
import java.util.*;

public class Main {
	static int n, m, land_c; static int[][] arr, land_dis; static boolean[][] visit;
	static int[] dr = {1,-1,0,0}; static int[] dc = {0,0,1,-1};
	static int[] p;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[n][m];
		for (int i=0;i<n;i++) {
			for (int j=0;j<m;j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		// 섬 나누기
		visit = new boolean[n][m];
		land_c = 0;
		for (int i=0;i<n;i++) {
			for (int j=0;j<m;j++) {
				if (arr[i][j]!=0 && !visit[i][j]) {
					land_c++;
					dfs(i,j,land_c);
				}
			}
		}
		// 섬간 거리 구하기
		land_dis = new int[land_c+1][land_c+1];
		for (int i=0;i<land_c+1;i++) {
			for (int j=0;j<land_c+1;j++) {
				land_dis[i][j]=n*m;
			}
		}
		for (int i=0;i<n;i++) {
			for (int j=0;j<m;j++) {
				if (arr[i][j]!=0) {
					getDis(i,j);
				}
			}
		}
		// 탐색을 위한 거리 노드 배열 생성
		Edge[] edges = new Edge[land_c*(land_c-1)/2];
		int idx = 0;
		for (int i=1;i<land_c;i++) {
			for (int j=i+1;j<land_c+1;j++) {
				edges[idx++] = new Edge(i,j,land_dis[i][j]);
			}
		}
		// 거리 노드 정렬
		Arrays.sort(edges);
		// 부모 배열 생성
		p = new int[land_c+1];
		for (int i=0;i<=land_c;i++) {
			p[i]=-1;
		}
		
		int v=0;
		int ans = 0;
		idx = 0;
		while (idx<land_c*(land_c-1)/2) {
			boolean check = Union(edges[idx].r,edges[idx].c);
			if (check) {
				v++;
				if (edges[idx].dis==n*m) {
					idx=land_c*(land_c-1)/2;
					break;
				}
				ans += edges[idx].dis;
			}
			if (v==land_c-1) break;
			idx++;
		}
		if (idx==land_c*(land_c-1)/2) ans = -1;
		System.out.println(ans);
		
//		for (int i=0;i<n;i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
//		for (int i=0;i<land_c+1;i++) {
//			System.out.println(Arrays.toString(land_dis[i]));
//		}
//		for (int i=0;i<land_c*(land_c-1)/2;i++) {
//			System.out.println(edges[i]);
//		}
	}
	static class Edge implements Comparable<Edge>{
		int r;
		int c;
		int dis;

		public Edge(int r, int c, int dis) {
			this.r = r;
			this.c = c;
			this.dis = dis;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			if (this.dis==o.dis) return 0;
			else if (this.dis < o.dis) return -1;
			return 1;
		}

		@Override
		public String toString() {
			return "Edge [r=" + r + ", c=" + c + ", dis=" + dis + "]";
		}
		
	}
	static void dfs(int i, int j, int c) {
		Queue<int[]> q = new LinkedList<>();
		arr[i][j] = c;
		q.add(new int[] {i,j});
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			for (int d=0;d<4;d++) {
				int new_r = tmp[0]+dr[d];
				int new_c = tmp[1]+dc[d];
				if (new_r<0||new_r>=n||new_c<0||new_c>=m||arr[new_r][new_c]==0||visit[new_r][new_c]) continue;
				visit[new_r][new_c]=true;
				arr[new_r][new_c]=c;
				q.add(new int[] {new_r,new_c});
			}
		}
	}
	static void getDis(int i, int j) {
		for (int d=0;d<4;d++) {
			int new_r = i;
			int new_c = j;
			boolean flag = false;
			int dis = 0;
			while (true) {
				new_r += dr[d];
				new_c += dc[d];
				if (new_r<0||new_r>=n||new_c<0||new_c>=m||arr[new_r][new_c]==arr[i][j]) break;
				dis++;
				if (arr[new_r][new_c]!=0) {
					flag = true;
					break;
				}
			}
			if (flag && dis>=3) {
				land_dis[arr[i][j]][arr[new_r][new_c]] = Math.min(land_dis[arr[i][j]][arr[new_r][new_c]], dis-1);
			}
		}
	}
	static int findSet(int a) {
		if (p[a]<0) return a;
		return p[a]=findSet(p[a]);
	}
	static boolean Union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot==bRoot) return false;
		if (p[aRoot]>p[bRoot]) {
			p[bRoot] += p[aRoot];
			p[aRoot] = bRoot;
		} else {
			p[aRoot] += p[bRoot];
			p[bRoot] = aRoot;
		}
		return true;
	}
}
