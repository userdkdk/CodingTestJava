
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int f = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int g = Integer.parseInt(st.nextToken());
		int u = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int[] arr = new int[f+1];
		boolean[] visit = new boolean[f+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		visit[s]=true;
		
		while (!q.isEmpty()) {
			int num = q.poll();
			if (num-d>=1 && !visit[num-d]) {
				arr[num-d]=arr[num]+1;
				q.add(num-d);
				visit[num-d]=true;
			}
			if (num+u<=f && !visit[num+u]) {
				arr[num+u]=arr[num]+1;
				q.add(num+u);
				visit[num+u]=true;
			}
		}
		if (arr[g]==0 && s!=g) {
			System.out.println("use the stairs");
		} else {
			System.out.println(arr[g]);
		}
		
	}
}
