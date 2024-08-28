
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m; static char[][] arr; static boolean[][][] visit;
	static int[] dr = {1,-1,0,0}; static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visit = new boolean[n][m][64];
		
		int ans = Integer.MAX_VALUE;
		Queue<Node> q = new LinkedList<>();
		arr = new char[n][m];
		for (int i=0;i<n;i++) {
			char[] char_arr = br.readLine().toCharArray();
			for (int j=0;j<m;j++) {
				arr[i][j] = char_arr[j];
				if (arr[i][j]=='0') {
					Node sta = new Node(i,j,0,0,new boolean[6]);
					q.add(sta);
					visit[i][j][0] = true;
				}
			}
		}
		
		while (!q.isEmpty()) {
			Node next = q.poll();
			for (int d=0;d<4;d++) {
				int new_r = next.r + dr[d];
				int new_c = next.c + dc[d];
				if (new_r<0 || new_r>=n || new_c<0 || new_c>=m || visit[new_r][new_c][next.keys]) continue;
				if (arr[new_r][new_c]=='.' || arr[new_r][new_c]=='0') {
					Node tmp = new Node(new_r,new_c,next.keys,next.ans+1,next.key);
					q.add(tmp);
					visit[new_r][new_c][next.keys] = true;
				} else if (arr[new_r][new_c]=='1') {
					ans = Math.min(ans, next.ans+1);
				} else if (arr[new_r][new_c]=='#'){
					continue;
				} else {
					if (arr[new_r][new_c]-'A'>30) {
						int loc = arr[new_r][new_c]-'a';
						if (next.key[loc]) {
							Node tmp = new Node(new_r,new_c,next.keys,next.ans+1,next.key);
							q.add(tmp);
							visit[new_r][new_c][next.keys] = true;
						} else {
							boolean[] new_key = next.key.clone();
							new_key[loc] = true;
							int new_keys = next.keys + (1<<loc);
							Node tmp = new Node(new_r,new_c,new_keys,next.ans+1,new_key);
							q.add(tmp);
							visit[new_r][new_c][new_keys]=true;
						}
					} else {
						int loc = arr[new_r][new_c]-'A';
						if (!next.key[loc]) continue;
						Node tmp = new Node(new_r,new_c,next.keys,next.ans+1,next.key);
						visit[new_r][new_c][next.keys] = true;
						q.add(tmp);
					}
				}
			}
		}
		if (ans==Integer.MAX_VALUE) ans=-1;
		System.out.println(ans);
		
	}
	static class Node {
		int r;
		int c;
		int keys;
		int ans;
		boolean[] key;
		public Node(int r, int c, int keys, int ans, boolean[] key) {
			super();
			this.r = r;
			this.c = c;
			this.keys = keys;
			this.ans = ans;
			this.key = key;
		}
		
		
	}
}
