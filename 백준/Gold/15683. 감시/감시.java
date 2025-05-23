import java.io.*;
import java.util.*;

class Main
{
    static int n, m, cctv_num, min; static int[] dr = {-1,0,1,0}; static int[] dc = {0,1,0,-1};
    static int[][] cctv_dir = {{},{0},{0,2},{0,1},{0,1,2},{0,1,2,3}};
    static int[][] office, modified_office; static int[] cctvs; static ArrayList<int[]> cctv_loc;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        office = new int[n][m];
        modified_office = new int[n][m];
        cctv_loc = new ArrayList<>();
        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp>=1 && tmp<=5)  {
                    cctv_loc.add(new int[]{i,j});
                }
                office[i][j]=tmp;
            }
        }
        cctv_num = cctv_loc.size();
        cctvs = new int[cctv_num];
        min = Integer.MAX_VALUE;

        comb(0);
        System.out.println(min);
    }
    static void comb(int depth) {
        if (depth>=cctv_num)  {
            // 원본 배열 복사하여 개수세기 위한 준비
            for (int i=0;i<n;i++) {
                modified_office[i]=office[i].clone();
            }
            getAns();
            int tmp = 0;
            for (int i=0;i<n;i++) {
                for (int j = 0; j < m; j++) {
                    if (modified_office[i][j] == 0) tmp++;
                }
            }

            min = Math.min(min,tmp);
            return;
        }
        for (int i=0;i<4;i++) {
            cctvs[depth] = i;
            comb(depth+1);
        }
    }
    static void getAns() {
        // 각 cctv의 방향별 개수 체크
        for (int cctv=0;cctv<cctv_num;cctv++) {
            // 현재 cctv의 위치 구하기
            int[] now_loc = cctv_loc.get(cctv);
            // cctv의 방향
            int[] now_dir = cctv_dir[office[now_loc[0]][now_loc[1]]];
            // 현재 cctv의 회전 방향
            int add_dir = cctvs[cctv];
            // 각 방향 표시
            for (int i=0;i<now_dir.length;i++) {
                int new_dir = (now_dir[i] + add_dir)%4;
                int new_r = now_loc[0];
                int new_c = now_loc[1];
                while (true) {
                    new_r += dr[new_dir];
                    new_c += dc[new_dir];
                    if (!goCheck(new_r,new_c)) break;
                    if (modified_office[new_r][new_c] == 0) {
                        modified_office[new_r][new_c] = 7;
                    }
                }
            }
        }
    }
    static boolean goCheck(int a, int b) {
        if (a<0 || a>=n || b<0 || b>=m || office[a][b]==6) return false;
        return true;
    }
}