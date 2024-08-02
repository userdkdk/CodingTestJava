import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		char[] arr = sc.next().toCharArray();
		
		int ans = 0;
		for (int i=0;i<arr.length;i++) {
			if (i<arr.length-1) {
				if (arr[i]=='c') {
					if (arr[i+1]=='=' || arr[i+1]=='-') {
						i++;
					}
				} else if (arr[i]=='d') {
					if (arr[i+1]=='-') {
						i++;
					} else if (i<arr.length-2 && arr[i+1]=='z' && arr[i+2]=='=') {
						i+=2;
					}
				} else if (arr[i+1]=='j') {
					if (arr[i]=='l' || arr[i]=='n') {
						i++;
					}
				} else if (arr[i+1]=='=') {
					if (arr[i]=='s' || arr[i]=='z') {
						i++;
					}
				}
			}
			ans++;
		}
		System.out.println(ans);
	}
}
