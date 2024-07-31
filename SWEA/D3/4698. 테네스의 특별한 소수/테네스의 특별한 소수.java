import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		
		// 소수 리스트 구하기
		List<Integer> num_list = new ArrayList<Integer>();

		for (int i=2;i<1000000;i++) {
			int max = (int) Math.sqrt(i);
			int sum = 0;
			for (int j=1;j<=max;j++) {
				if (i%j==0) {
					sum+=1;
					if (sum>2) break;
				}
			}
			if (sum==1) {
				num_list.add(i);
			}
		}
		
		int T = sc.nextInt();
		
		for (int tc=1;tc<=T;tc++) {
			int D = sc.nextInt();
			int n = sc.nextInt();
			int m = sc.nextInt();
			int res = 0;
			int tar_n = binaryS(num_list,n);
			int tar_m = binaryS(num_list,m);
			if (tar_n>0) tar_n--;
			if (tar_n>0) tar_n--;
			if (tar_m<num_list.size()) tar_m++;
			if (tar_m<num_list.size()) tar_m++;

			for (int i=tar_n;i<tar_m;i++) {
				int check_num = num_list.get(i);
				if (check_num<n || check_num>m) continue;
				while (check_num>0) {
					if (check_num%10==D) {
						res++;
						break;
					}
					check_num /=10;
				}
			}
			System.out.println("#"+tc+" "+res);
		}
	}
	static int binaryS(List<Integer> li, int tar) {
		int sta = 0;
		int las = li.size()-1;
		int mid = 0;
		while (sta<las) {
			mid = (sta+las)/2;
			if (li.get(mid)==tar) {
				return mid;
			}
			else if (li.get(mid)<tar) {
				sta = mid+1;
			} else {
				las = mid-1;
			}
		}

		return las;
	}
}
