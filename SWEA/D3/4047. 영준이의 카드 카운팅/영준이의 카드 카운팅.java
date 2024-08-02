
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1;tc<=T;tc++) {
			int[][] arr = new int[4][13];
			char[] card = br.readLine().toCharArray();
			int ans=0;
			for (int i=0;i<card.length;i+=3) {
				int num = (int)(card[i+1]-'0')*10+(int)(card[i+2]-'0')-1;
				if (card[i]=='S') {
					arr[0][num]++;
					if (arr[0][num]==2) {
						ans--;
						break;
					}
				} else if (card[i]=='D') {
					arr[1][num]++;
					if (arr[1][num]==2) {
						ans--;
						break;
					}
				} else if (card[i]=='H') {
					arr[2][num]++;
					if (arr[2][num]==2) {
						ans--;
						break;
					}
				} else {
					arr[3][num]++;
					if (arr[3][num]==2) {
						ans--;
						break;
					}
				}
			}
			if (ans==-1) {
				System.out.println("#"+tc+" ERROR");
			} else {
				System.out.print("#"+tc);
				for (int i=0;i<4;i++) {
					int sum = 0;
					for (int j=0;j<13;j++) {
						sum+=arr[i][j];
					}
					System.out.print(" "+(13-sum));
				}
				System.out.println();
			}
		}

	}
}
