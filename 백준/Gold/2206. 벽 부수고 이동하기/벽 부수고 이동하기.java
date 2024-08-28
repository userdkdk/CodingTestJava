import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m; static int[][] arr; static boolean[][] visit, visit2;
	static int[] dr = {1,-1,0,0}; static int[] dc = {0,0,1,-1};
	static int[] loc; static Queue<int[]> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		for (int i=0;i<n;i++) {
			char[] char_arr = br.readLine().toCharArray();
			for (int j=0;j<m;j++) {
				arr[i][j] = char_arr[j]-'0';
			}
		}
		loc = new int[] {0,0,1,1};
		q = new LinkedList<>();
		visit = new boolean[n][m];
		visit2 = new boolean[n][m];
		visit[0][0] = true;
		q.add(loc);
		visit[0][0] = true;
		visit2[0][0] = true;
		int ans = n*m+1;
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			if (tmp[0]==n-1 && tmp[1]==m-1) {
				ans = Math.min(tmp[2], ans);
			}
			for (int d=0;d<4;d++) {
				int new_r = tmp[0]+dr[d];
				int new_c = tmp[1]+dc[d];
				if (new_r<0 || new_r>=n || new_c<0 || new_c>=m || visit[new_r][new_c]) continue;
				if (arr[new_r][new_c]==0 && tmp[3]==1) {
					q.add(new int[] {new_r,new_c,tmp[2]+1,tmp[3]});
					visit[new_r][new_c] = true;
				} else if (arr[new_r][new_c]==0 && tmp[3]==0 && !visit2[new_r][new_c]) {
					q.add(new int[] {new_r,new_c,tmp[2]+1,tmp[3]});
					visit2[new_r][new_c] = true;
				} else if (arr[new_r][new_c]==1 && tmp[3]==1 && !visit2[new_r][new_c]) {
					q.add(new int[] {new_r,new_c,tmp[2]+1,tmp[3]-1});
					visit[new_r][new_c] = true;
				}
			}
		}
        if (ans==n*m+1) ans=-1;
		System.out.println(ans);
	}
}
