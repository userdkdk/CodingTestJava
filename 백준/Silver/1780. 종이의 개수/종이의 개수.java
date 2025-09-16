import java.io.*;
import java.util.*;

public class Main {
    static int N; static int[][] arr; static int[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        ans = new int[3];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        checkArr(0,0,N);
        for (int i=0;i<3;i++) {
            System.out.println(ans[i]);
        }
    }
    static void divArr(int r, int c, int len) {
        int div = len/3;
        for (int i=r;i<r+len;i+=div) {
            for (int j=c;j<c+len;j+=div) {
                checkArr(i,j,div);
            }
        }
    }
    static void checkArr(int r, int c, int len) {
        int num = arr[r][c];
        for (int i=r;i<r+len;i++) {
            for (int j=c;j<c+len;j++) {
                if (arr[i][j]!=num) {
                    divArr(r,c,len);
                    return;
                }
            }
        }
        ans[num+1]+=1;
    }
}
