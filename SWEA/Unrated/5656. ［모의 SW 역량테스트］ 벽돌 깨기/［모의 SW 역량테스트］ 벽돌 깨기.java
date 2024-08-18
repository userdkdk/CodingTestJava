
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	static int n, w, h, tmp_ans; static int[][] arr; static int[] cases;
	static int[] dr = {-1,1,0,0}; static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			arr = new int[h][w];
			for (int i=0;i<h;i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0;j<w;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			tmp_ans = Integer.MAX_VALUE;
			cases = new int[n];
			getCase(0);
			System.out.println("#"+tc+" "+tmp_ans);
		}

	}
	static void getCase(int depth) {
		if (depth==n) {
			int[][] arr_copy = new int[h][w];
			// 배열복사 추가
			for (int i=0;i<h;i++) {
				for (int j=0;j<w;j++) {
					arr_copy[i][j] = arr[i][j];
				}
			}
			// 값 반환 수정 필요
			int tmp = bomb(cases,arr_copy);
//			if (tmp_ans>tmp) System.out.println(Arrays.toString(cases));
			tmp_ans = Math.min(tmp_ans, tmp);
			return;
		}
		for (int i=0;i<w;i++) {
			cases[depth] = i;
			getCase(depth+1);
		}
	}
	static int bomb(int[] cs, int[][] arr_copy) {
		int ttmp = 0;
		for (int css=0;css<n;css++) {
			int c = cs[css];
			if (arr_copy[h-1][c]==0) continue;
			boolean[][] visit = new boolean[h][w];
			
			int r=0;
			for (int i=0;i<=h-1;i++) {
				if (arr_copy[i][c]!=0) {
					r=i;
					break;
				}
			}
			Queue<int[]> q = new LinkedList<int[]>();
			q.add(new int[] {r,c});
			visit[r][c] = true;
			
			while (!q.isEmpty()) {
				int[] loc = q.poll();
				int splash = arr_copy[loc[0]][loc[1]];
				arr_copy[loc[0]][loc[1]]=0;
				// 스플래시 추가
				for (int d=0;d<4;d++) {
					for (int sp=1;sp<splash;sp++) {
						int new_r = loc[0]+dr[d]*sp;
						int new_c = loc[1]+dc[d]*sp;
						if (new_r<0 || new_r>=h || new_c<0 || new_c>=w || visit[new_r][new_c]) continue;
						if (arr_copy[new_r][new_c]==0) continue;
						q.add(new int[] {new_r,new_c});
						visit[new_r][new_c] = true;
					}
				}
			}
			// 중간 0 비워주기 추가
			int[][] new_arr = new int[h][w];
			for (int j=0;j<=w-1;j++) {
				int tmp_i=h-1;
				for (int i=h-1;i>=0;i--) {
					if (arr_copy[i][j]!=0) new_arr[tmp_i--][j] = arr_copy[i][j];
				}
			}
			arr_copy = new_arr;
		}
		for (int i=0;i<=h-1;i++) {
			for (int j=0;j<=w-1;j++) {
				if (arr_copy[i][j]!=0) ttmp++;
			}
		}
		return ttmp;
	}
}
