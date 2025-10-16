import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] rules;         // rules[x][y] : 2(왼쪽 승), 0(오른쪽 승), 1(무승부=번호 큰 쪽 승)
    static int[][] seq = new int[3][20]; // 1,2번 친구 수열(0-index 저장)
    static boolean[] used;        // 지우 손 사용 여부
    static int[] wins = new int[3];
    static int[] idx = new int[3]; // 1,2번 친구가 쓴 수 개수
    static boolean ok;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        rules = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) rules[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 20; i++) seq[1][i] = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 20; i++) seq[2][i] = Integer.parseInt(st.nextToken()) - 1;

        used = new boolean[N];
        ok = false;

        // 시작은 (지우=0, 친구1=1)
        dfs(0, 1);

        // 백준 출력 규격: 가능하면 1, 아니면 0
        System.out.println(ok ? 1 : 0);
    }

    static void dfs(int a, int b) {
        if (ok) return;
        if (wins[0] == K) { ok = true; return; }
        if (wins[1] == K || wins[2] == K) return;

        // 친구 vs 친구
        if (a != 0 && b != 0) {
            // 접근 전 가드: 두 친구 중 하나라도 수열 소진이면 이 경로 불가
            if (idx[a] >= 20 || idx[b] >= 20) return;

            int ha = seq[a][idx[a]];
            int hb = seq[b][idx[b]];
            int res = rules[ha][hb];
            int winner = decideWinner(a, b, res);

            // 상태 갱신
            idx[a]++; idx[b]++;
            wins[winner]++;

            dfs(winner, 3 - a - b);

            // 롤백
            wins[winner]--;
            idx[a]--; idx[b]--;
            return;
        }

        // 지우가 참가하는 분기: 지우 손을 하나 골라서 시뮬레이션
        // 상대가 친구라면, 그 친구가 더 낼 수 있는지 먼저 확인
        int friend = (a == 0 ? b : a);
        if (idx[friend] >= 20) return; // 친구가 더 못 내면 이 경로 종료

        for (int h = 0; h < N; h++) {
            if (used[h]) continue;       // 지우 손 중복 금지

            // 지우 손 선택
            used[h] = true;

            int res, winner;
            if (a == 0) {
                int hb = seq[b][idx[b]];
                res = rules[h][hb];
                winner = decideWinner(0, b, res);
            } else { // b == 0
                int ha = seq[a][idx[a]];
                res = rules[ha][h];
                winner = decideWinner(a, 0, res);
            }

            // next state
            wins[winner]++;
            if (a == 0) idx[b]++; else idx[a]++;

            // 가지치기: 누군가 K 도달하면 바로 처리
            if (wins[0] == K) { ok = true; 
                // 롤백
                if (a == 0) idx[b]--; else idx[a]--;
                wins[winner]--;
                used[h] = false;
                return;
            }
            if (wins[1] < K && wins[2] < K) {
                dfs(winner, 3 - a - b);
            }

            // 롤백
            if (a == 0) idx[b]--; else idx[a]--;
            wins[winner]--;
            used[h] = false;

            if (ok) return;
        }
    }

    // res: rules[leftHand][rightHand]
    static int decideWinner(int leftPlayer, int rightPlayer, int res) {
        if (res == 2) return leftPlayer;     // 왼쪽 승
        if (res == 0) return rightPlayer;    // 오른쪽 승
        return Math.max(leftPlayer, rightPlayer); // 무승부는 번호 큰 쪽 승
    }
}
