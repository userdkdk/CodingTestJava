
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+1];
		for (int i=1;i<=n;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		if (n==1) {
			System.out.println(arr[1]);
		} else if (n==2) {
			System.out.println(arr[2]+arr[1]);
		} else {
			int[] ans1 = new int[n+1];
			int[] ans2 = new int[n+1];
			ans1[1] = arr[1];
			ans1[2] = arr[2];
			ans2[1] = arr[1];
			ans2[2] = ans1[1]+ans1[2];
			for (int i=3;i<=n;i++) {
				ans2[i] = Math.max(ans2[i-1],ans1[i-1]+arr[i]);
				ans1[i] = Math.max(ans1[i-2]+arr[i], ans2[i-2]+arr[i]);
			}
			int max = 0;
			for (int i=n;i>=n-3;i--) {
				max = Math.max(max, Math.max(ans1[i], ans2[i]));
			}
			System.out.println(max);
		}
	}
	
}
