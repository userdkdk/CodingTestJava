
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, m; static int[] arr;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[m];
		sb = new StringBuilder();
		getans(0);
		System.out.println(sb);
	}
	static void getans(int depth) {
		if (depth==m) {
			for (int j=0;j<m;j++) {
				sb.append(arr[j]+" ");
			}
			sb.append("\n");
			return;
		}
		for (int i=1;i<=n;i++) {
			arr[depth] = i;
			getans(depth+1);
		}
	}
}
