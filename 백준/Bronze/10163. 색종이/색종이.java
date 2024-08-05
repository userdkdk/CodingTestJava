import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] arr = new int[1002][1002];
		for (int p=1;p<=n;p++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			
			for (int i=x1;i<x1+x2;i++) {
				for (int j=y1;j<y1+y2;j++) {
					arr[i][j] = p;
				}
			}
		}
		int[] ans = new int[n+1];
		for (int i=0;i<1002;i++) {
			for (int j=0;j<1002;j++) {
				ans[arr[i][j]]++;
			}
		}
		for (int i=1;i<=n;i++) {
			System.out.println(ans[i]);
		}
	}
}