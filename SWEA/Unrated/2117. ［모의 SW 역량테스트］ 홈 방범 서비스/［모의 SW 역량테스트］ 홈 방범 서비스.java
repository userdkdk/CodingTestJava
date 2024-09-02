
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			ArrayList<int[]> arr = new ArrayList<>();
			for (int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0;j<n;j++) {
					if (st.nextToken().equals("1")) {
						arr.add(new int[] {i,j});
					}
				}
			}
			int ans = 1;
			for (int i=0;i<n;i++) {
				for (int j=0;j<n;j++) {
					int[] countNum = new int[2*n];
					for (int p=0;p<arr.size();p++) {
						int r = Math.abs(i-arr.get(p)[0]);
						int c = Math.abs(j-arr.get(p)[1]);
						countNum[r+c]++;
					}
					for (int k=1;k<2*n;k++) {
						countNum[k] += countNum[k-1];
					}
					for (int k=0;k<2*n;k++) {
						if (countNum[k]*m-k*k-(k+1)*(k+1)>=0) {
							ans = Math.max(ans, countNum[k]);
						}
					}
				}
			}
			System.out.println("#"+tc+" "+ans);
		}
	}
}
