
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
	static int[][] arr; static boolean[][] visit;
	static int n, max, k;
	static int[] dr = {1,-1,0,0}; static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			arr = new int[n][n];
			
			ArrayList<int[]> starts = new ArrayList<int[]>();
			starts.add(new int[] {0,0,0});
			for (int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0;j<n;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j]>starts.get(0)[2]) {
						starts = new ArrayList<int[]>();
						starts.add(new int[] {i,j,arr[i][j]});
					} else if (arr[i][j]==starts.get(0)[2]) {
						starts.add(new int[] {i,j,arr[i][j]});
					}
				}
			}
			max = 0;
			for (int num=0;num<starts.size();num++) {
				for (int ni=0;ni<n;ni++) {
					for (int nj=0;nj<n;nj++) {
						for (int di=0;di<=k;di++) {
							visit = new boolean[n][n];
							arr[ni][nj]-=di;
							ArrayList<int[]> check = new ArrayList<int[]>();
							getans(0,starts.get(num), check);
							arr[ni][nj]+=di;
						}
					}
				}
			}
			System.out.println("#"+tc+" "+(max+1));
		}
	}
	static void getans(int depth, int[] loc, ArrayList<int[]> check) {
		visit[loc[0]][loc[1]] = true;
		check.add(loc);
		for (int d=0;d<4;d++) {
			int new_r = loc[0]+dr[d];
			int new_c = loc[1]+dc[d];
			if (new_r<0 || new_r>=n || new_c<0 || new_c>=n || visit[new_r][new_c]) {
				continue;
			}
			if (arr[new_r][new_c]<loc[2]) {
				visit[new_r][new_c] = true;
				getans(depth+1,new int[] {new_r,new_c,arr[new_r][new_c]}, check);
				visit[new_r][new_c] = false;
			}
		}
		max = Math.max(depth, max);
		visit[loc[0]][loc[1]] = false;
	}
}
