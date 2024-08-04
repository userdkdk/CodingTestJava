import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int l = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		boolean[] arr = new boolean[l+1];
		int[] expect = {0,0};
		int[] max = {0,0};
		for (int i=0;i<n;i++) {
			String[] str = br.readLine().split(" ");
			int sta = Integer.parseInt(str[0]);
			int las = Integer.parseInt(str[1]);
			if (expect[1]<las-sta) {
				expect[0] = i;
				expect[1] = las-sta;
			}
			int tmp_sum = 0;
			for (int j=sta;j<=las;j++) {
				if (!arr[j]) {
					arr[j] = true;
					tmp_sum++;
				}
			}
			if (max[1]<tmp_sum) {
				max[0] = i;
				max[1] = tmp_sum;
			}
		}
		
		System.out.println(expect[0]+1);
		System.out.println(max[0]+1);
	}
}
