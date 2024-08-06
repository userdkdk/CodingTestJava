import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[][] arr = new int[n+2][m+2];
			int sum = 0;
			
			for (int i=1;i<=n;i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j=1;j<=m;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int[] dx = {1,-1,0,0};
			int[] dy = {0,0,1,-1};
			
			
			for (int i=1;i<=n;i++) {
				for (int j=1;j<=m;j++) {
					int tmp = arr[i][j];
					for (int k=0;k<4;k++) {
						tmp += arr[i+dx[k]][j+dy[k]];
					}
					sum = Math.max(sum, tmp);
				}
			}
			System.out.println("#"+tc+" "+sum);
		}
		
	}
}