import java.io.*;
import java.util.*;

class Main
{
    static int L, R, C, ans; static char[][][] arr; static int[] sta, end;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            arr = new char[L][R][C];
            visited = new boolean[L][R][C];
            sta = new int[4];
            end = new int[3];
            if (L==0) break;
            for (int i=0;i<L;i++) {
                for (int j=0;j<R;j++) {
                    char[] tmp_arr = br.readLine().toCharArray();
                    for (int k=0;k<C;k++) {
                        arr[i][j][k] = tmp_arr[k];
                        if (arr[i][j][k]=='S') {
                            sta[0] = i;
                            sta[1] = j;
                            sta[2] = k;
                            visited[i][j][k] = true;
                        }
                        if (arr[i][j][k]=='E') {
                            end[0] = i;
                            end[1] = j;
                            end[2] = k;
                        }
                    }
                }
                br.readLine();
            }
            ans = -1;
            bfs();
            if (ans==-1) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in "+ans+" minute(s).");
            }
        }
    }
    static int[] dl = {0,0,0,0,1,-1}; static int[] dr = {-1,1,0,0,0,0}; static int[] dc = {0,0,1,-1,0,0};
    static void bfs() {
        Queue<int[]> q= new LinkedList<>();
        q.add(sta);
        while (!q.isEmpty()) {
            int[] now = q.poll();
            if (arr[now[0]][now[1]][now[2]]=='E') {
                ans = now[3];
                break;
            }
            for (int d=0;d<6;d++) {
                int new_l = now[0]+dl[d];
                int new_r = now[1]+dr[d];
                int new_c = now[2]+dc[d];
                if (!goCheck(new_l,new_r,new_c)) continue;
                visited[new_l][new_r][new_c] = true;
                q.add(new int[]{new_l,new_r,new_c, now[3]+1});
            }
        }
    }
    static boolean goCheck(int a, int b, int c) {
        if (a<0 || a>=L || b<0 || b>=R || c<0 || c>=C || arr[a][b][c]=='#' ||
            visited[a][b][c]) return false;
        return true;
    }
}