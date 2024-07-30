import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer arrs =new StringTokenizer(br.readLine());
		int num = Integer.parseInt(arrs.nextToken());
		int M = Integer.parseInt(arrs.nextToken());
		int L = Integer.parseInt(arrs.nextToken());
		int[] arr = new int[num];
		int cnt = 0;
		int loc = 0;
		while (arr[loc]<M) {
			if (arr[loc]%2==1) {
				loc = (loc+L)%num;
				arr[loc]++;
			} else {
				loc = (loc-L+num)%num;
				arr[loc]++;
			}
			cnt++;
		}
		System.out.println(cnt-1);
	}
}