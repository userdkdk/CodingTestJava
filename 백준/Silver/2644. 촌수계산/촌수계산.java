
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr; static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1][n+2];
		st = new StringTokenizer(br.readLine());
		int tar1 = Integer.parseInt(st.nextToken());
		int tar2 = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(br.readLine());
		for (int i=0;i<t;i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[p][arr[p][n+1]++]=c;
			arr[c][n] = p;
		}
		int[] tar2p = findp(tar2);
		boolean flag = false;
		int ans = 0;
		out:
		while (tar1!=0) {
			for (int i=0;i<tar2p.length;i++) {
				if (tar1==tar2p[i]) {
					ans +=i;
					flag = true;
					break out;
				}
			}
			tar1 = arr[tar1][n];
			ans++;
		}
		if (flag) {
			System.out.println(ans);			
		} else {
			System.out.println(-1);
		}

	}
	static int[] findp(int tar) {
		int[] ans = new int[n+1];
		int i = 0;
		while(tar!=0) {
			ans[i++]=tar;
			tar = arr[tar][n];
		}
		return ans;
	}
}
