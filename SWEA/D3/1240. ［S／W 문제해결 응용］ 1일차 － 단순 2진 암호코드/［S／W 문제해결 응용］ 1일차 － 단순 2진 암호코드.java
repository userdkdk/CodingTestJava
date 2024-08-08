
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			char[] arr = new char[m];
			boolean flag = false;
			int las = 0;
			for (int i=0;i<n;i++) {
				char[] tmp = br.readLine().toCharArray();
				if (!flag) {
					for (int j=m-1;j>=0;j--) {
						if (tmp[j]-'0'==1) {
							arr=tmp;
							flag = true;
							las = j;
							break;
						}
					}
				}
					
			}
			StringBuilder sb = null;
			int check = 0;
			int ans = 0;
			flag = true;
			for (int i=las;i>=las-49;i-=7) {
				sb = new StringBuilder();
				for (int j=0;j<7;j++) {
					sb.insert(0, arr[i-j]);
				}
				int tmp_num = map.get(sb.toString());
				if (flag) {
					check+=tmp_num;
					flag = false;
				}
				else {
					check += tmp_num*3;
					flag = true;
				}
				ans += tmp_num;
			}

			if (check%10==0) {
				System.out.println("#"+tc+" "+ans);
			} else {
				System.out.println("#"+tc+" "+0);
			}
			
		}
	}
	
	static Map<String,Integer> map = new HashMap<String, Integer>();
	static {
		map.put("0001101", 0);
		map.put("0011001", 1);
		map.put("0010011", 2);
		map.put("0111101", 3);
		map.put("0100011", 4);
		map.put("0110001", 5);
		map.put("0101111", 6);
		map.put("0111011", 7);
		map.put("0110111", 8);
		map.put("0001011", 9);
	}
	
}
