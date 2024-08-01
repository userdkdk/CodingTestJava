import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc=0;tc<4;tc++) {
			int[] tmp = new int[4];
			int[] sq1 = new int[4];
			int[] sq2 = new int[4];
			for (int i=0;i<4;i++) {
				tmp[i] = sc.nextInt();
			}
			for (int i=0;i<4;i++) {
				sq2[i] = sc.nextInt();
			}
			if (tmp[0]<=sq2[0]) {
				sq1 = tmp;
			} else {
				sq1 = sq2;
				sq2 = tmp;
			}
			String ans = "";
			if (sq2[1]>sq1[3] || sq2[3]<sq1[1] || sq2[0]>sq1[2]) {
				ans = "d";
			} else if (sq2[1]==sq1[3] || sq2[3]==sq1[1]) {
				if (sq2[0]<sq1[2]) {
					ans = "b";
				} else if (sq2[0]==sq1[2]) {
					ans = "c";
				} else ans = "d";
			} else {
				if (sq2[0]==sq1[2]) {
					ans = "b";
				} else ans = "a";
			}
			System.out.println(ans);
			
		}

	}
}
