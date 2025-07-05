import java.util.*;
import java.io.*;

public class Main {
    static int n, m; static char[] arr1, arr2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;

        arr1 = br.readLine().toCharArray();
        arr2 = br.readLine().toCharArray();
        n = arr1.length;
        m = arr2.length;
        int[][] ans = new int[n+1][m+1];

        for (int i=1;i<=n;i++) {
            for (int j=1;j<=m;j++) {
                if (arr1[i-1]==arr2[j-1]) {
                    ans[i][j] = ans[i-1][j-1]+1;
                } else {
                    ans[i][j] = Math.max(ans[i-1][j],ans[i][j-1]);
                }
            }
        }
        System.out.println(ans[n][m]);
    }
}
