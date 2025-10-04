import java.io.*;
import java.util.*;

public class Main {
    static int N, M; static int[][] arr; static int[] locs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        locs = new int[N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<M;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i=0;i<N;i++) {
            Arrays.sort(arr[i]);
        }

        PriorityQueue<Node> pq = new PriorityQueue<Node>(
                Comparator.comparingInt(node -> node.val));
        int curMax = 0;
        for (int i=0;i<N;i++) {
            pq.add(new Node(i,arr[i][0]));
            curMax = Math.max(curMax,arr[i][0]);
        }
        int ans = Integer.MAX_VALUE;
        while (true) {
            Node cur = pq.poll();
            ans = Math.min(ans,curMax - cur.val);

            int m = locs[cur.cls];
            if (m==M-1) break;
            pq.add(new Node(cur.cls,arr[cur.cls][m+1]));
            curMax = Math.max(curMax,arr[cur.cls][m+1]);
            locs[cur.cls]++;
        }
        System.out.println(ans);

    }
    static class Node {
        int val, cls;
        public Node(int c, int v){
            this.cls = c;
            this.val = v;
        }
    }
}
