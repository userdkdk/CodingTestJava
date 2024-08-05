import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc=1;tc<=T;tc++) {
			char[] arr = sc.next().toCharArray();
			int tmp = 0;
			int ans = 0;
			for (char c : arr) {
				if (c=='O') {
					ans+=++tmp;
				} else {
					tmp = 0;
				}
			}
			System.out.println(ans);
			
		}
	}
}