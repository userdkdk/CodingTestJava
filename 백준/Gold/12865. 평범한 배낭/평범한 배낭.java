
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, k, tmp_weight, tmp_value, ans; static int[] weight, value;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		weight = new int[n];
		value = new int[n];
		for (int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		int[] arr = new int[k+1];
		for (int i=0;i<n;i++) {
			for (int j=k;j>=weight[i];j--) {
				arr[j] = Math.max(arr[j], arr[j-weight[i]]+value[i]);
			}
		}
		System.out.println(arr[k]);
	}

}

