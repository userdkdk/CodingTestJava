
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
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		arr = new int[T+1];
		for (int tc=1;tc<=T;tc++) {
			int n = Integer.parseInt(br.readLine());
			sorting(n,0,tc-1,tc);
			sb.append(arr[(tc-1)/2]+"\n");
		}
		sb.delete(sb.length()-1, sb.length());
		System.out.println(sb);
	}
	static void sorting(int n, int sta, int las, int tc) {
		if (sta==las) {
			System.arraycopy(arr, sta, arr, sta+1, tc-sta);
			arr[sta] = n;
			return;
		}
		int mid = (sta+las)/2;
		if (arr[mid]>=n) {
			las = mid;
			sorting(n, sta, las, tc);
		} else {
			sta = mid+1;
			sorting(n, sta, las, tc);
		}
	}
}

