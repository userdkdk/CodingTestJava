import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int[][] sq = new int[6][2];
		int[] count = new int[5];
		int check=1;
		int core = 0;
		for (int i=0;i<6;i++) {
			sq[i][0] = sc.nextInt();
			sq[i][1] = sc.nextInt();
			count[sq[i][0]]++;
			if (count[sq[i][0]]==2) {
				check*=sq[i][0];
			}
		}
		switch (check) {
			case 6: {
				for (int i=0;i<6;i++) {
					if (sq[i][0]==4) {
						core=i;
					}
				}
				break;
			}
			case 3: {
				for (int i=0;i<6;i++) {
					if (sq[i][0]==2) {
						core=i;
					}
				}
				break;
			}
			case 4: {
				for (int i=0;i<6;i++) {
					if (sq[i][0]==3) {
						core=i;
					}
				}
				break;
			}
			case 8: {
				for (int i=0;i<6;i++) {
					if (sq[i][0]==1) {
						core=i;
					}
				}
				break;
			}
		}
		
		int ans = sq[core][1]*sq[(core+5)%6][1] - sq[(core+2)%6][1]*sq[(core+3)%6][1];
		
		System.out.println(ans*num);
		
	}
}
