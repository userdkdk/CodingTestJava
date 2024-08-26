import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n; static int[][] arr; static boolean[][] visit;
	static int[] dr = {1,-1,0,0}; static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		int min = 100;
		int max = 1;
		for (int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<n;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(min, arr[i][j]);
				max = Math.max(max, arr[i][j]);
			}
		}
		int ans = 1;
		for (int i=min;i<max;i++) {
			visit = new boolean[n][n];
			ans = Math.max(ans,getans(i));
		}
		System.out.println(ans);
	}
	static int getans(int depth) {
		Queue<int[]> q = new LinkedList<>();
		int ans=0;
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				if (!visit[i][j] && arr[i][j]>depth) {
					q.add(new int[] {i,j});
					visit[i][j]=true;
					while (!q.isEmpty()) {
						int[] tmp = q.poll();
						for (int d=0;d<4;d++) {
							int new_r = tmp[0]+dr[d];
							int new_c = tmp[1]+dc[d];
							if (new_r<0 || new_r>=n || new_c<0 || new_c>=n) continue;
							if (visit[new_r][new_c] || arr[new_r][new_c]<=depth) continue;
							q.add(new int[] {new_r,new_c});
							visit[new_r][new_c] = true;
						}
					}
					ans++;
				}
			}
		}
		return ans;
	}
}
