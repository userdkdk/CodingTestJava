import java.util.*;
import java.io.*;

public class Main {
    static int N; static int day = 10001; static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        list = new ArrayList[day];
        for (int i=0;i<day;i++) {
            list[i] = new ArrayList<>();
        }
        int max_day = 0;
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[b].add(a);
            max_day = Math.max(max_day,b);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int ans = 0;
        for (int i=max_day;i>=1;i--) {
            for (int num : list[i]) {
                pq.add(num);
            }
            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }
        System.out.println(ans);

    }

}
