import java.util.*;
import java.io.*;

public class Main {
    static int N, M; static int[] ladder, snake;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ladder = new int[101];
        snake = new int[101];
        Arrays.fill(ladder,-1);
        Arrays.fill(snake,-1);
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a] = b;
        }
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            snake[a] = b;
        }
        int ans = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1,0});
        while (!q.isEmpty()) {
            int[] now = q.poll();
            if (now[0]+6>=100) {
                ans = now[1]+1;
                break;
            }
            for (int i=1;i<=6;i++) {
                if (ladder[now[0]+i]!=-1) {
                    q.add(new int[]{ladder[now[0]+i],now[1]+1});
                }
                if (snake[now[0]+i]!=-1) {
                    q.add(new int[]{snake[now[0]+i],now[1]+1});
                }
            }
            for (int i=6;i>=1;i--) {
                if (ladder[now[0]+i]!=-1 || snake[now[0]+i]!=-1) continue;
                q.add(new int[]{now[0]+i,now[1]+1});
                break;
            }
        }
        System.out.println(ans);

    }
}
