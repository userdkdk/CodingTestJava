
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;


public class Solution {
	static int n, right_sum, left_sum, ans;
	static int[] arr; static boolean[] visit; static int[] rarr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for (int tc=1;tc<=T;tc++) {
			// 값 입력받기
			n = Integer.parseInt(br.readLine());
			arr = new int[n];
			visit = new boolean[n];
			rarr = new int[n];
			
			st = new StringTokenizer(br.readLine());
			for (int i=0;i<n;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			ans = 0;
			Arrays.sort(arr);
			allnum(0);
			System.out.println("#"+tc+" "+ans);
		}
	}
	static void allnum(int num) {
		if (num==n) {
			getans(0);
			return;
		}
		for (int i=0;i<n;i++) {
			if (visit[i]) continue;
			else {
				visit[i] = true;
				num++;
				rarr[num-1] = arr[i];
				allnum(num);
				visit[i] = false;
				num--;
			}
		}
	}
	static void getans(int loc) {
		if (loc==n) {
			if (left_sum>=right_sum) {
				ans++;
			}
			return;
		}
		for (int i=loc;i<n;) {
			
			if (left_sum>=right_sum+rarr[i]) {
				right_sum+=rarr[i];
				getans(i+1);
				right_sum-=rarr[i];
			}
			left_sum+=rarr[i];
			getans(i+1);
			left_sum-=rarr[i];
			break;
		}
	}
}
