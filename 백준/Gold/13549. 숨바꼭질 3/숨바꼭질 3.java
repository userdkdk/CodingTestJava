import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
	static int[] dn = {1,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[101000];
		for (int i=0;i<101000;i++) {
			arr[i] = Integer.MAX_VALUE;
		}
		arr[n] = 0;
		Deque<Integer> dq = new ArrayDeque<>();
		dq.addFirst(n);
		int ans = -1;
		boolean[] visit = new boolean[101000];
		while (!dq.isEmpty()) {
			int now = dq.removeFirst();
			int tmp = 2*now;
			while (tmp<101000) {
				if (arr[now]>=arr[tmp]) break;
				arr[tmp] = arr[now];
				dq.addFirst(tmp);
				tmp *= 2;
			}
			for (int d=0;d<2;d++) {
				int new_n = now + dn[d];
				if (new_n<0 || new_n>=101000) continue;
				if (arr[now]+1>=arr[new_n]) continue;
				arr[new_n] = arr[now]+1;
				dq.addLast(new_n);
			}
		}
		System.out.println(arr[m]);
	}
}