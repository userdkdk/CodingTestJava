import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		char[] arr = sc.next().toCharArray();
		int r=1;
		for (int i=(int)Math.sqrt(arr.length);i>=1;i--) {
			if (arr.length%i==0) {
				r=i;
				break;
			}
		}
		int c = arr.length/r;
		char[] ans = new char[arr.length];
		for (int i=0;i<r;i++) {
			for (int j=0;j<c;j++) {
				ans[c*i+j] = arr[i+r*j];
			}
		}
		String str = new String(ans);
		
		System.out.println(str);
	}
}
