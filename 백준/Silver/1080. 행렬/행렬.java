import java.util.*;
import java.io.*;

public class Main {
    static int N, M; static boolean[][] sta, end; static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 맨 위에서부터 검증하고 마지막에 마지막줄 검증
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = 0;
        sta = new boolean[N][M];
        end = new boolean[N][M];

        for (int i=0;i<N;i++) {
            char[] tmp_arr = br.readLine().toCharArray();
            for (int j=0;j<M;j++) {
                if (tmp_arr[j]=='1') sta[i][j] = true;
            }
        }

        for (int i=0;i<N;i++) {
            char[] tmp_arr = br.readLine().toCharArray();
            for (int j=0;j<M;j++) {
                if (tmp_arr[j]=='1') end[i][j] = true;
            }
        }
        if (N<3 || M<3) {
            out:
            for (int i=0;i<N;i++) {
                for (int j=0;j<M;j++) {
                    if (sta[i][j]!=end[i][j]) {
                        ans = -1;
                        break out;
                    }
                }
            }
        } else {
            for (int i=1;i<N-1;i++) {
                for (int j=1;j<M-1;j++) {
                    if (sta[i-1][j-1]!=end[i-1][j-1]) {
                        changeArr(i,j);
                        ans++;
                    }
                }
                if (sta[i-1][M-2]!=end[i-1][M-2] || sta[i-1][M-1]!=end[i-1][M-1]) {
                    ans = -1;
                    break;
                }
            }
            out:
            for (int i=N-2;i<N;i++) {
                for (int j=0;j<M;j++) {
                    if (sta[i][j] != end[i][j]) {
                        ans = -1;
                        break out;
                    }
                }
            }
        }
        System.out.println(ans);

    }

    static void changeArr(int a, int b) {
        for (int i=a-1;i<=a+1;i++) {
            for (int j=b-1;j<=b+1;j++) {
                sta[i][j] = !sta[i][j];
            }
        }
    }
}
