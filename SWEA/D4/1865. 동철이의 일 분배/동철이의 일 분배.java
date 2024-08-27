
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	static int n; static int[][] arr; static boolean[] visit;
	static int[] loc; static double max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc=1;tc<=T;tc++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			for (int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0;j<n;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			loc = new int[n];
			visit = new boolean[n];
			max = 0;
			getans(0, 100);
			System.out.println("#"+tc+" "+String.format("%.6f", max));
		}
	}
	static void getans(int depth, double ans) {
		if (ans<=max) return;
		if (depth==n) {
			max = Math.max(max, ans);
			return;
		}
		for (int i=0;i<n;i++) {
			if (visit[i]) continue;
			visit[i] = true;
			getans(depth+1,(double)ans*arr[depth][i]/100);
			visit[i] = false;
		}
	}
}
