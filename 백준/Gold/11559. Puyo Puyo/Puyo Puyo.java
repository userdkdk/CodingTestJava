import java.util.*;
import java.io.*;

public class Main {
    static char[][] arr; static boolean[][] visited;
    static int[] dr = {1,-1,0,0}; static int[] dc = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        arr = new char[12][6];

        for (int i=0;i<12;i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j=0;j<6;j++) {
                arr[i][j] = tmp[j];
            }
        }

        // 될때까지 돌리기
        int ans = 0;
        while (puyo()) {
            ans++;
        }
        System.out.println(ans);
    }
    static boolean puyo() {
        boolean flag = false;
        for (int i=0;i<12;i++) {
            for (int j=0;j<6;j++) {
                if (arr[i][j]=='.') continue;
                visited = new boolean[12][6];
                ArrayList<int[]> list = bfs(i,j,arr[i][j]);
                if (list.size()<4) continue;
                for (int[] l : list) {
                    arr[l[0]][l[1]] = '.';
                }
                flag = true;
            }
        }
        if (flag) blockDown();
        return flag;
    }

    static void blockDown() {
        for (int j=0;j<6;j++) {
            char[] tmp = new char[12];

            for (int i=0;i<12;i++) {
                tmp[i] = '.';
            }

            int idx = 11;
            for (int i=11;i>=0;i--) {
                if (arr[i][j]!='.') {
                    tmp[idx--] = arr[i][j];
                }
            }

            for (int i=0;i<12;i++) {
                arr[i][j] = tmp[i];
            }
        }
    }

    static ArrayList<int[]> bfs(int a, int b, char c) {
        ArrayList<int[]> list = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {a,b});
        list.add(new int[] {a,b});
        visited[a][b] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int d=0;d<4;d++) {
                int new_r = now[0] + dr[d];
                int new_c = now[1] + dc[d];
                if (!goCheck(new_r,new_c, c)) continue;
                q.add(new int[] {new_r,new_c});
                visited[new_r][new_c] = true;
                list.add(new int[]{new_r, new_c});
            }
        }
        return list;
    }

    static boolean goCheck(int a, int b, char c) {
        if (a<0 || a>=12 || b<0 || b>=6 || arr[a][b]!=c || visited[a][b]) {
            return false;
        }
        return true;
    }

}
