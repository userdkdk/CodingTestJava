import java.io.*;
import java.util.*;

class Main
{
    static int n, m, ans; static ArrayList<int[]> chicks, homes;
    static int[][] chick_home; static int[] pick; static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        chicks = new ArrayList<>();
        homes = new ArrayList<>();
        pick = new int[m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 1) homes.add(new int[]{i, j});
                if (tmp == 2) chicks.add(new int[]{i, j});
            }
        }
        chick_home = new int[homes.size()][chicks.size()];
        visited = new boolean[chicks.size()];
        // 각 집과의 거리 구하기
        for (int i=0; i<homes.size(); i++) {
            for (int j=0; j<chicks.size(); j++) {
                chick_home[i][j] = Math.abs(homes.get(i)[0] - chicks.get(j)[0]) +
                        Math.abs(homes.get(i)[1] - chicks.get(j)[1]);
            }
        }
        ans = Integer.MAX_VALUE;
        comb(0,0);
        System.out.println(ans);
    }
    static void comb(int depth, int idx) {
        if (depth == m) {
            getAns();
            return;
        }
        for (int i=idx; i<chicks.size(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            pick[depth] = i;
            comb(depth+1,i);
            visited[i] = false;
        }
    }
    static void getAns() {
        int tmp_ans = 0;
        for (int i=0;i< homes.size();i++) {
            int tmp = Integer.MAX_VALUE;
            for (int j=0;j<m;j++) {
                tmp = Math.min(tmp, chick_home[i][pick[j]]);
            }
            tmp_ans += tmp;
        }
        ans = Math.min(ans, tmp_ans);
    }
}