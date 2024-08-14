
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
	static String[] cals; static int n; static int[][] arr;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= 10; tc++) {
			// 2중배열을 통해 접근, 요소는 char 배열에 저장
			n = Integer.parseInt(br.readLine());
			cals = new String[n+1];
			arr = new int[n+1][2];
			for (int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				int loc = Integer.parseInt(st.nextToken());
				cals[loc] = st.nextToken();
				int tmp = st.countTokens();
				for (int j=0;j<tmp;j++) {
					arr[loc][j] = Integer.parseInt(st.nextToken());
				}
			}

			getans(1);
			int ans = (int) Double.parseDouble(cals[1]);
			System.out.println("#"+tc+" "+ans);
		}
	}
	static void getans(int i) {
		if (i>n) {
			return;
		}
		if (arr[i][0]>0) {
			getans(arr[i][0]);
		}
		if (arr[i][1]>0) {
			getans(arr[i][1]);
		}
		if (cals[i].equals("+") || cals[i].equals("-") || cals[i].equals("*") || cals[i].equals("/")) {
			double num1 = Double.parseDouble(cals[arr[i][0]]);
			double num2 = Double.parseDouble(cals[arr[i][1]]);
			if (cals[i].equals("+")) {
				cals[i] = Double.toString(num1+num2);
			} else if (cals[i].equals("-")) {
				cals[i] = Double.toString(num1-num2);
			} else if (cals[i].equals("*")) {
				cals[i] = Double.toString(num1*num2);
			} else {
				cals[i] = Double.toString(num1/num2);
			}
		}
	}
}
