
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		for (int tc=1;tc<=10;tc++) {
			int realtc = sc.nextInt();
			int n = sc.nextInt();
			int[] arr1 = new int[100];
			int[] arr2 = new int[100];
			int[] check = new int[100];
			for (int i=0;i<n;i++) {
				int num = sc.nextInt();
				if (check[num]==0) {
					arr1[num] = sc.nextInt();
					check[num]++;
				} else {
					arr2[num] = sc.nextInt();
				}
			}
			Stack<Integer> st = new Stack<>();
			if (arr1[0]!=0) {
				st.add(arr1[0]);
			}
			if (arr2[0]!=0) {
				st.add(arr2[0]);
			}
			int tmp;
			int ans = 0;
			while (st.size()!=0) {
				tmp = st.pop();
				if (tmp==99) {
					ans = 1;
					break;
				}
				if (arr1[tmp]!=0) {
					st.add(arr1[tmp]);
					arr1[tmp] = 0;
				}
				if (arr2[tmp]!=0) {
					st.add(arr2[tmp]);
					arr1[tmp] = 0;
				}
			}
			System.out.println("#"+tc+" "+ans);
		}
		
	}
}
