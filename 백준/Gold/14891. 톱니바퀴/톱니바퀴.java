import java.util.*;
import java.io.*;

public class Main {
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;
        arr = new int[4][8];
        for (int i=0;i<4;i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j=0;j<8;j++) {
                arr[i][j] = tmp[j] - '0';
            }
        }
        int T = Integer.parseInt(br.readLine());
        for (int tc=0;tc<T;tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken());
            Queue<int[]> q = new LinkedList<>();
            ArrayList<int[]> list = new ArrayList<>();
            list.add(new int[] {n,d});
            q.add(new int[] {n,d,-1});
            q.add(new int[] {n,d,1});

            while (!q.isEmpty()) {
                int[] now = q.poll();
                // left
                int nowLoc = now[0];
                int nowD = now[1];
                int nowDir = now[2];
                if (nowDir==-1 && nowLoc>0) {
                    if (arr[nowLoc][6]!=arr[nowLoc-1][2]) {
                        list.add(new int[] {nowLoc-1,-nowD});
                        q.add(new int[] {nowLoc-1, -nowD, -1});
                    }
                }

                // right
                if (nowDir==1 && nowLoc<3) {
                    if (arr[nowLoc][2] != arr[nowLoc+1][6]) {
                        list.add(new int[] {nowLoc+1, -nowD});
                        q.add(new int[] {nowLoc+1, -nowD, 1});
                    }
                }
            }

            for (int[] now : list) {
                rotation(now[0],-now[1]);
            }
        }

        int ans = 0;
        for (int i=0;i<4;i++) {
            if (arr[i][0]==1) {
                ans += (1<<i);
            }
        }
        System.out.println(ans);
    }
    // rotation
    static void rotation(int loc, int dir) {
        int[] tmp = new int[8];
        for (int i=0;i<8;i++) {
            tmp[i] = arr[loc][(i+dir+8)%8];
        }
        arr[loc] = tmp;
    }
}
