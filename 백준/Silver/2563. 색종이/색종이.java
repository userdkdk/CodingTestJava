import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		boolean[][] arr = new boolean[100][100];
		for (int tc=1;tc<=T;tc++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			for (int i=n;i<n+10;i++) {
				for (int j=m;j<m+10;j++) {
					arr[i][j] = true;
				}
			}
		}
		int sum = 0;
		for (int i=0;i<100;i++) {
			for (int j=0;j<100;j++) {
				if (arr[i][j]==true) {
					sum+=1;
				}
			}
		}
		System.out.println(sum);
		
	}

}
