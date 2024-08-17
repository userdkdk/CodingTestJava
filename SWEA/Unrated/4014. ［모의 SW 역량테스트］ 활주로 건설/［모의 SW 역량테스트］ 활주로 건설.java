
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
	static int[][] arr; static int n, x; static boolean[][] visit;
	static int ans=0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			arr = new int[n][n];
			for (int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0;j<n;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = 0;
			visit = new boolean[n][n];
			hor_deploy();
			visit = new boolean[n][n];
			ver_deploy();
			System.out.println("#"+tc+" "+ans);
		}
	}
	static void hor_deploy() {
		out:
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				if (j<n-1 && arr[i][j]<arr[i][j+1]) {
					if (arr[i][j+1]-arr[i][j]>1) continue out;
					for (int k=0;k<x;k++) {
						if (j-k<0 || visit[i][j-k]) continue out;
						visit[i][j-k] = true;
					}
				}
				if (j<n-1 && arr[i][j]>arr[i][j+1]) {
					if (arr[i][j]-arr[i][j+1]>1) continue out;
					for (int k=1;k<=x;k++) {
						if (j+k>=n || visit[i][j+k]) continue out;
						visit[i][j+k] = true;
					}
				}
			}
			ans++;
		}
	}
	static void ver_deploy() {
		out:
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				if (j<n-1 && arr[j][i]<arr[j+1][i]) {
					if (arr[j+1][i]-arr[j][i]>1) continue out;
					for (int k=0;k<x;k++) {
						if (j-k<0 || visit[j-k][i]) continue out;
						visit[j-k][i] = true;
					}
				}
				if (j<n-1 && arr[j][i]>arr[j+1][i]) {
					if (arr[j][i]-arr[j+1][i]>1) continue out;
					for (int k=1;k<=x;k++) {
						if (j+k>=n || visit[j+k][i]) continue out;
						visit[j+k][i] = true;
					}
				}
			}
			ans++;
		}
	}
}
