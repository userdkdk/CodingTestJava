import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class Main {
    static int n, m; static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[100020];
        Arrays.fill(arr,Integer.MAX_VALUE);

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{n,0});
        int ans = 0;
        arr[n] = 0;
        while (!q.isEmpty()) {
            int[] now = q.poll();

            if (now[0]==m) {
                ans++;
                while(!q.isEmpty()) {
                    int[] tmp = q.poll();
                    if (tmp[0]==m) ans++;
                }
                break;
            }
            if (arr[now[0]] < now[1]) continue;
            if (now[0]+1 < 100010 && arr[now[0]+1] >= now[1]+1) {
                arr[now[0]+1] = now[1]+1;
                q.add(new int[]{now[0]+1,now[1]+1});
            }
            if (now[0]-1>=0 && arr[now[0]-1] >= now[1]+1) {
                arr[now[0]-1] = now[1]+1;
                q.add(new int[] {now[0]-1,now[1]+1});
            }
            if (now[0]!=0 && now[0]*2 < 100010 && arr[now[0]*2] >= now[1]+1) {
                arr[now[0]*2] = now[1]+1;
                q.add(new int[] {now[0]*2,now[1]+1});
            }
//            for (int i=0;i<30;i++) {
//                if (arr[i]==Integer.MAX_VALUE) System.out.print(i+": "+0+" ");
//                else System.out.print(i+": "+arr[i]+" ");
//            }
//            System.out.println();
        }
        System.out.println(arr[m]);
        System.out.println(ans);
    }
}
