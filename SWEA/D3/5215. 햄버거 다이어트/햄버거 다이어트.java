
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	static int ans, l;
	static int[] score, price, score_sum, price_sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			
			score = new int[n];
			price = new int[n];
			
			for (int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine()," ");
				score[i] = Integer.parseInt(st.nextToken());
				price[i] = Integer.parseInt(st.nextToken());
			}
			ans = 0;
			for (int i=1;i<=n;i++) {
				score_sum = new int[i];
				price_sum = new int[i];
				getans(n, i);
			}
			System.out.println("#"+tc+" "+ans);
		}
	}
	static void getans(int num, int depth) {
		if (depth==0) {
			int tmp_score = 0;
			int tmp_price = 0;
			for (int i=0;i<score_sum.length;i++) {
				tmp_score+=score_sum[i];
				tmp_price+=price_sum[i];
			}
			if (tmp_price<=l) {
				ans = Math.max(ans, tmp_score);
			}
			return;
		} else if (num<depth) {
			return;
		} else {
			score_sum[depth-1] = score[num-1];
			price_sum[depth-1] = price[num-1];
			getans(num-1,depth-1);
			getans(num-1,depth);
		}
	}
}
