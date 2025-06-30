import java.util.*;
import java.io.*;

public class Main {
    static int n, m; static int[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ans = new int[m];
        comb(0,0);
    }
    static void comb(int idx, int depth) {
        if (depth == m) {
            for (int i=0;i<m;i++) {
                System.out.print(ans[i]+" ");
            }
            System.out.println();
            return;
        }
        for (int i=idx+1;i<=n;i++) {
            ans[depth] = i;
            comb(i,depth+1);
        }
    }
}
