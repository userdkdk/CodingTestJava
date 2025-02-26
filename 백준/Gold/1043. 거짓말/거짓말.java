import java.io.*;
import java.util.*;

public class Main {
    static int[] p; static int n,m; static int[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = new int[n+1];
        arr = new int[m][n+1];
        int ans = 0;

        for (int i=1; i<=n; i++) {
            p[i] = 0;
        }
        st = new StringTokenizer(br.readLine());
        int tmp = Integer.parseInt(st.nextToken());
        for (int i=0;i<tmp;i++) {
            int tmp2 = Integer.parseInt(st.nextToken());
            p[tmp2] = -1;
        }
        for (int tc=0;tc<m;tc++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            arr[tc][0] = num;
            for (int i=1;i<=num;i++) {
                arr[tc][i] = Integer.parseInt(st.nextToken());
            }
        }
//        System.out.println(Arrays.deepToString(arr));
        for (int tc=0;tc<m;tc++) {
            int a = arr[tc][1];
            for (int i=2;i<=arr[tc][0];i++) {
                int b = arr[tc][i];
                union(a,b);
            }
        }
        boolean flag = false;
        for (int tc=0;tc<m;tc++) {
            flag = false;
            for (int i=1;i<=n;i++) {
                int now = findP(arr[tc][i]);
                if (p[now] == -1) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                ans++;
            }
        }
//        System.out.println(Arrays.toString(p));
        System.out.println(ans);
    }
    static int findP(int a) {
        int par = a;
        while (p[par] > 0) {
            par = p[par];
        }
        return par;
    }
    static boolean union(int a, int b) {
        int rootA = findP(a);
        int rootB = findP(b);
        if (rootA == rootB) {
            return false;
        } else {
            if (p[rootA] == -1) {
                p[rootB] = rootA;
            } else if (p[rootB] == -1) {
                p[rootA] = rootB;
            } else {
                if (rootA > rootB) {
                    p[rootA] = rootB;
                } else {
                    p[rootB] = rootA;
                }
            }
        }
        return true;
    }
}
