
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, m; static int[] arr, ans;
	static boolean[] visit;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		visit = new boolean[n];
		ans = new int[m];
		sb = new StringBuilder();
		getans(0);
		sb.delete(sb.length()-2, sb.length()-1);
		System.out.println(sb);
	}
	static void getans(int depth) {
		if (depth==m) {
			for (int j=0;j<m;j++) {
				sb.append(ans[j]+" ");
			}
			sb.append("\n");
			return;
		}
		for (int i=0;i<n;i++) {
			if (visit[i]) continue;
			visit[i] = true;
			ans[depth] = arr[i];
			getans(depth+1);
			visit[i] = false; 
		}
	}
}
