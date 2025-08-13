import java.util.*;
import java.io.*;

public class Main {
    static int G, P; static int[] p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        p = new int[G+1];
        for (int i=0;i<=G;i++) p[i] = i;

        int ans = 0;
        for (int i=0;i<P;i++) {
            int now = Integer.parseInt(br.readLine());
            int r = getP(now);
            if (p[r]==0) break;
            ans++;
            Union(r,r-1);
        }
        System.out.println(ans);

    }
    static int getP(int a) {
        if (p[a]==a) return a;
        return p[a] = getP(p[a]);
    }
    static void Union(int a, int b) {
        int rootA = getP(a);
        int rootB = getP(b);
        p[rootA] = p[rootB];
    }
}
