import java.io.*;
import java.util.*;

public class Main {
    static int N; static int[] arr; static Queue<int[]> q; static boolean[][] visited;
    static boolean[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        arr = new int[3];
        for (int i=0;i<3;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[201][201];
        answer = new boolean[201];
        q = new ArrayDeque<>();
        q.add(new int[]{0,0,arr[2]});
        visited[0][0] = true;
        answer[arr[2]] = true;
        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i=0;i<3;i++) {
                for (int j=0;j<3;j++) {
                    if (i==j) continue;

                    int[] next = {now[0],now[1],now[2]};
                    int move = Math.min(next[i], arr[j] - next[j]);
                    if (move==0) continue;
                    next[i] -= move;
                    next[j] += move;
                    if (!visited[next[0]][next[1]]) {
                        visited[next[0]][next[1]] = true;
                        if (next[0]==0) answer[next[2]] = true;
                        q.add(next);
                    }
                }
            }
        }
        for (int i=0;i<201;i++) {
            if (answer[i]) System.out.print(i + " ");
        }
    }
}
