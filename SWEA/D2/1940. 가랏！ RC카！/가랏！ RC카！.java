
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc=1;tc<=T;tc++) {
			int ans = 0;
			int v = 0;
			int num = sc.nextInt();
			for (int i=0;i<num;i++) {
				int dir = sc.nextInt();
				if (dir==1) {
					int size = sc.nextInt();
					v+=size;
				} else if (dir==2) {
					int size = sc.nextInt();
					v = v-size>0 ? v-size : 0;
				}
				ans+=Math.abs(v);
			}
			System.out.println("#"+tc+" "+ans);
		}

	}
}
