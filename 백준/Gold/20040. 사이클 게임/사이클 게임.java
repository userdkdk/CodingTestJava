import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main
{
    static int[] p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = -1;
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            boolean check = union(a, b);
            if (!check) {
                ans = i + 1;
                break;
            }
        }
        System.out.println(ans);
    }
    // 조상 찾기
    static int findP(int a) {
        int par = a;
        while (p[par] != -1) {
            par = p[par];
        }
        return par;
    }
    // 결합하기
    static boolean union(int a, int b) {
        int parA = findP(a);
        int parB = findP(b);
        if (parA == parB) {
            return false;
        } else if (parA > parB) {
            p[parA] = parB;
            return true;
        } else {
            p[parB] = parA;
            return true;
        }

    }
}