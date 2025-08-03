import java.util.*;
import java.io.*;

public class Main {
    static int T, N, K, W; static int[] times; static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc=1;tc<=T;tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            times = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for (int i=1;i<=N;i++) {
                times[i] = Integer.parseInt(st.nextToken());
            }
            list = new ArrayList[N+1];
            int[] indegree = new int[N+1];
            int[] ans = new int[N+1];
            for (int i=1;i<=N;i++) {
                list[i] = new ArrayList<>();
            }
            for (int i=0;i<K;i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list[a].add(b);
                indegree[b]++;
            }
            Queue<Integer> q = new LinkedList<>();
            for (int i=1;i<=N;i++) {
                if (indegree[i]==0) {
                    ans[i] = times[i];
                    q.add(i);
                    indegree[i]--;
                }
            }
            W = Integer.parseInt(br.readLine());

            while (!q.isEmpty()) {
                int now = q.poll();
                for (int to : list[now]) {
                    ans[to] = Math.max(ans[to],ans[now]+times[to]);
                    indegree[to]--;
                    if (indegree[to]==0) q.add(to);
                }
            }
            sb.append(ans[W]).append("\n");
        }
        System.out.println(sb);
    }
}
