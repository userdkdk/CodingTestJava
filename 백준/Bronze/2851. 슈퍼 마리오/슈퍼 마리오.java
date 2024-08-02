
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner sc = new Scanner(System.in);
		int[] arr = new int[10];
		for (int i=0;i<10;i++) {
			arr[i] = sc.nextInt();
		}
		int ans = 0;
		for (int i=0;i<10;i++) {
			if (Math.abs(100-ans)>Math.abs(100-(ans+arr[i]))) {
				ans += arr[i];
			} else if (Math.abs(100-ans)==Math.abs(100-(ans+arr[i]))) {
				ans += arr[i];
				break;
			} else break;
			
			
		}
		System.out.println(ans);
	}

}
