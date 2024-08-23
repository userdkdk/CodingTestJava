
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr; static int n; static int[] loc;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		for (int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<n;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Stack<int[]> s = new Stack<int[]>();
		loc = new int[] {0,1,0};
		s.add(loc);
		int ans = 0;
		int[] now;
		while (!s.isEmpty()) {
			now = s.pop();
			if (now[0]==n-1 && now[1]==n-1) {
				ans++;
			}
			if (now[2]==0) {
				if (checkRight(now)) {
					s.add(new int[] {now[0],now[1]+1,0});
				}
				if (checkDiag(now)) {
					s.add(new int[] {now[0]+1,now[1]+1,2});
				}
			} else if (now[2]==1) {
				if (checkDown(now)) {
					s.add(new int[] {now[0]+1,now[1],1});
				}
				if (checkDiag(now)) {
					s.add(new int[] {now[0]+1,now[1]+1,2});
				}
			} else {
				if (checkRight(now)) {
					s.add(new int[] {now[0],now[1]+1,0});
				}
				if (checkDown(now)) {
					s.add(new int[] {now[0]+1,now[1],1});
				}
				if (checkDiag(now)) {
					s.add(new int[] {now[0]+1,now[1]+1,2});
				}
			}
		}
		System.out.println(ans);
	}
	
	static boolean checkRight(int[] loc) {
		int new_c = loc[1]+1;
		if (new_c<n && arr[loc[0]][new_c]==0) {
			return true;
		}
		return false;
	}
	static boolean checkDown(int[] loc) {
		int new_r = loc[0]+1;
		if (new_r<n && arr[new_r][loc[1]]==0) {
			return true;
		}
		return false;
	}
	static boolean checkDiag(int[] loc) {
		int new_r = loc[0]+1;
		int new_c = loc[1]+1;
		if (new_r<n && new_c<n && arr[new_r][loc[1]]==0 && arr[loc[0]][new_c]==0 &&
				arr[new_r][new_c]==0) {
			return true;
		}
		return false;
	}
}
