
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1,1,0,0}; static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 배열 입력
		int[][] arr = new int[n][m];
		for (int i=0;i<n;i++) {
			String str = br.readLine();
			for (int j=0;j<m;j++) {
				arr[i][j] = str.charAt(j)-'0';
			}
		}
		boolean[][] visit = new boolean[n][m];
		
		int[] loc = {0,0,1};
		
		Queue<int[]> q = new LinkedList<>();
		q.add(loc);
		visit[0][0] = true;
		while (!q.isEmpty()) {
			int[] par = q.poll();
			if (par[0]==n-1 && par[1]==m-1) {
				System.out.println(par[2]);
				break;
			}
			for (int d=0;d<4;d++) {
				int new_x = par[0]+dx[d];
				int new_y = par[1]+dy[d];
				if (new_x<0 || new_x>=n || new_y<0 || new_y>=m || visit[new_x][new_y] || arr[new_x][new_y]==0) {
					continue;
				}
				q.add(new int[] {new_x,new_y,par[2]+1});
				visit[new_x][new_y] = true;
			}
		}
	}

	
}
