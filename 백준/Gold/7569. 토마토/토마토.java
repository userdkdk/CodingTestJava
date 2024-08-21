
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int m, n, h; static int[][][] arr; static boolean[][][] nvisit;
	static int[] dx = {1,-1,0,0,0,0}; static int[] dy = {0,0,1,-1,0,0}; 
	static int[] dz = {0,0,0,0,1,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		arr = new int[n][m][h];
		nvisit = new boolean[n][m][h];
		Queue<int[]> q = new LinkedList<>();
		for (int k=0;k<h;k++) {
			for (int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0;j<m;j++) {
					arr[i][j][k] = Integer.parseInt(st.nextToken());
					if (arr[i][j][k]==0) nvisit[i][j][k]=true;
					if (arr[i][j][k]==1) q.add(new int[] {i,j,k});
				}
			}
		}

		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			for (int d=0;d<6;d++) {
				int new_x = tmp[0]+dx[d];
				int new_y = tmp[1]+dy[d];
				int new_z = tmp[2]+dz[d];
				if (new_x<0 || new_x>=n || new_y<0 || new_y>=m || new_z<0 || new_z>=h) continue;
				if (!nvisit[new_x][new_y][new_z]) continue;
				arr[new_x][new_y][new_z] = arr[tmp[0]][tmp[1]][tmp[2]]+1;
				nvisit[new_x][new_y][new_z] = false;
				q.add(new int[] {new_x,new_y,new_z});
			}
		}
		int ans = 0;
		out:
		for (int i=0;i<n;i++) {
			for (int j=0;j<m;j++) {
				for (int k=0;k<h;k++) {
					if (arr[i][j][k]==0) {
						ans = -1;
						break out;
					}
					ans = Math.max(ans, arr[i][j][k]);
				}
			}
		}
		if (ans==-1) {
			System.out.println(ans);			
		} else {
			System.out.println(ans-1);						
		}
	}
}
