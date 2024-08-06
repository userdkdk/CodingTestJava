import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1;tc<=T;tc++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int[] arr = new int[n];
			for (int i=0;i<n;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				}
			
			int[] comp = new int[n];
			comp[n-1] = arr[n-1];
			for (int i=n-2;i>=0;i--) {
				comp[i] = Math.max(comp[i+1], arr[i]);
			}
			
			long sum = 0;
			for (int i=0;i<n-1;i++) {
				sum+=comp[i]-arr[i];
			}
			System.out.println("#"+tc+" "+sum);
		}
		
	}
}