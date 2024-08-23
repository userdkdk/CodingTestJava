
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static char[][] arr; static int n;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		arr = new char[n][n];
		for (int i=0;i<n;i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j=0;j<n;j++) {
				arr[i][j] = tmp[j];
			}
		}
		int[] dr = {-1,-1,-1,0,0,1,1,1};
		int[] dc = {-1,0,1,-1,1,-1,0,1};
		
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				if (arr[i][j]=='.') {
					int tmp=0;
					for (int d=0;d<8;d++) {
						int new_r = i+dr[d];
						int new_c = j+dc[d];
						if (new_r<0 || new_r>=n || new_c<0 || new_c>=n || arr[new_r][new_c]=='.') continue;
						tmp += arr[new_r][new_c]-'0';
					}
					if (tmp>=10) {
						System.out.print("M");
					} else {
						System.out.print(tmp);
					}
				} else System.out.print("*");
			}
			System.out.println();
		}
	}
}

