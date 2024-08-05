import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[][] arr = new int[7][2];
		for (int i=0;i<n;i++) {
			int sex = sc.nextInt();
			int grade = sc.nextInt();
			arr[grade][sex]++;
		}
		int ans = 0;
		for (int i=1;i<=6;i++) {
			for (int j=0;j<=1;j++) {
				ans+=arr[i][j]/k;
				if (arr[i][j]%k!=0) ans++;
			}
		}
		System.out.println(ans);
	}
}