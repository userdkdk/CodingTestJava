
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[][] arr = new int[11][11];
		for (int i=0;i<11;i++) {
			arr[i][0]=1;
		}
		for (int i=1;i<11;i++) {
			for (int j=1;j<=i;j++) {
				arr[i][j] = arr[i-1][j-1]+arr[i-1][j];
			}
		}
		
		for (int tc=1;tc<=T;tc++) {
			int n = sc.nextInt();
            System.out.println("#"+tc);
			for (int i=0;i<n;i++) {
				for (int j=0;j<=i;j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
		}

	}
}
