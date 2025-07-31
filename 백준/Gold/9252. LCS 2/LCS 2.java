import java.util.*;
import java.io.*;

public class Main {
    static int N, M, max; static char[] arr1, arr2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        arr1 = br.readLine().toCharArray();
        arr2 = br.readLine().toCharArray();
        N = arr1.length;
        M = arr2.length;
        Node[][] ans = new Node[N+1][M+1];
        for (int i=0;i<=N;i++) {
            for (int j=0;j<=M;j++) {
                ans[i][j] = new Node();
            }
        }
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=M;j++) {
                Node max = (ans[i-1][j].len > ans[i][j-1].len) ? ans[i-1][j] : ans[i][j-1];
                if (arr1[i-1]==arr2[j-1]) {
                    if (ans[i-1][j-1].len+1>max.len) {
                        ans[i][j].str = ans[i-1][j-1].str + arr1[i-1];
                        ans[i][j].len = ans[i-1][j-1].len+1;
                    } else {
                        ans[i][j] = max;
                    }
                } else {
                    ans[i][j] = max;
                }
            }
        }
        System.out.println(ans[N][M].len);
        System.out.println(ans[N][M].str);
//        for (int i=1;i<=N;i++) {
//            for (int j=1;j<=M;j++) {
//                System.out.print(ans[i][j].len+" "+ans[i][j].str+", ");
//            }
//            System.out.println();
//        }
    }
    static class Node {
        int len;
        String str="";
        public Node() {}

        public Node(int len, String str) {
            this.len = len;
            this.str = str;
        }
    }
}
