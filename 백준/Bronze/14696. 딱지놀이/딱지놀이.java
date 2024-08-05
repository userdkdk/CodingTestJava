import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for (int tc=1;tc<=n;tc++) {
			int[] a = new int[5];
			int[] b = new int[5];
			
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			int c = Integer.parseInt(st.nextToken());
			
			for (int i=0;i<c;i++) {
				a[Integer.parseInt(st.nextToken())]++;
			}

			str = br.readLine();
			st = new StringTokenizer(str," ");
			c = Integer.parseInt(st.nextToken());
			for (int i=0;i<c;i++) {
				b[Integer.parseInt(st.nextToken())]++;
			}
			int num = 4;
			
			while (true) {
				if (a[num]>b[num]) {
					System.out.println("A");
					break;
				} else if (a[num]<b[num]) {
					System.out.println("B");
					break;
				} else {
					num--;
				}
				if (num==-1) {
					System.out.println("D");
					break;
				}
			}
			
			
		}
	}
}