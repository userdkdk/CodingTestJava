import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] arr = new int[n];
		for (int i=0;i<n;i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		int sum = 0;
		for (int i=0;i<n-2;i++) {
			if (arr[i]+arr[i+1]+arr[i+2]>m) break;
			for (int j=i+1;j<n-1;j++) {
				if (arr[i]+arr[j]+arr[j+1]>m) break;
				for (int k=j+1;k<n;k++) {
					if (arr[i]+arr[j]+arr[k]<=m) {
						sum = Math.max(arr[i]+arr[j]+arr[k],sum);
					} else break;
				}
			}
		}
		System.out.println(sum);
	}

}
